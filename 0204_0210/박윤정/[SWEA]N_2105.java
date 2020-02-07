package SWEA;

import java.util.HashMap;
import java.util.Scanner;

public class N_2105 {
	static int[][] dot = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };
	// 순서는 우하 -> 좌하 -> 좌상 -> 우상 (0,1,2,3)
	static int[][] arr;
	static int result;
	static HashMap<Integer, Integer> hm;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			arr = new int[N][N];
			result = -1;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					boolean[][] visited = new boolean[N][N];
					hm = new HashMap<>();
					hm.put(arr[i][j], 1);
					go(visited, i, j, i, j, 0);
				}
			}
			System.out.println("#" + test_case+ " " + result);
		}
	}

	public static void go(boolean[][] visited, int sx, int sy, int x, int y, int dir) {
		if(dir == 4) return;
		
		int dx = x + dot[dir][0];
		int dy = y + dot[dir][1];

		visited[x][y] = true;
		if (sx == dx && sy == dy) {
			result = Math.max(result, hm.size());
			return;
		}	
		if (dx < 0 || dy < 0 || dx >= arr.length || dy >= arr[0].length || visited[dx][dy] || hm.containsKey(arr[dx][dy])) {
			return;
		}
		
		hm.put(arr[dx][dy], 1);
		go(visited, sx, sy, dx, dy, dir);
		go(visited, sx, sy, dx, dy, dir + 1);
		visited[dx][dy] = false;
		hm.remove(arr[dx][dy]);
	}
}
