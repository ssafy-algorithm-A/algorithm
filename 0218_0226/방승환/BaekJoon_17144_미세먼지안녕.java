import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

class BaekJoon_17144_미세먼지안녕 {

	static int R, C, T;
	static int[][] map;
	static Queue<Pair> queue;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
	static ArrayList<Pair> air = new ArrayList<Pair>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		map = new int[R][C];
		queue = new LinkedList<Pair>();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] != -1 && map[i][j] != 0)
					queue.offer(new Pair(i, j, map[i][j]));
				if (map[i][j] == -1) {
					air.add(new Pair(i, j, map[i][j]));
				}
			}
		}

		for (int i = 0; i < T; i++) {
			spread();
			wind();

			for(int j=0; j<R; j++) {
				for(int k=0; k<C; k++) {
					if(map[j][k] != -1 && map[j][k] != 0)
						queue.offer(new Pair(j, k, map[j][k]));
				}
			}
			
		}
		
		int answer = 0;
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] != -1)
					answer += map[i][j];
			}
		}
		System.out.println(answer);

	}
	
	
	
	public static void wind() {
		Pair p1 = air.get(0);
		Pair p2 = air.get(1);
		
		// 반시계방향 순환
		// 1. 위에서 아래로
		for(int i=p1.x-1; i>0; i--) {
			map[i][0] = map[i-1][0];
		}
		// 2. 오른쪽에서 왼쪽으로
		for(int i=0; i<C-1; i++) {
			map[0][i] = map[0][i+1];
		}
		// 3. 아래서 위로
		for(int i=0; i<p1.x; i++) {
			map[i][C-1] = map[i+1][C-1];
		}
		// 4. 왼쪽에서 오른쪽으로
		for(int i=C-1; i>p1.y+1; i--) {
			map[p1.x][i] = map[p1.x][i-1];
		}
		map[p1.x][p1.y+1] = 0;
		
		// 시계방향 순환
		// 1. 아래에서 위로
		for(int i=p2.x+1; i<R-1; i++) {
			map[i][0] = map[i+1][0]; 
		}
		// 2. 오른쪽에서 왼쪽으로
		for(int i=0; i<C-1; i++) {
			map[R-1][i] = map[R-1][i+1];
		}
		// 3. 위에서 아래로
		for(int i=R-1; i>p2.x; i--) {
			map[i][C-1] = map[i-1][C-1];
		}
		// 4. 왼쪽에서 오른쪽으로
		for(int i=C-1; i>p2.y+1; i--) {
			map[p2.x][i] = map[p2.x][i-1];
		}
		map[p2.x][p2.y+1] = 0;
	}

	public static void spread() {

		while (!queue.isEmpty()) {
			Pair p = queue.poll();
			int dust = p.value / 5;

			int cnt = 0;

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dir[i][0];
				int ny = p.y + dir[i][1];

				if (isInside(nx, ny) && map[nx][ny] != -1) {
					map[nx][ny] += dust;
					cnt++;
				}
			}
			map[p.x][p.y] -= (dust * cnt);
			
		}
	}

	public static boolean isInside(int x, int y) {
		if (x >= 0 && y >= 0 && x < R && y < C)
			return true;
		else
			return false;
	}

	public static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

class Pair {
	int x;
	int y;
	int value; 
	
	Pair(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
}
