package beackj;

import java.util.Scanner;

/*

 * 

 * 뿌요뿌요의 룰은 다음과 같다.

 

필드에 여러 가지 색깔의 뿌요를 놓는다. 뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.

 

뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다.

 

뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.

 

아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어난다.

 

터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.

 

남규는 최근 뿌요뿌요 게임에 푹 빠졌다. 이 게임은 1:1로 붙는 대전게임이라 잘 쌓는 것도 중요하지만, 상대방이 터뜨린다면 연쇄가 몇 번이 될지 바로 파악할 수 있는 능력도 필요하다. 하지만 아직 실력이 부족하여 남규는 자기 필드에만 신경 쓰기 바쁘다. 상대방의 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산하여 남규를 도와주자!

 

 

 

입력

12*6의 문자가 주어진다.

 

이때 .은 빈공간이고 .이 아닌것은 각각의 색깔의 뿌요를 나타낸다.

 

R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑이다.(모두 대문자로 주어진다.)

 

입력으로 주어지는 필드는 뿌요들이 전부 아래로 떨어진 뒤의 상태(즉 뿌요 아래에 빈 칸이 있는 경우는 없음) 이다.

 */

public class puyopuyo {

	static int dir_x[] = { 0, -1, 0, 1 };
	static int dir_y[] = { -1, 0, 1, 0 };
	static Block[] blocks;
	static int top = -1;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		char arr[][] = new char[12][6];

		for (int i = 0; i < arr.length; i++) {
			String input = sc.next();
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = input.charAt(j);
			}
		}

		int break_count = 0;

		boolean end = true;

		while (end) {

			int inner_count = break_count;

			int max = 0;

			for (int i = 0; i < arr.length; i++) {

				for (int j = 0; j < arr[0].length; j++) {

					if (arr[i][j] != '.') {

						char ch = arr[i][j];

						blocks = new Block[100];

						top = -1;

						erase(arr[i][j], arr, i, j); //지우고

						blocks[++top] = new Block(i, j); //하나는 그냥 넣고
						arr[i][j] = '.';//으면서 지우기

						erase2(ch, arr); // 4개 이상이면 복구

						max = Math.max(max, top);
					}
				}
			}
			// 내리기
			gravity(arr);

			if (max >= 4) {
				break_count++;
			}
			
			//깨진게 없으면 나오기
			if (inner_count == break_count) {
				end = false;
			}

		}
		sc.close();
		System.out.println(break_count);

	}

	public static void erase(char ch, char[][] arr, int x, int y) {

		for (int k = 0; k < dir_x.length; k++) {
			if (x + dir_x[k] >= 0 && y + dir_y[k] >= 0 && x + dir_x[k] < arr.length && y + dir_y[k] < arr[0].length) {
				if (arr[x + dir_x[k]][y + dir_y[k]] == ch && ch != '.') {
					blocks[++top] = new Block(x + dir_x[k], y + dir_y[k]);//블럭의 리스트를 저장하고
					arr[x + dir_x[k]][y + dir_y[k]] = '.';//저장한 곳을 .로 변경하고
					erase(ch, arr, x + dir_x[k], y + dir_y[k]);//재귀
				}
			}
		}
	}

	public static void erase2(char ch, char[][] arr) {//복구

		if (top < 4) {
			for (int i = 0; i <= top; i++) {
				arr[blocks[i].x][blocks[i].y] = ch;
			}
		}
	}

	public static void gravity(char[][] arr) {

		// 검사 순서 설정 좌측 하다부터 시작
		for (int j = 0; j < arr[0].length; j++) {
			int count = 0;
			for (int i = arr.length - 1; i >= 0; i--) {
				if (arr[i][j] == '.') {
					count++;
				} else if (arr[i][j] != '.' && count != 0) {
					arr[i + count][j] = arr[i][j];
					arr[i][j] = '.';
				}
			}
		}
	}
}

class Block {

	int x;

	int y;

	public Block(int x, int y) {

		this.x = x;

		this.y = y;

	}

}