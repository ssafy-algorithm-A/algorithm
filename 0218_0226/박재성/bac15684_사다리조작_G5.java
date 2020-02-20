package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class bac15684_사다리조작_G5 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int answer = Integer.MAX_VALUE;

	static int N;
	static int H;
	static int M;


	static int arr[][]; // 맵
	static LinkedList<Point> AL;
	static Point[][] p_arr;
	static int out_arr[]; // 결과 출력용
	static Point trr[]; // 조합
	static Point excepted_arr[]; // 선택안된 배열
	
	static int last_flag = 0;

	// N H M | 세로 개수 가로
	// i번 세로선의 결과가 i
	/*
	 * 5 5 6 1 1(왼쪽에서 오른쪽, (1,1) -> (1,2) | (3,2) -> (3,3) 3 2 2 3 5 1 5 4
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M + 1][N + 1];
		p_arr = new Point[M+1][N+1];
		out_arr = new int[N + 1];
		excepted_arr = new Point[N * M - H + 1];
		AL = new LinkedList<Point>();
		
		
		if (H != 0) {
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				int in_x = Integer.parseInt(st.nextToken());
				int in_y = Integer.parseInt(st.nextToken());
				arr[in_x][in_y] = 1;
				arr[in_x][in_y+1] = 1;
				p_arr[in_x][in_y] = new Point(in_x, in_y);
				AL.add(new Point(in_x, in_y));
			}
		}
		
		// 조합만들기 위해 만든 배열, 연결되지않은!
		int idx = 0;
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= (N-1); j++) {
				if (arr[i][j] != 1) // 선택안된
					excepted_arr[idx++] = new Point(i, j);
			}
		}

		// 다돌려요
		for (int i = 0; i <= 3; i++) { // 배치할 가로수
			trr = new Point[i];
			comb(idx, i ,i);
		}
		
		if(answer == Integer.MAX_VALUE)
			answer = -1;

		System.out.println(answer);
	}

	public static void DFS(int count, int current_j ,int start_i, Point trr[]) {

		if(last_flag ==1)
			return;
		
		if (count == M + 1) { // 종료
			out_arr[start_i] = current_j;
			return;
		}		
		int next_current_j = current_j;	
		
		if(p_arr[count][current_j] != null) {
			next_current_j +=1;
		}
		if(p_arr[count][current_j-1] != null) {
			next_current_j -=1;
		}	
		
		DFS(count+1,next_current_j ,start_i, trr);
		
	}

	
	
	public static void comb(int n, int r, int origin_r) {
		
		if(last_flag ==1)
			return;
		
		if (r == 0) { // 종료 파트
			Arrays.fill(out_arr, 0);
	
			for(Point p : trr) {
				p_arr[p.x][p.y] = new Point(p.x,p.y);
				AL.add(p);
			}
			
			for (int j = 1; j <= N; j++) DFS(1,j, j, trr);
			
			for(Point p : trr) {
				p_arr[p.x][p.y] = null;
				AL.remove(p);
			}

			int flag = 0;
			for(int i=1; i<=N; i++)
				if(out_arr[i] != i)
					flag =1;
			
			if(flag == 0) {
				answer = Math.min(answer, origin_r);
				last_flag = 1;
				return;
			}
			//System.out.println();
			
		} else if (n < r) {
			return;
		} else // 재귀 파트
		{
			trr[r - 1] = excepted_arr[n - 1];
			comb(n - 1, r - 1, origin_r);
			comb(n - 1, r, origin_r);
		}
	}

	
	
	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + "]";
		}

		@Override
		public int hashCode() {
			return ((x + "," + y)).hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Point) {
				Point tmp = (Point) obj;
				return (x== tmp.x) && (y == tmp.y);
			}
			return false;
		}
		
		
		
	}
	
	
}
