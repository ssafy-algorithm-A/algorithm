package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 파이프옮기기1 {
		static int N;
		static int map[][];
		static int count;
		static int dx[]= {0,0,-1,1};
		static int dy[] = {-1,1,0,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		count =0;
		for(int i=0 ; i<N; i++) {
			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,1,0);
		System.out.println(count);
		
	}
	
	public static void dfs(int y, int x, int position) {//position 0: 가로, 1:세로, 2: 대각선

		if(y==N-1 &&x==N-1) {
			count++;
			return;
		}
		
		
		switch(position) {
			case 0:
				if(nextPo1(y,x)) {//가로 방향 오케이
					dfs(y,x+1,0);
				}
				if(nextPo1(y,x) && nextPo2(y,x) &&nextPo3(y,x)) {//대각선 방향 오케이
					dfs(y+1, x+1, 2);
				}
				break;
			case 1 :
				if(nextPo2(y,x)) {//세로
					dfs(y+1,x,1);
				}
				if(nextPo1(y,x) && nextPo2(y,x) &&nextPo3(y,x)) {
					dfs(y+1, x+1, 2);
				}
				break;
			case 2:
				if(nextPo1(y,x)) {
					dfs(y,x+1,0);
				}
				if(nextPo2(y,x)) {
					dfs(y+1,x,1);
				}
				if(nextPo1(y,x) && nextPo2(y,x) &&nextPo3(y,x)) {
					dfs(y+1, x+1, 2);
				}
				break;
		
		}
	}
	
	public static boolean nextPo1(int y, int x) { //오른쪽 이동.
		x++;
		if(y<0||x<0||x>=N||y>=N) return false;
		if(map[y][x]==1) return false;
		
		return true;
	}
	
	public static boolean nextPo2(int y, int x) { //세로 이동.
		y++;
		if(y<0||x<0||x>=N||y>=N) return false;
		if(map[y][x]==1) return false;
		
		return true;
	}
	
	public static boolean nextPo3(int y, int x) { //대각선 이동.
		x++;
		y++;
		if(y<0||x<0||x>=N||y>=N) return false;
		if(map[y][x]==1) return false;
		
		return true;
	}
	
	

}
