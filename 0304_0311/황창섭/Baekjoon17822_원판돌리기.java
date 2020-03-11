import java.util.Scanner;

public class Baekjoon17822_���ǵ����� {
	private static int[][] circle;
	private static int N;
	private static int M;
	private static boolean checkChange;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		int T = sc.nextInt();
		circle = new int[N + 1][M]; // ����
		for (int i = 1; i < circle.length; i++) {
			for (int j = 0; j < circle[i].length; j++) {
				circle[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < T; i++) {
			int x = sc.nextInt(); // ���
			int d = sc.nextInt(); // ����
			int k = sc.nextInt(); // ȸ����

			// 1. ��ȣ�� x�� ����� ������ d�������� kĭ ȸ����Ų��. d�� 0�� ���� �ð� ����, 1�� ���� �ݽð� �����̴�.
			for (int j = x; j <= N; j += x) {
				if (d == 0) { // �ð�
					for (int l = 0; l < k; l++) {
						int temp = circle[j][M - 1];
						for (int m = M - 1; m > 0; m--) {
							circle[j][m] = circle[j][m - 1];
						}
						circle[j][0] = temp;
					}

				} else { // �ݽð�
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

			// 2. �����ϸ鼭 ���� ���� ���� ��� ã�´�.
			// dfs ���, checkChange : �����ϸ鼭 ���ϼ��� �ִ��� ����
			boolean check = false;
			for (int r = 1; r <= N; r++) {
				for (int c = 0; c < M; c++) {
					if (!visited[r][c] && circle[r][c] > 0) {
						visited[r][c] = true;
						checkChange = false;
						dfs(r, c, visited);
						// 2-1 �׷��� ���� �ִ� ��쿡�� ���ǿ��� �����ϸ鼭 ���� ���� ��� �����.
						if (checkChange) {
							circle[r][c] = 0;
							check = true;
						}
					}
				}
			}

			// 2-2 ���� ��쿡�� ���ǿ� ���� ���� ����� ���ϰ�, ��պ��� ū ������ 1�� ����, ���� ������ 1�� ���Ѵ�.
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
		// ���ǿ� ���� ���� ��
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
