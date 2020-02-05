package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bac14502 {
	static Scanner sc = new Scanner(System.in);
	
	static ArrayList<Point> AL = new ArrayList<>();
	static Queue<Point> Qu = new LinkedList<>();
	static int N;
	static int M;
	static int Op=0;

	static int answer = 0;
	static boolean[][] visited;
	static int[][] arr;
	

	//상 하 좌 우
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};


	public static void main(String[] args) {

		//0 빈칸, 1 벽, 2 바이러스
		// 2<= 바이러스 <=10
		// 빈칸 >=3
		// 꼭 벽을 3개 새워야함.
		for (int t = 0; t < 1; t++) {
			
			//입력
			N = sc.nextInt();
			M = sc.nextInt();
			
			//초기화

			AL.clear();
			Qu.clear();
			visited = new boolean[N][M];
			arr = new int[N][M];
			make_init();
			//pprint();
			
			solution();

			System.out.println(answer);
		}

	}
	
	
	public static void make_init() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] != 0) {
					visited[i][j] = true;
				}
				if(arr[i][j] == 2)
					AL.add(new Point(i,j));
			}
		}		
	}
	
	public static int calcul_space() {
		int result = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 0)
					result++;
			}
		}
		return result;
	}
	
	public static void pprint() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	
	public static void solution() {
		DFS(0);
	}
	
	public static void DFS(int count) {
		if(count ==3) {
			Qu.clear();
			BFS();
			answer = Math.max(calcul_space(), answer);
			//pprint();
			reset();
			return;
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				
				if(!visited[i][j]) {
					visited[i][j] = true;
					arr[i][j] = 1;
					DFS(count +1);
					visited[i][j] = false;
					arr[i][j] = 0;
				}
					
			}
		}
		
		
	}
	
	public static void BFS() {
		for(Point p : AL) {
			int cur_x = p.x;
			int cur_y = p.y;
			search(cur_x, cur_y);					
		}
	}
	
	
	public static void search(int cur_x, int cur_y) {
		
		//4방향 검색
		for(int i=0; i<4; i++) {
			int new_x = cur_x + dy[i];
			int new_y = cur_y + dx[i];
			
			//범위안이면
			if((new_x >=0 && new_x <N) &&
					(new_y >=0 && new_y <M)) {			
				//방문안한곳이면!
				if(!visited[new_x][new_y]) {
					visited[new_x][new_y] = true;
					arr[new_x][new_y] = 2;
					Qu.add(new Point(new_x,new_y));
					search(new_x, new_y);
				}				
			}		
			
		}

	}
	
	public static void reset() {
		while(!Qu.isEmpty()) {
			int x = Qu.peek().x;
			int y = Qu.poll().y;
			visited[x][y] = false;
			arr[x][y] = 0;
		}
	}

}

class Point{
	int x;
	int y;

	Point(int x,int y) {
		this.x = x;
		this.y = y;
	}
}