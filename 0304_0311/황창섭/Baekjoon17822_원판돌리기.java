import java.util.Scanner;

public class Baekjoon17822_원판돌리기 {
	private static int[][] circle;
	private static int N;
	private static int M;
	private static boolean checkChange;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		int T = sc.nextInt();
		circle = new int[N + 1][M]; // 원판
		for (int i = 1; i < circle.length; i++) {
			for (int j = 0; j < circle[i].length; j++) {
				circle[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < T; i++) {
			int x = sc.nextInt(); // 배수
			int d = sc.nextInt(); // 방향
			int k = sc.nextInt(); // 회전수

			// 1. 번호가 x의 배수인 원판을 d방향으로 k칸 회전시킨다. d가 0인 경우는 시계 방향, 1인 경우는 반시계 방향이다.
			for (int j = x; j <= N; j += x) {
				if (d == 0) { // 시계
					for (int l = 0; l < k; l++) {
						int temp = circle[j][M - 1];
						for (int m = M - 1; m > 0; m--) {
							circle[j][m] = circle[j][m - 1];
						}
						circle[j][0] = temp;
					}

				} else { // 반시계
					for (int l = 0; l < k; l++) {
						int temp = circle[j][0];
						for (int m = 0; m < circle[j].length - 1; m++) {
							circle[j][m] = circle[j][m + 1];
						}
						circle[j][M - 1] = temp;
					}
				}
			}

			boolean[][] visited = new boolean[N + 1][M];

			// 2. 인접하면서 수가 같은 것을 모두 찾는다.
			// dfs 사용, checkChange : 인접하면서 동일수가 있는지 여부
			boolean check = false;
			for (int r = 1; r <= N; r++) {
				for (int c = 0; c < M; c++) {
					if (!visited[r][c] && circle[r][c] > 0) {
						visited[r][c] = true;
						checkChange = false;
						dfs(r, c, visited);
						// 2-1 그러한 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
						if (checkChange) {
							circle[r][c] = 0;
							check = true;
						}
					}
				}
			}

			// 2-2 없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
			if (!check) {
				double sum = 0.0;
				double count = 0.0;
				for (int r = 1; r < circle.length; r++) {
					for (int c = 0; c < circle[r].length; c++) {
						if (circle[r][c] > 0) {
							sum += circle[r][c];
							count++;
						}
					}
				}
				double avg = sum / count;

				for (int r = 1; r < circle.length; r++) {
					for (int c = 0; c < circle[r].length; c++) {
						if (circle[r][c] > avg && circle[r][c] != 0) {
							circle[r][c] = circle[r][c] - 1;
						} else if (circle[r][c] < avg && circle[r][c] != 0) {
							circle[r][c] = circle[r][c] + 1;
						}
					}
				}
			}

		} // end of T

		int answer = 0;
		// 원판에 적힌 수의 합
		for (int r = 1; r < circle.length; r++) {
			for (int c = 0; c < circle[r].length; c++) {
				answer += circle[r][c];
			}
		}

		System.out.println(answer);

	} // end of main

	public static void dfs(int r, int c, boolean[][] visited) {
		if (r - 1 > 0) {
			if (circle[r][c] == circle[r - 1][c] && !visited[r - 1][c]) {
				visited[r - 1][c] = true;
				dfs(r - 1, c, visited);
				circle[r - 1][c] = 0;
				checkChange = true;
			}
		}

		if (r + 1 <= N) {
			if (circle[r][c] == circle[r + 1][c] && !visited[r + 1][c]) {
				visited[r + 1][c] = true;
				dfs(r + 1, c, visited);
				circle[r + 1][c] = 0;
				checkChange = true;
			}
		}

		if (circle[r][c] == circle[r][(c + 1) % M] && !visited[r][(c + 1) % M]) {
			visited[r][(c + 1) % M] = true;
			dfs(r, (c + 1) % M, visited);
			circle[r][(c + 1) % M] = 0;
			checkChange = true;
		}

		if (circle[r][c] == circle[r][(c - 1 + M) % M] && !visited[r][(c - 1 + M) % M]) {
			visited[r][(c - 1 + M) % M] = true;
			dfs(r, (c - 1 + M) % M, visited);
			circle[r][(c - 1 + M) % M] = 0;
			checkChange = true;
		}

	} // end of dfs

}
