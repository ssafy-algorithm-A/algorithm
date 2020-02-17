package Baekjoon;

import java.util.Scanner;

public class N_17070 {
	public static int[][] dot = {{0,1},{1,0},{1,1}}; // 가로 세로 대각선
	public static int[][] arr;
	public static int count;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		arr = new int[N+1][N+1];
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[i].length; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		count = 0;
		arr[1][1] = 1;
		arr[1][2] = 1;
		go(1,2,0);
		System.out.println(count);
	}
	
	public static void go(int ex, int ey,int dir) {
		if(ex == arr.length-1 && ey == arr.length-1) {
			count++;
			return;
		}
		if(ex >= arr.length || ey >= arr.length) {
			return;
		}
		
		for (int i = 0; i < dot.length; i++) {
			if((dir == 0 && i == 1) || (dir == 1 && i == 0)) {
				continue;
			}
			
			int dx = ex + dot[i][0];
			int dy = ey + dot[i][1];
			
			if(dx <= 0 || dy <= 0 || dx >= arr.length || dy >= arr.length || arr[dx][dy] != 0) {
				continue;
			}
			if(i== 2) { // 대각선 검사
				if(dx-1 <= 0 || dy-1 <= 0 || arr[dx-1][dy] != 0 || arr[dx][dy-1] != 0) {
					continue;
				}
			}

			arr[dx][dy] = 1;
			if(i== 2) { 
				arr[dx-1][dy] = 1;
				arr[dx][dy-1] = 1;
			}
			go(dx,dy,i);
			arr[dx][dy] = 0;
			if(i== 2) { 
				arr[dx-1][dy] = 0;
				arr[dx][dy-1] = 0;
			}
		}
	}
}
