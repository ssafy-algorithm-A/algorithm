package BaekJoon;

import java.util.Scanner;

public class N_17471 {
	static int[][] arr;
	static int[] count;
	static int fin = Integer.MAX_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		count = new int[N]; 
		for (int i = 0; i < count.length; i++) {
			count[i] = sc.nextInt();
		}
		arr= new int[N][N]; 
		for (int i = 0; i < N; i++) {
			int n = sc.nextInt();
			for (int j = 0; j < n; j++) {
				int num = sc.nextInt();
				arr[i][num-1] = 1;
				arr[num-1][i] = 1;
			}
		}
		
	
		int[] visited= new int[N];
		powerSet(visited,0);
		if(fin == Integer.MAX_VALUE) {
			fin = -1;
		}
		System.out.println(fin);			
	}
	
	public static void powerSet(int[] visited, int n) {
		if(n == visited.length-1) { 
			boolean flag = true;
			for (int i = 0; i < visited.length; i++) {
				boolean[] visit = new boolean[visited.length];
				if(visited[i] == 1) {
					check(1,i,visit,visited);
					for (int j = 0; j < visit.length; j++) {
						if(visited[j] == 1&& !visit[j]) flag =false;
					}
					break;
				}
			}
			
			if(flag) {
				for (int i = 0; i < visited.length; i++) {
					boolean[] visit = new boolean[visited.length];
					if(visited[i] == 0) {
						check(0,i,visit,visited);
						for (int j = 0; j < visit.length; j++) {
							if(visited[j] == 0&& !visit[j]) flag =false;
						}
						break;
					}
				}
			}
			
			if(flag) {
				int result1 =0;
				int result2=0;
				for (int i = 0; i < visited.length; i++) {
					if(visited[i] == 1) {
						result1 += count[i];
					}else {
						result2 += count[i];
					}
				}
				
				fin = Math.min(fin, Math.abs(result1-result2));
			}
			
			return;
		}
		
		visited[n] = 1;
		powerSet(visited,n+1);
		
		visited[n] = 0;
		powerSet(visited,n+1);
	}
	
	public static void check(int num,int index,boolean[] visit, int[] visited) {
		visit[index] = true;
		for (int i = 0; i < visit.length; i++) {
			if(arr[index][i] == 0 || visit[i] || visited[i] != num) continue; 
			check(num,i,visit,visited);
		}
	}
}