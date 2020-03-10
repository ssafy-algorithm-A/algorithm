package codingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class country{
	int y;
	int x;
	public country(int y, int x) {
		this.y=y;
		this.x=x;
	}
}
public class 인구이동_dfs {
	static int dx[]= {0,0, -1,1};
	static int dy[]= {-1,1,0,0};
	static int N;
	static int L;
	static int R;
	static int map[][];
	static int visited[][];
	static Queue<country> q= new LinkedList();
	static int totalcnt;
	static int cnt;
	static int sum;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new int[N][N];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < map.length; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		/*for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}*/
		int res=0;
		while(true) {
			for (int i = 0; i < visited.length; i++) {
				Arrays.fill(visited[i], 0);
			}
			totalcnt=0; //연합이 생기면 올라갈거임
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					if(visited[i][j]==0) {
						cnt =0;
						sum=0;
						q.clear();
						dfs(i,j); //방문 안한 위치에 대해 그 위치의 연합 찾음.
						//cnt, sum 이 나올 거고 q 에 그 위치들 다 있음.
						//이용해서 맵을 갱신.
						int val = sum/cnt;
						int size = q.size();
						for (int k = 0; k < size; k++) {
							country c = q.poll();
							map[c.y][c.x]=val;
						}
						
					}
				}
			}//맵 한바퀴 갱신.
			
			if(totalcnt==0) break; //연합한번도 안생김
			res++;
		}
		System.out.println(res);
	}
	
	public static void dfs(int y, int x) {
		cnt++;
		sum+= map[y][x];
		visited[y][x]=1;
		q.add(new country(y,x));
		for (int i = 0; i < dx.length; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			if(ny<0||nx<0||ny>=N||nx>=N) continue;
			if(visited[ny][nx]==1) continue;
			if(Iscompany(y,x,ny,nx)) {
				totalcnt ++;
				dfs(ny,nx);
			}
			
		}
	}
	
	public static boolean Iscompany(int y, int x, int ny, int nx) {
		
		int pivot=Math.abs(map[y][x]-map[ny][nx]);
		if(pivot>=L && pivot<=R) {
			return true;
		}else return false;
	}

}
