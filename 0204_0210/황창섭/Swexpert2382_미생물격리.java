import java.util.Arrays;
import java.util.Scanner;

public class Swexpert2382_미생물격리 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 }; // 상하좌우

	static class Microbe {
		Microbe(int r, int c, int a, int d, int i, int t) {
			R = r;
			C = c;
			amount = a;
			dest = d;
			index = i;
			tempMax = t;
		}

		int R;
		int C;
		int amount; // 미생물 수
		int dest;	// 현재 방향
		int index;	// 현재 미생물 번호
		int tempMax;	// 3명 이상의 미생물이 합칠경우를 방지하기 위한 변수
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();
			int[][] map = new int[N][N];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					map[i][j] = 10001;
				}
			}

			Microbe[] micro = new Microbe[K];
			boolean[] isDead = new boolean[K]; // 미생물이 죽었는지 확인하기 위한 변수
			
			// 미생물 정보 입력 및 맵 생성
			for (int i = 0; i < micro.length; i++) {
				micro[i] = new Microbe(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt() - 1, i, 0);
				map[micro[i].R][micro[i].C] = i;
			} 

			for (int time = 0; time < M; time++) {
				
				for (int i = 0; i < micro.length; i++) {
					if (!isDead[i]) { // 미생물이 살아있다면
						int curR = micro[i].R;
						int curC = micro[i].C;
						int curAmount = micro[i].amount;
						int curDest = micro[i].dest;
						int curTemp = micro[i].tempMax;

						micro[i].tempMax = curAmount; // 미생물이 실제적으로 움직일때 tempMax 최신화
						int nextR = curR + dr[curDest];
						int nextC = curC + dc[curDest];

						// 맵 경계(빨간 약품이 칠해져 있는 셀 영역 도달)
						if (nextR == 0 || nextC == 0 || nextR == N-1 || nextC == N-1) {
							micro[i].amount /= 2;
							if (curDest == 0) {
								micro[i].dest = 1;
							} else if (curDest == 1) {
								micro[i].dest = 0;
							} else if (curDest == 2) {
								micro[i].dest = 3;
							} else if (curDest == 3) {
								micro[i].dest = 2;
							}
						}

						// 미생물의 이동순서가 1 2 3 4 5... 방식으로 이뤄지므로
						// 3의 현재 위치로 1이 이동했을 경우 3은 이동하지 않았으므로
						// 합치는 과정을 처리하지 않고 뒤에 번호가 앞의 번호의 미생물에
						// 도달했을 경우에만 합치는 과정 진행
						if (map[nextR][nextC] < i) {
							
							// 미생물의 수가 30, 20, 40의 경우 
							// 실질적으로 40의 이동방향으로 진행해야하지만
							// 앞의 두 미생물을 합치면 50이 되기때문에
							// 이동방향의 문제가 발생하므로 tempMax 변수 설정
							if (micro[map[nextR][nextC]].tempMax < micro[i].tempMax) {
								micro[i].amount += micro[map[nextR][nextC]].amount;
								isDead[map[nextR][nextC]] = true;
								map[nextR][nextC] = i;
							} else {
								micro[map[nextR][nextC]].amount += micro[i].amount;
								isDead[i] = true;
							}
						}
						else if(map[nextR][nextC] == 10001 || map[nextR][nextC] > i ) {
							map[nextR][nextC] = i;
						}
						
						micro[i].R = nextR;
						micro[i].C = nextC;
						
						// 미생물이 이동하기 전의 위치 빈공간으로 변경
						if(map[curR][curC] == micro[i].index)
							map[curR][curC] = 10001;
						
					} // end of if
				} // end of micro
			} // end of time
			
			int answer=0;
			for (int i = 0; i < isDead.length; i++) {
				if(!isDead[i]) {
					answer += micro[i].amount;
				}
			}
			
			System.out.println("#" + test_case + " " + answer);

		} // end of testcase

	}
}
