package 백준;

/*
 * memory : 19140KB
 * time : 216ms
 */

import java.util.Scanner;

public class problem1449주사위굴리기 {

	static int bottom, top, E, W, S, N = 0;

	static int row;
	static int col;
	static int arr[][];
	static int dir[][] = { { 0, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		row = sc.nextInt();
		col = sc.nextInt();
		arr = new int[row][col];

		int x = sc.nextInt();
		int y = sc.nextInt();
		int num = sc.nextInt();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				arr[i][j] = sc.nextInt();
			}
		}



		for (int i = 0; i < num; i++) {
			int d = sc.nextInt();
			int dx = x + dir[d][0];
			int dy = y + dir[d][1];

			if (dx < 0 || dy < 0 || dx >= row || dy >= col)
				continue;
			else {
				moveDice(d);
				if(arr[dx][dy] == 0) {
					arr[dx][dy] = bottom;
				}
				else {
					bottom = arr[dx][dy];
					arr[dx][dy] = 0;
				}
				x = dx;
				y = dy;
				
				System.out.println(top);
			}
		}
		sc.close();
	}

	private static void moveDice(int d) {
		int tmp = bottom;
		if (d == 1) {//동
			bottom = E;
			E = top;
			top = W;
			W = tmp;
		} else if (d == 2) {//서
			bottom = W;
			W = top;
			top = E;
			E = tmp;
		} else if (d == 3) {//남
			bottom = N;
			N = top;
			top = S;
			S = tmp;
		} else if (d == 4) {//북
			bottom = S;
			S = top;
			top = N;
			N = tmp;
		}

	}
}
