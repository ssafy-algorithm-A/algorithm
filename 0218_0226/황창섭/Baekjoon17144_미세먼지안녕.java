import java.util.Scanner;

// 464ms 29076kb
public class Baekjoon17144_미세먼지안녕 {

	private static Map[][] map;

	static class Map {
		int dust;
		int totalDust;

		public Map(int dust, int totalDust) {
			this.dust = dust;
			this.totalDust = totalDust;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		int T = sc.nextInt();
		map = new Map[R][C];
		int airR = 0, airC = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int dust = sc.nextInt();
				if (dust == -1) {
					airR = i;
					airC = j;
				}
				map[i][j] = new Map(dust, dust);
			}
		}
		/////////// 위로 입력 부분 ///////////

		for (int t = 0; t < T; t++) { // t초까지
			

			// 먼지 퍼트리기
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j].dust > 0) { // 현재 위치에 먼지가 있을경우
						int count = 0;
						if (i - 1 >= 0 && map[i - 1][j].dust != -1) { // 상
							count++;
							map[i - 1][j].totalDust += map[i][j].dust / 5;
						}
						if (i + 1 < R && map[i + 1][j].dust != -1) { // 하
							count++;
							map[i + 1][j].totalDust += map[i][j].dust / 5;
						}
						if (j - 1 >= 0 && map[i][j - 1].dust != -1) { // 좌
							count++;
							map[i][j - 1].totalDust += map[i][j].dust / 5;
						}

						if (j + 1 < C && map[i][j + 1].dust != -1) { // 우
							count++;
							map[i][j + 1].totalDust += map[i][j].dust / 5;
						}

						map[i][j].totalDust -= (map[i][j].dust / 5) * count;
						// 현재 위치의 먼지는 일단 변경하지 말고 totalDust에 저장한 후
						// 확산이 끝나면 변경
					}
				}
			} // end of diffustion
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j].dust = map[i][j].totalDust; // 현재 먼지를 저장된 먼지로 갱신
				}
			}

			// 공기 청정기 가동, 아주아주 노가다 방법으로 해결
			int tempR = airR + 1;
			int tempC = airC;
			while (tempR + 1 < R) {
				map[tempR][tempC] = map[tempR + 1][tempC];
				tempR++;
			}
			while (tempC + 1 < C) {
				map[tempR][tempC] = map[tempR][tempC + 1];
				tempC++;
			}
			while (tempR - 1 >= airR) {
				map[tempR][tempC] = map[tempR - 1][tempC];
				tempR--;
			}
			while (tempC - 1 > airC) {
				map[tempR][tempC] = map[tempR][tempC - 1];
				tempC--;
			}
			map[airR][airC + 1] = new Map(0, 0);

			tempR = airR - 2;
			tempC = airC;
			while (tempR - 1 >= 0) {
				map[tempR][tempC] = map[tempR - 1][tempC];
				tempR--;
			}
			while (tempC + 1 < C) {
				map[tempR][tempC] = map[tempR][tempC + 1];
				tempC++;
			}
			while (tempR + 1 < airR) {
				map[tempR][tempC] = map[tempR + 1][tempC];
				tempR++;
			}
			while (tempC - 1 > airC) {
				map[tempR][tempC] = map[tempR][tempC - 1];
				tempC--;
			}
			map[airR - 1][airC + 1] = new Map(0, 0);

		} // end of second

		int answer = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				answer += map[i][j].dust;
			}
		}
		System.out.println(answer + 2); // 공기청정기 위치는 -1이 2개이므로 +2로 출력

	} // end of main

} // end of class