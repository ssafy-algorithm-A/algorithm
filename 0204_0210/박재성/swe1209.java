package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class a {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// 선언
	static int answer;
	static int N;
	static int arr[][];
	static boolean visited[][];
	static int[] dp;// 해쉬맵으로 하려다가 자연수가 100까지밖에없다고해서..
	static int first_x;
	static int first_y;
	static int last_x;
	static int last_y;
	static int pos_x, pos_y;
	// 북동 동남 남서 서북, 시계방향
	static int dx[] = { 0, 1, 1, -1, -1 };
	static int dy[] = { 0, -1, 1, 1, -1 };
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			init();
			solution();
			System.out.println("#" + (testcase) + " " + answer);
			// System.out.println(last_x + " -- " + last_y);
		}
	}
	public static void solution() {
		// 20*20*100 -> 4만*가지...

		// 3초면 1억 5000만번의 연산 안넘어야함

		for (int i = 1; i <= N; i++) {

			for (int j = 1; j <= N; j++) {

				// boolean flag_4[] = new boolean[5];

				// System.out.println("i :" + i + " j : " + j + "=================");

				first_x = i;

				first_y = j;

				visited[i][j] = true;

				dp[arr[i][j]] = 1;

				dfs(i, j, 0, 0, 1, 0, 0);// x y count cur_dir next_dir flag

				dp[arr[i][j]] = 0;

				visited[i][j] = false;

			}

		}

	}

	public static boolean isEnd(boolean[] flag_4) {

		boolean result = true;

		for (int i = 1; i <= 4; i++)

			if (!flag_4[i])

				result = false;

		return result;

	}

	public static boolean isDuplicated(int cur_x, int cur_y) {

		boolean result = false;

		if (dp[arr[cur_x][cur_y]] > 0)

			result = true;

		return result;

	}

	public static void dfs(int cur_x, int cur_y, int count, int cur_dir, int next_dir, int posx, int posy) {

		dp[arr[cur_x][cur_y]]++;

		// 종료문

		if (next_dir == 4) {

			if ((cur_x == first_x && cur_y == first_y)) {

				if (answer < count) {

					answer = count;

					pos_x = posx;

					pos_y = posy;

					last_x = first_x;

					last_y = first_y;

//					dprint();

				}

				answer = Math.max(answer, count);

				// System.out.println("종료 - " + count);

				return;

			}

		}

		int new_x = cur_x + dy[next_dir];

		int new_y = cur_y + dx[next_dir];

		// 범위니

		if (new_x >= 1 && new_x <= N && new_y >= 1 && new_y <= N) {

			if ((!isDuplicated(new_x, new_y) && !visited[new_x][new_y])

					|| (new_x == first_x && new_y == first_y)) {

				// System.out.println("방향 : "+next_dir +" n_x :" + new_x + "---- n_y : " +
				// new_y);

				dfs(new_x, new_y, count + 1, next_dir, next_dir, posx, posy);

				if (next_dir != 4) {

					dfs(new_x, new_y, count + 1, next_dir, next_dir + 1, new_x, new_y);

				}

			}

		}

		dp[arr[cur_x][cur_y]] = 0;

	}

	public static void init() throws IOException {

		// 선언과 초기화

		answer = -1;

		N = Integer.parseInt(br.readLine());

		arr = new int[N + 1][N + 1];

		dp = new int[101];

		visited = new boolean[N + 1][N + 1];

		// 출력

		for (int i = 1; i <= N; i++) {

			String row_t[] = br.readLine().split(" ");

			for (int j = 0; j < N; j++) {

				arr[i][j + 1] = Integer.parseInt(row_t[j]);

			}

		}

	}

//	 public static void pprint() {

//	 

//	 System.out.print("   "); for(int i=1; i<=N; i++) System.out.printf("%3d ",

//	 i); System.out.println(); for(int i=1; i<=N; i++) {

//	 System.out.printf("%2d|",i ); for(int j =1; j<=N; j++) {

//	 

//	 System.out.printf("%3d ",arr[i][j] ); } System.out.println(); } }

//	 

	public static void dprint() {

		for (int i = 1; i <= 100; i++) {

			System.out.print(dp[i] + " ");

			if (i % 20 == 0)

				System.out.println();

		}

		System.out.println();

	}

}

class Point {

	int x;

	int y;

	Point(int x, int y) {

		this.x = x;

		this.y = y;

	}

}