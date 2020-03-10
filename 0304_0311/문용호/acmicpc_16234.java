package acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class country{
	int pop;//인구수
	int up;
	int down;
	int left;
	int right;
	int x;
	int y;
	public country(int pop,int up,int down, int left, int right, int x,int y) {
		this.pop = pop;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.x = x;
		this.y = y;
	}
}

public class acmicpc_16234 {
	static country [][] map;
	static int N,L,R;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new country [N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = new country(Integer.parseInt(st.nextToken()),0,0,0,0,i,j);
			}
		}
		//인구이동이 없을때까지 지속
		while (true) {
			//= 모든 나라의 인구차이가 L보다 작고, R보다 크면 더이상 인구이동을 하지 않음
			if (getPopDiff()) {
				if (ans == 0) {
					System.out.println(0);
					break;
				} else {
					System.out.println(ans);
					break;					
				}
			} else {
				//연합 찾기
				findGuild();
				ans++;
			}
		}
	}//end main
	private static void findGuild() {
		ArrayList<country> guild = new ArrayList<country>();
		ArrayList<country> list = new ArrayList<country>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				boolean [][] visited = new boolean[N][N];
				list.add(map[i][j]);
				guild.add(map[i][j]);
				visited[i][j] = true;
				while (!list.isEmpty()) {
					country c = list.remove(0);
					int x = c.x;
					int y = c.y;
//					if (visited[x][y]) {
//						guild.remove(c);
//						continue;
//					}
//					System.out.println("x : "+x+" y : "+y+" c.up : "+c.up+" c.down : "+c.down+" c.left : "+c.left+" c.right : "+c.right);
					if (c.right == 1 && !visited[x][y+1]) {
						list.add(map[x][y+1]);
						guild.add(map[x][y+1]);
						visited[x][y+1] = true;
						map[x][y].right = 0;
						map[x][y+1].left = 0;
					}
					if (c.down == 1 && !visited[x+1][y]) {
						list.add(map[x+1][y]);
						guild.add(map[x+1][y]);
						visited[x+1][y] = true;
						map[x][y].down = 0;
						map[x+1][y].up = 0;
					}
					if (c.left == 1 && !visited[x][y-1]) {
						list.add(map[x][y-1]);
						guild.add(map[x][y-1]);
						visited[x][y-1] = true;
						map[x][y].left = 0;
						map[x][y-1].right = 0;
					}
					if (c.up == 1 && !visited[x-1][y]) {
						list.add(map[x-1][y]);
						guild.add(map[x-1][y]);
						visited[x-1][y] = true;
						map[x][y].up = 0;
						map[x-1][y].down = 0;
					}
				}
				int size = guild.size();
				if (size == 1) {
					guild.clear();
					list.clear();
					continue;
				}
				int sum = 0;
				for (country c : guild) {
					sum+=c.pop;
				}
				sum /= size;
				for (country c : guild) {
					map[c.x][c.y].pop = sum;
				}
				guild.clear();
				list.clear();
				
			}
		}
//		for (int k = 0; k < N; k++) {
//			for (int l = 0; l < N; l++) {
//				System.out.print(map[k][l].pop+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
	}//end findGuild
	private static boolean getPopDiff() {//인구수 차이를 구하고 국경선을 염
		//map 출력
		boolean [][] compared = new boolean[N][N];
		boolean flag = true;
		int [] dx = {0,1,0,-1};
		int [] dy = {1,0,-1,0};
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 4; k++) {
					int nx = i+dx[k];
					int ny = j+dy[k];
					if (nx <0 || nx >= N || ny < 0 || ny >= N) continue;
					if (compared[nx][ny]) continue;
					int diff = Math.abs(map[i][j].pop-map[nx][ny].pop);
					if (diff >= L && diff <= R) {//차이가 L이상 R이하
//						System.out.println("i : "+i+" j : "+j+" nx : "+nx+" ny : "+ny+" k : "+k);
						flag = false;
						if (k == 0) {//우
							map[i][j].right = 1;
							map[nx][ny].left = 1;
						} else if (k== 1) {//하
							map[i][j].down = 1;
							map[nx][ny].up = 1;
						} else if (k== 2) {//좌
							map[i][j].left = 1;
							map[nx][ny].right = 1;
						} else {//상
							map[i][j].up = 1;
							map[nx][ny].down = 1;
						}
					}
				}//사방탐색 끝
				compared[i][j] = true;
			}
		}
		return flag;
	}
}//end class
