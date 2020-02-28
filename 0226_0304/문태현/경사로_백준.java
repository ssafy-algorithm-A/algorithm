package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 경사로 {
		static int N;
		static int L;
		static int[][] map;
		static int[][] path;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s= bf.readLine();
		StringTokenizer st;
		st = new StringTokenizer(s);
		N= Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		path = new int[2*N][N];
		
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < map.length; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for (int j = 0; j < N; j++) {
				path[i][j]=map[i][j];
			}
		}
		for (int i = N; i < 2*N; i++) {
			for (int j = 0; j < N; j++) {
				path[i][j] = map[j][i-N];
			}
		}
		int count=0;
		for(int i =0; i<2*N; i++) {
			if(checkpath(i)) {
				count++;
			}
		}
		System.out.println(count);
		
		
	}
	public static boolean checkpath(int i) {
		int[] visited = new int[N];
		//path[i] 길 배열
		for(int j=0; j< N-1; j++) {
			if(path[i][j]==path[i][j+1]) continue;
			if(Math.abs(path[i][j]-path[i][j+1])>1) return false;
			else if(path[i][j]>path[i][j+1]) {//앞으로경사로 필요.
				int index = j;
				for(int l =1; l<L+1; l++) { //앞으로 한칸. 두칸. L칸.
					if (index+l>=N) return false;
					if(visited[index+l]==1) return false;
					if(path[i][j+1]!=path[i][index+l]) return false;
				}// 경사로 놓을 수 있었음.
				for(int l=1; l<L+1;l++) {//놓는다.
					visited[index+l]=1;
				}
			}else if(path[i][j]<path[i][j+1]){//뒤로 경사로 필요
				int index =j;
				for(int l=0; l<L; l++) {
					if(index-l<0) return false;
					if(visited[index-l]==1) return false;
					if(path[i][j]!=path[i][index-l]) return false;
				}
				for(int l=0; l<L; l++) {
					visited[index-l]=1;
				}
			}
			
		}
		
		return true;
	}

}
