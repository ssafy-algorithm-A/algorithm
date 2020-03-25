package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N_1938 {
	public static char[][] arr;
	public static int[][] dot = {{-1,0},{0,1},{1,0},{0,-1},{-1,-1},{-1,1},{1,1},{1,-1}}; // 상하좌우
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		Queue<DOT> queue = new LinkedList<>(); // 첫점만
		DOT[] tree = new DOT[3];
		int index = 0;
		boolean[][][] visited = new boolean[3][N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i] = st.nextToken().toCharArray();
			for (int j = 0; j < N; j++) {
				if(arr[i][j] == 'B') {
					tree[index++] = new DOT(i,j,0,2);
				}
			}
		}
		// 1이 세로방향(하), 2이 가로방향(우)
		if(tree[0].x == tree[1].x) tree[0].dir = 1;
		
		int answer = Integer.MAX_VALUE;
		visited[tree[0].dir][tree[0].x][tree[0].y] = true;
		queue.add(tree[0]);
		while(!queue.isEmpty()) {
			DOT d = queue.poll();
			
			boolean result = true;
			for (int i = 0; i < 3; i++) {
				if(arr[d.x + (dot[d.dir][0]*i)][d.y + (dot[d.dir][1]*i)] != 'E') {
					result = false;
					break;
				}
			}
			if(result) { // 종료 체크
				answer = Math.min(d.cnt, answer); 
				break;
			}
			
			for (int i = 0; i < 4; i++) { // 4방향 체크
				boolean check = true;
				for (int j = 0; j < 3; j++) { // 배열 돌기
					int dx = d.x + (dot[d.dir][0]*j) + dot[i][0];
					int dy = d.y + (dot[d.dir][1]*j) + dot[i][1];
					
					if(dx < 0|| dy< 0|| dx>=arr.length || dy>=arr[0].length || arr[dx][dy] == '1') {
						check = false;
						break;
					}
				}
				
				if(check) {
					int dx = d.x + dot[i][0];
					int dy = d.y + dot[i][1];
					
					if(visited[d.dir][dx][dy]) continue;
					
					visited[d.dir][dx][dy] = true;
					queue.add(new DOT(dx,dy,d.cnt+1,d.dir));					
				}
			}
			
			if(checkRotate(d.x + (dot[d.dir][0]),d.y + (dot[d.dir][1]))) { // 회전 경우 체크
				if(d.dir == 1) { // 가로방향	
					if(visited[d.dir+1][d.x+dot[5][0]][d.y+dot[5][1]]) continue;
					visited[d.dir+1][d.x+dot[5][0]][d.y+dot[5][1]] = true;
					queue.add(new DOT(d.x+dot[5][0],d.y+dot[5][1],d.cnt+1,2));
				}else { // 세로 방향
					if(visited[d.dir-1][d.x+dot[7][0]][d.y+dot[7][1]]) continue;
					visited[d.dir-1][d.x+dot[7][0]][d.y+dot[7][1]] = true;
					queue.add(new DOT(d.x+dot[7][0],d.y+dot[7][1],d.cnt+1,1));
				}
				

			}
		}
		
		if(answer == Integer.MAX_VALUE) {
			System.out.println("0");
		}else {
			System.out.println(answer);			
		}
	}
	
	public static boolean checkRotate(int x,int y) {
		for (int i = 0; i < 8; i++) {
			int dx = x + dot[i][0];
			int dy = y + dot[i][1];
			
			if(dx < 0|| dy< 0|| dx>=arr.length || dy>=arr[0].length || arr[dx][dy] == '1') return false;
		}
		return true;
	}

	public static class DOT{
		int x,y,cnt,dir;
		
		public DOT(int x,int y,int cnt,int dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}
	}
}
