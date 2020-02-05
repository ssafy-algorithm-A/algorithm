package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bac11559 {
	static Scanner sc = new Scanner(System.in);
	static Queue<Point> Qu = new LinkedList<>();
	static int x = 12;
	static int y = 6;

	static int answer = 0;

	static boolean visited[][];

	// 동 남 서 북
	static int dx[] = { 1, 0, -1, 0, };
	static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) {

		for (int t = 0; t < 1; t++) {

			// 초기화
			String arr[][] = new String[x][y];
			visited = new boolean[x][y];
			Qu.clear();

			// 입력
			for (int i = 0; i < x; i++) {
				arr[i] = sc.next().split("");
			}

			answer = 0;

			solution(x, y, arr);

			System.out.println(answer);
		}

	}

	// t * 12 * 6 * N의 갯수 * 상하 좌우(4) =
	// BFS로 Queue에 들어오면 Visted를 true로 만든다음 Queue에서 나가면서 삭제
	// 삭제 끝난후에는 다시 false로 리셋.
	// 동시에 터지는 경우 연쇄 폭팔이 하나 로봄;;;<------개
	public static void solution(int x, int y, String arr[][]) {
		String temp = "";
		gravity(arr);
		for (int k = 0; k < (x * y) / 4; k++) {

			// 4개짜리인에
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					if (!arr[i][j].equals(".")) {
						if (!visited[i][j]) {
							BFS(i, j, arr);
						}
					}
				}
			}
			delete(arr);
			gravity(arr);
		}
	}

	public static void BFS(int cur_x, int cur_y, String arr[][]) {
		int result = 1;
		Queue<Point> temp_Qu = new LinkedList<>();

		// 첫쨰값
		visited[cur_x][cur_y] = true;
		String giho = arr[cur_x][cur_y];

		Qu.add(new Point(cur_x, cur_y));
		temp_Qu.add(new Point(cur_x, cur_y));

		while (!Qu.isEmpty()) {

			Point temp_p = Qu.poll();
			cur_x = temp_p.x;
			cur_y = temp_p.y;

			// 동서남북 돌리기위한 for문
			for (int i = 0; i < 4; i++) {
				int new_x = cur_x + dy[i];
				int new_y = cur_y + dx[i];

				// 범위안에서만 서치
				if ((new_x >= 0 && new_x < x) && (new_y >= 0 && new_y < y)) {
					if (arr[new_x][new_y].equals(giho) && !visited[new_x][new_y]) {
						visited[new_x][new_y] = true;

						Qu.add(new Point(new_x, new_y));
						temp_Qu.add(new Point(new_x, new_y));

						result++;
					}
				}

			}

		}
		if (result < 4) {
			while (!temp_Qu.isEmpty()) {
				Point temp_P = temp_Qu.poll();
				visited[temp_P.x][temp_P.y] = false;
			}
		}
	}

	public static void delete(String arr[][]) {
		int btn = 0;

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (visited[i][j] == true) {
					arr[i][j] = ".";
					btn = 1;
				}
			}
		}

		for (boolean[] row : visited)
			Arrays.fill(row, false);

		if (btn == 1) {
			answer++;
			gravity(arr);
		}
	}

	public static void gravity(String arr[][]) {

		for (int i = x - 2; i >= 0; i--) {
			for (int j = 0; j < y; j++) {
				String giho = arr[i][j];
				int temp_x = i;

				while (true) {

					// 끝에 도달하면 끝냄
					if (temp_x == x - 1)
						break;

					if (arr[++temp_x][j].equals(".")) {
						arr[temp_x][j] = giho;
						arr[temp_x - 1][j] = ".";
					}

				}
			}
		}

	}

	public static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
