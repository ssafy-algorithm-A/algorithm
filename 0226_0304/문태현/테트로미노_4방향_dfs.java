/**
 * 1) 처음에 dfs 로 하면 되겟다고 생각했으나 ㅗㅜㅏㅓ 가 안됨을 알았음, 고민하다가 따로 처리하기로 함.
 * 2) 상하좌우 하면 중복이 생길것 같다고 생각했음, 증명은 못하겠는데 4방향 중 아무거나 한방향은 빼도 반례가 없이 돌아가는 것 같음. 중복 몇개를 제거할 수 있음.
 * 
 */

package codingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 테트로미노_4방향_dfs {
	static int map[][];
	static int visited[][];
	static int N, M;
	static int dx[]= {0,0,-1,1};
	static int dy[]= {-1,1,0,0};
	static int max_res = -1;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N= Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < map[0].length; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				visited[i][j]=1;
				dfs(i,j,0, map[i][j]);
				visited[i][j]=0;
				cal_rest(i,j);
			}
		}
		System.out.println(max_res);
		
	}
	public static void cal_rest(int y, int x) {
		// ㅜ
		if(x-1>=0 && x+1<M && y+1<N) {
			int res= map[y][x]+map[y][x-1]+map[y][x+1]+map[y+1][x];
			max_res = Math.max(max_res, res);
		}
		//ㅗ
		if(x-1>=0 && x+1<M && y-1>=0) {
			int res= map[y][x]+map[y][x-1]+map[y][x+1]+map[y-1][x];
			max_res = Math.max(max_res, res);
		}
		//ㅓ
		if(x-1>=0 && y-1>=0 && y+1<N) {
			int res= map[y][x]+map[y][x-1]+map[y+1][x]+map[y-1][x];
			max_res = Math.max(max_res, res);
		}
		//k
		if(x+1<M && y-1>=0 && y+1<N) {
			int res= map[y][x]+map[y][x+1]+map[y+1][x]+map[y-1][x];
			max_res = Math.max(max_res, res);
		}
	}
	public static void dfs(int y, int x, int dep, int res) {
		if(dep==3) {
			max_res = Math.max(res, max_res);
			return;
		}
		for (int i = 0; i < 4; i++) {
			//for(int i=1; i<4;i++) , for(int i=0; i<3; i++) 다 돌아감
			int ny = y+dy[i];
			int nx = x+dx[i];
			if(ny<0||nx<0|| nx>=M||ny>=N) continue;
			if(visited[ny][nx]==1) continue;
			visited[ny][nx]=1;
			dfs(ny,nx, dep+1, res+map[ny][nx]);
			visited[ny][nx]=0;
		}
	}

}
