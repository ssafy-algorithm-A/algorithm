package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class bac17136 {

	public static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static Scanner sc = new Scanner(System.in);
	static ArrayList<Point> AL = new ArrayList<>();

	static int answer = Integer.MAX_VALUE;
	static int count_1 = 0;
	static boolean[][] visited;
	static int[][] arr;
	static int count[] = new int[6];

	// 색종이는 10x10, 색종이 종류는 N*N 5장씩(N =1,2,3,4,5)
	// 최소한의 색종이 갯수
	public static void main(String[] args) {

		for (int t = 0; t < 1; t++) {

			// 초기화
			answer = Integer.MAX_VALUE;
			arr = new int[10][10];
			visited = new boolean[10][10];
			make_arr();
			Arrays.fill(count, 0);

			// 솔루션
			solution();
			if(answer == Integer.MAX_VALUE)
				answer = -1;
			System.out.println(answer);
		}

	}

	public static void solution() {
		dfs(0, 0, 0);	
	}

	public static void pprint() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(visited[i][j]);
			}
			System.out.println();
		}
	}

	// i = 0~(10-size) j = 0~(10-size)
	// 10*10
	//
	public static void dfs(int total, int cnt, int idx) {

		if (answer <= cnt) {
			System.out.println("가지치기!");
			return;
		}
		if (total == count_1 && is_covered()) {
			answer = Math.min(answer, cnt);
			System.out.println("Success End!!");
			return;
		}

		for (int i = idx; i < AL.size(); i++) {
			boolean btn = false;
			for (int k = 5; k >= 1; k--) {
				// 덮을 수 있으면서 그 색종이를 다쓰지 않았을 경우
				int new_x = AL.get(i).x + k;
				int new_y = AL.get(i).y + k;

				if (new_x < 10 && new_y < 10 && count[k] != 5) {
					if (!btn) {
						btn = is_cover(AL.get(i).x, AL.get(i).y, k);
					}
					if (btn) {
						cover(AL.get(i).x, AL.get(i).y, k);
						dfs(total + k * k, cnt + 1, i);
						uncover(AL.get(i).x, AL.get(i).y, k);
					}
				}
			}
			//
			//
		}
		
		
	}

	// 배열 만들고 큐에 추가!
	public static void make_arr() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int buffer = sc.nextInt();
				if (buffer == 1) {
					AL.add(new Point(i, j));
					count_1++;
				}
				arr[i][j] = buffer;
			}
		}
	}
	
	// 배열리스트를 이용
		public static boolean is_covered() {
			boolean result = true;
			for(Point p : AL)
				if(!visited[p.x][p.y])
					result = false;
			return result;
		}
	

	// 배열리스트를 이용
	public static boolean is_cover(int p_x, int p_y, int size) {
		for (int i = p_x; i < 10 && i < (p_x + size); i++) {
			for (int j = p_y; j < 10 && j < (p_y + size); j++) {
				if (visited[i][j]) {
					//System.out.println(visited[i][j]);
					return false;
				}
			}
		}
		return true;
	}

	// 색종이로 덮어고 카운터 추가.

	public static void cover(int p_x, int p_y, int size) {
		count[size]++;
		for (int i = p_x; i < 10 && i < (p_x + size); i++) {
			for (int j = p_y; j < 10 && j < (p_y + size); j++) {
				visited[i][j] = true;
			}
		}
	}

	public static void uncover(int p_x, int p_y, int size) {
		count[size]--;
		for (int i = p_x; i < 10 && i < (p_x + size); i++) {
			for (int j = p_y; j < 10 && j < (p_y + size); j++) {
				visited[i][j] = false;
			}
		}
	}

}
