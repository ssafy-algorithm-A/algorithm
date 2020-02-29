package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N_14500 {
	public static int[][] dot = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int[][] arr;
	public static int result;
	public static int max;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				visited[i][j] = true;
				// ㅗ외의 경우 처리를 위해 두 점을 고른 후 dfs처리 (한 점 고르고 하면 시간초과남)
				for (int k = 0; k < dot.length; k++) {
					int dx = i + dot[k][0];
					int dy = j + dot[k][1];
					
					if(dx <0 || dy <0 || dx >= arr.length || dy >= arr[0].length) continue;
					
					visited[dx][dy] = true;
					dfs(visited, dx, dy, 2, arr[i][j]+arr[dx][dy]);
					visited[dx][dy] = false;
				}
	
				// ㅗ 모양 처리부분
				int count = 0;
				int min = Integer.MAX_VALUE;
				int sum = arr[i][j];
				for (int k = 0; k < dot.length; k++) {
					int dx = i + dot[k][0];
					int dy = j + dot[k][1];
					
					if(dx <0 || dy <0 || dx >= arr.length || dy >= arr[0].length) continue;
					
					count++;
					min = Math.min(arr[dx][dy], min);
					sum += arr[dx][dy];
				}
				if(count == 3) result = Math.max(result, sum);
				if(count == 4) {
					result = Math.max(result, sum-min);
				}
			}
		}
		System.out.println(result);
	}
	
	public static void dfs(boolean[][] visited,int x, int y,int count,int sum) {
		if(count >= 4) { // 종료조건
			result = Math.max(result, sum);
			return;
		}
		
		for (int i = 0; i < dot.length; i++) {
			int dx = x + dot[i][0];
			int dy = y + dot[i][1];
			
			if(dx <0 || dy <0 || dx >= arr.length || dy >= arr[0].length || visited[dx][dy]) continue;
			
			visited[dx][dy] = true;
			dfs(visited,dx,dy,count+1, sum+arr[dx][dy]);
			visited[dx][dy] = false;
		}
	}

}
