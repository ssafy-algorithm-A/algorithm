import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class acmicpc_17070 {
	static int [][] map;
	static int [] dx = {0,1,1};
	static int [] dy = {1,0,1};//가로 : 0, 세로 ; 1, 대각선 : 2
	static int answer = 0;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int [N][N];
		for (int i = 0; i < map.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int idx = 0;
			while (st.hasMoreTokens()) {
				map[i][idx] = Integer.parseInt(st.nextToken());
				idx++;
			}
		}
		//시작은 0행 1열에서 시작 방향성을 줘야됨
		doDFS(0,1,0);
		System.out.println(answer);
		
		
		
		
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		} map 출력
	}//end main
	public static void doDFS(int x, int y, int dir) {
		System.out.println(x+" "+y+" "+dir);
		if (x == N-1 && y == N-1) {
			answer++;
			return;
		}
		//가로 0 / 세로 1 / 대각선 2
		if (dir==0) {
			for (int i = 0; i < dx.length; i++) {
				if (i == 1) {//세로
					continue;
				}
				int nx = x+dx[i];
				int ny = y+dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (!checker(x, y, i)) continue;
				doDFS(nx,ny,i);
			}
		}
		else if (dir==1) {
			for (int i = 1; i < dx.length; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (!checker(x, y, i)) continue;
				doDFS(nx,ny,i);
			}
		}
		else if (dir ==2 ) {
			for (int i = 0; i < dx.length; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (!checker(x, y, i)) continue;
				doDFS(nx,ny,i);
			}
		}
	}//end doDFS
	public static boolean checker(int x, int y, int dir) {
		if(dir == 0) { //가로 
			if(map[x][y+1] == 0) {
				return true;
			}
		}
		if(dir == 1) { //세로 
			if(map[x+1][y] == 0) {
				return true;
			}
		}
		if(dir == 2) { // 가로 세로 대각선 이 전부 0 이면 
			if( map[x][y+1] == 0 && map[x+1][y] == 0 && map[x+1][y+1] == 0) {
				return true;
			}
		}
		return false;
	}
	
}//end class
