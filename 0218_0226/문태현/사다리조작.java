package codingtest;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.Arrays;

import java.util.StringTokenizer;

public class 사다리조작 {

	static int N;
	static int M;
	static int H;
	static int[][] sadari;
	static int min_res = 10000;
	static int flag =0;
	
	public static boolean check() {
		for (int i = 1; i < sadari[0].length+1; i++) {
			int x=i;
			int y=1;
			while(true) {
				if(x==1) {
					if(sadari[y][x]==1) {
						x++;
						y++;
					}else {
						y++;
					}
				}else if(x==N){
					if(sadari[y][x-1]==1) {
						x--;
						y++;
					}else {
						y++;
					}
				}else {
					if(sadari[y][x]==1) {
						x++;
						y++;
					}else if(sadari[y][x-1]==1) {
						x--;
						y++;
					}else {
						y++;
					}
				}
				if(y>=H+1) {
					if(x!=i) return false;
					break;
				}
			}
		}
		return true;
	}
	public static void dfs(int num, int count) {
		if(flag ==1) return;
		if(count== num) {
			if(check()) {
				min_res = count;
				flag =1;
			}
			return;
		}
		
		for (int i = 1; i < sadari.length; i++) {
			for (int j = 1; j < sadari[0].length; j++) {
				if(N==2) {
					if(sadari[i][j]==1) continue;
					sadari[i][j]=1;
					dfs(num,count+1);
					sadari[i][j]=0;
				}else {
					if(j==1) {
						if(sadari[i][j]==1 || sadari[i][j+1]==1) continue;
						sadari[i][j]=1;
						dfs(num, count+1);
						sadari[i][j]=0;
					}
					else if(j==sadari[0].length-1) {
						if(sadari[i][j]==1 || sadari[i][j-1]==1) continue;
						sadari[i][j]=1;
						dfs(num, count+1);
						sadari[i][j]=0;
					}else {
						if(sadari[i][j]==1 || sadari[i][j+1]==1 || sadari[i][j-1]==1)continue;
						sadari[i][j]=1;
						dfs(num, count+1);
						sadari[i][j]=0;
					}
				}
					
			}
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		sadari = new int[H + 1][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			sadari[y][x] = 1;
		}
		
		for (int i = 0; i < 4; i++) {
			dfs(i, 0);
		}
		if(min_res ==10000) System.out.println(-1);
		else System.out.println(min_res);
	}

}