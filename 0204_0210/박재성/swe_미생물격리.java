package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class swe_미생물격리 {
	static int N, M, K;
	static Pair Stone = new Pair(-1, -1, -1, -1);
	static Set<Point> AL;
	static int[][] visited_arrD;
	static int[][] visited_arrN;
	static int[][] visited_arrTotal;	
	static int[][][] visited_arr_for_Clone;
	static Queue<Pair> queue;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// 상하좌우
	static int dx[] = { 0, 0, 0, -1, 1 };
	static int dy[] = { 0, -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {

		// testcase 입력
		int temp = Integer.parseInt(br.readLine());
		int answer = 0;

		// N-셀크기 , M-격리시간, K-미생물수
		for (int testcase = 1; testcase <= temp; testcase++) {

			input();
			answer = solution();
			System.out.println("#" + testcase + " " + answer);

		}
	}

	// 입력
	public static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited_arrD = new int[N][N];
		visited_arrN = new int[N][N];
		visited_arrTotal = new int[N][N];
		queue = new LinkedList<>();
		for (int j = 0; j < K; j++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			queue.add(new Pair(x, y, n, d));
		}
	}

	private static int solution() {

		int result = 0;
		int time = -1;
		int idx;
		int new_x;
		int new_y;

		AL = new HashSet<>();
		queue.add(Stone);

		// 큐에 넣어서 돌림 시간초과때문에
		while (!queue.isEmpty()) {

			// 돌이면 한사이클 돈것이니 확인.
			if (queue.peek().equals(Stone)) {

				time++;
				if (time == M) {
					break;
				}
				if (AL.size() == 0)
					break;

				queue.poll();

				// 다음 큐
				make_next(queue);
				queue.add(Stone);

				init();
			}

			// 사이클 돌리기
			// 1234 - 상하좌우
			Pair temp = queue.poll();
			idx = 0;
			new_x = temp.x + dy[temp.d];
			new_y = temp.y + dx[temp.d];

			// 끝에서 위치 바꾸는 거 추가.

			AL.add(new Point(new_x, new_y));
			if (visited_arrN[new_x][new_y] < temp.n) {
				visited_arrD[new_x][new_y] = temp.d;
				visited_arrN[new_x][new_y] = temp.n;
			}
			visited_arrTotal[new_x][new_y] += temp.n;

		}

		result = getResult();
		return result;
	}

	static int getResult() {
		int result = 0;
		for (Point p : AL) {
				result += visited_arrTotal[p.x][p.y];		
		}
		return result;
	}

	static void init() {

		visited_arrN = new int[N][N];
		visited_arrD = new int[N][N];
		visited_arrTotal = new int[N][N];
		AL.clear();
	}

	static void make_next(Queue<Pair> queue) {
		int r_dir;
		int r_N;
	
		//for(Point p : AL)
			//System.out.println(p.x + " - " + p.y);
		//System.out.println();
		for (Point p : AL) {
			r_dir = visited_arrD[p.x][p.y];
			r_N = visited_arrTotal[p.x][p.y];

			// 1)
			// 엣지 부분이면 방향 반대로 바꾸고, 절반
			if (p.x == 0 || p.x == N - 1 || p.y == 0 || p.y == N - 1)
				queue.add(new Pair(p.x, p.y, (r_N / 2), (r_dir % 2) == 1 ? r_dir + 1 : r_dir - 1));
			else
				queue.add(new Pair(p.x, p.y, r_N, r_dir));

		}

	}// end make_next

	// x , y , 미생물수, 방향
	static class Pair {
		private int x, y, n, d;

		public Pair(int x, int y, int n, int d) {
			this.x = x;
			this.y = y;
			this.n = n;
			this.d = d;
		}
	}

	static class Point {
		private int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Point) {
				Point tmp = (Point) obj;
				return (x == tmp.x) && (y == tmp.y);
			}
			return false;
		}

		@Override
		public int hashCode() {
			return ( (x+y) + "" ).hashCode();
		}
	}
}
