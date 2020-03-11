package 백준;

/**
 * 79%에서  시간초과
 * 
 */

import java.util.Scanner;

public class problem16234인구이동 {

	static int N, low, high, sum;
	static int[][] arr, visitedArr;
	static int[][] dir = { { 1, 0 }, { 0, 1 } };
	static boolean[] visited;

	static int[] q;
	static int front = -1;
	static int rear = -1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		low = sc.nextInt();
		high = sc.nextInt();

		arr = new int[N][N];

		q = new int[N * N];//배열

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		//여기까지 받기끝
		
		int count = 0;
		while (true) {

			visitedArr = new int[N * N][N * N];
			visited = new boolean[N * N];

			boolean open = connect();//조건 맞춰서 국경 열린지 확인

			if (open) {
				count++;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {

						front = -1;
						rear = -1;

						if (!visited[i * N + j])
							dfs(i * N + j);
						
						if (rear > 0) {
							avgStart();
						}
						else {
							sum = 0;
						}
					}
				}
			} else {
				break;
			}

		}
		System.out.println(count);

		sc.close();
	}

	private static boolean connect() {
		boolean change = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int d = 0; d < dir.length; d++) {
					int dx = i + dir[d][0];
					int dy = j + dir[d][1];

					if (dx >= N || dy >= N)
						continue;
					else {
						int t = Math.abs(arr[i][j] - arr[dx][dy]);

						if (t >= low && t <= high) {
							change = true;
							visitedArr[i * N + j][(dx * N) + dy] = 1;
							visitedArr[(dx * N) + dy][i * N + j] = 1;
						}
					}
				}
			}
		}

		return change;
	}

	private static void avgStart() {

		int count = rear + 1;


		int avg = sum / count;
		sum = 0;
		

		for (int i = 0; i <= rear; i++) {

			int t = q[i];
			int r = t / N;
			int c = t % N;

			arr[r][c] = avg;
		}

	}

	private static void dfs(int num) {
		q[++rear] = num;
		visited[num] = true;
		sum += arr[num / N][num % N];

		for (int i = 0; i < N * N; i++) {
			if (visitedArr[num][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
	}
}
