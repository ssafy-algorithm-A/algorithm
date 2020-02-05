package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class bac4179 {

	static Scanner sc = new Scanner(System.in);

	static int answer = Integer.MAX_VALUE;
	static int arr[][];
	static int R;
	static int C;

	static int J_x;
	static int J_y;

	static Point Stone = new Point(-1, -1);
	static Queue<Point> Qu;
	static ArrayList<Point> AL;

	// 동 서 남 북
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	public static void main(String[] args) {

		// 행 열
		R = sc.nextInt();
		C = sc.nextInt();
		arr = new int[R][C];
		Qu = new LinkedList<>();
		AL = new ArrayList<>();
		String return_value = "IMPOSSIBLE";

		// 입력
		make_Arr(R, C);

		solution();
		if ((answer != Integer.MAX_VALUE))
			return_value = answer + "";

		System.out.println(return_value);

	}

	public static void solution() {

		Move(J_x, J_y);

	}

	public static void Move(int cur_x, int cur_y) {

		Qu.add(new Point(cur_x, cur_y));
		Qu.add(Stone);
		for (Point p : AL)
			Qu.add(p);
		Qu.add(Stone);

		while (!Qu.isEmpty()) {
			int btn = 0;

			// 사람 움직임
			while (!Qu.isEmpty()) {
				Point p_t = Qu.poll();

				if (p_t.equals(Stone)) {
					Qu.add(Stone);
					break;
				}
				for (int i = 0; i < 4; i++) {
					int new_x = p_t.x + dy[i];
					int new_y = p_t.y + dx[i];
					if ((new_x >= 0 && new_x < R && new_y >= 0 && new_y < C)) {
						if ((arr[new_x][new_y] <= arr[p_t.x][p_t.y])
								&& (arr[new_x][new_y] == 0 || arr[p_t.x][p_t.y] < arr[new_x][new_y])) {
							arr[new_x][new_y] = arr[p_t.x][p_t.y] + 1;
							Qu.add(new Point(new_x, new_y));
						}
					} else {
						if (is_edge(p_t.x, p_t.y)) {
							if (arr[p_t.x][p_t.y] != -2)// 예외케이스
								answer = Math.min(answer, arr[p_t.x][p_t.y]);
							btn = 1;
							break;
						}
					}
				}
			}

			pprint(R, C);

			if (btn == 1)
				break;

			// 불 움직임
			while (!Qu.isEmpty()) {

				Point p_t = Qu.poll();

				if (p_t.equals(Stone)) {
					if (Qu.peek().equals(Stone))
						btn = 1;
					Qu.add(Stone);
					break;
				}

				for (int i = 0; i < 4; i++) {
					int new_x = p_t.x + dy[i];
					int new_y = p_t.y + dx[i];
					if ((new_x >= 0 && new_x < R && new_y >= 0 && new_y < C)) {
						if (arr[new_x][new_y] >= 0) {
							arr[new_x][new_y] = -2;
							Qu.add(new Point(new_x, new_y));
						}
					}
				}
			}
			pprint(R, C);
			if (btn == 1)
				break;

			// 큐가 비면.
			if (Qu.size() == 2)
				break;
		}
	}

	//
	public static void make_Arr(int R, int C) {
		for (int i = 0; i < R; i++) {
			String buffer = sc.next();
			for (int j = 0; j < C; j++) {

				if (buffer.charAt(j) == '.') {
				} else if (buffer.charAt(j) == '#')
					arr[i][j] = -1;
				else if (buffer.charAt(j) == 'F') {
					AL.add(new Point(i, j));
					arr[i][j] = -2;
				} else if (buffer.charAt(j) == 'J') {
					J_x = i;
					J_y = j;
					arr[i][j] = 1;
				}
			}
		}
	}

	public static boolean is_edge(int cur_x, int cur_y) {
		boolean result = false;
		if (cur_x == 0 || cur_x == R - 1 || cur_y == 0 || cur_y == C - 1)
			result = true;
		return result;
	}

	public static void pprint(int R, int C) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.printf("%5d ", arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

}