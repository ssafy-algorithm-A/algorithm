package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swe_미생물격리 {
	static int N, M, K;
	static Pair Stone = new Pair(-1, -1, -1, -1);
	static Pair[][][] Parr = new Pair[100][100][4];
	static ArrayList<Point> AL;
	static int[][][] visited_arrD = new int[100][100][4];
	static int[][][] visited_arrN = new int[100][100][4];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 상하좌우
	static int dx[] = { 0, 0, 0, -1, 1 };
	static int dy[] = { 0, -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		int temp = Integer.parseInt(br.readLine());
		int answer = 0;
		// N-셀크기 , M-격리시간, K-미생물수
		for (int testcase = 1; testcase <= temp; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			Pair[] microbe = new Pair[K];
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				microbe[j] = new Pair(x, y, n, d);
			}
			answer = solution(microbe);
			System.out.println("#" + testcase + " " + answer);
		}
	}

	private static int solution(Pair[] microbe) {
		Queue<Pair> queue = new LinkedList<>();
		// queue 넣어주기 & 배열에 미생물 넣기
		for (int i = 0; i < K; i++) {
			queue.add(microbe[i]);
		}
		queue.add(Stone);
		int result = 0;
		int time = 0;
		AL = new ArrayList<>();

		// 큐에 넣어서 돌림 시간초과때문에
		while (!queue.isEmpty()) {
			// 돌이면 한사이클 돈것이니 확인.
			if (queue.peek().equals(Stone)) {
				time++;
				if (time == M)
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
			int idx = 0;
			int flag = 0;
			int new_x = temp.x + dy[temp.d];
			int new_y = temp.y + dx[temp.d];
			while (idx < 4) {
				if (visited_arrD[new_x][new_y][idx] != 0)
					idx++;
				else {
					if (flag == 0) {
						flag = 1;
						AL.add(new Point(new_x, new_y));
					}
					visited_arrD[new_x][new_y][idx] = temp.d;
					visited_arrN[new_x][new_y][idx] = temp.n;
				}
			}
		}
		result = getResult();
		return result;
	}

	static int getResult() {
		int result = 0;
		for (Point p : AL) {
			for (int i = 0; i < 4; i++)
				result += visited_arrN[p.x][p.y][i];
		}
		return result;
	}

	static void init() {
		
		visited_arrN = new int[100][100][4];
		visited_arrD = new int[100][100][4];
		AL.clear();
	}

	static void make_next(Queue<Pair> queue) {
		int max = 0;
		int count = 0;
		int next_d = 0;
		for (int i = 0; i < AL.size(); i++) {
			for (int j = 0; j < 4; j++) {
				if (max < visited_arrN[AL.get(i).x][AL.get(i).y][j]) {
					if (visited_arrD[AL.get(i).x][AL.get(i).y][j] == 0)
						break;
					max = visited_arrN[AL.get(i).x][AL.get(i).y][j];
					next_d = visited_arrD[AL.get(i).x][AL.get(i).y][j];
				}
				count += visited_arrN[AL.get(i).x][AL.get(i).y][j];
			}
		}
		for (int i = 0; i < AL.size(); i++) {
			if (AL.get(i).x == 0 || AL.get(i).y == N - 1 || AL.get(i).y == 0 || AL.get(i).y == N - 1)
				queue.add(new Pair(AL.get(i).x, AL.get(i).y, count, (next_d % 2) == 1 ? next_d + 1 : next_d - 1));
			else
				queue.add(new Pair(AL.get(i).x, AL.get(i).y, count, next_d));
		}
	}

	// x , y , 미생물수, 방향 ,시간
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
	}
}