package codingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 주사위굴리기_시뮬 {
	static int bottom, top, up, down, left, right;
	static int map[][];
	static int N, M, K; //세로, 가로, 명령 개수
 	static int sx, sy; // 처음 시작 x, y
	static int order[];
	static int dx[]= {0,1,-1,0,0}; //동서북남
	static int dy[]= {0,0,0,-1,1}; //동서북남
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		sx = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		order = new int[K];
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < order.length; i++) {
			order[i]=Integer.parseInt(st.nextToken());
		}
		//System.out.println(sy+" "+sx);
		//System.out.println(top + " "+ bottom +" "+ up+" "+down+" "+left+" "+right);
		for (int i = 0; i < order.length; i++) {
			//order대로 굴린다.
			move(order[i]);
			//System.out.println(sy+" "+sx);
			//System.out.println(top + " "+ bottom +" "+ up+" "+down+" "+left+" "+right);
		
		}

	}
	public static void move(int order) {
		int ny = sy + dy[order];
		int nx = sx + dx[order];
		//옮길 때 맵 밖이면 명령 무시한다.현위치 sy, sx 도 바꾼다.
		if(ny<0 || nx<0 || ny>=N || nx>=M) return;
		sy= ny;
		sx= nx;
		//order가 1, 2, 3, 4 에 따라서 굴린다.
		if(order ==1) {// 동쪽으로 굴린다.
			//굴릴 때는 주사위 변수들 모두 바꾼다.
			//동쪽 up, down 고정에 나머지 네개 교체
			int temp = left;
			left = bottom;
			bottom = right;
			right = top;
			top = temp;
			//옮겼으면 맞닿은 바닥끼리 값을 보고 해야할 일을 수행한다. bottom 과 map[sy][sx] 비교
			if(map[sy][sx]==0) {
				map[sy][sx]=bottom;
			}else {
				bottom= map[sy][sx];
				map[sy][sx]=0;
			}
		}else if(order==2) {//서쪽
			int temp = bottom;
			bottom = left;
			left = top;
			top = right;
			right = temp;
			if(map[sy][sx]==0) {
				map[sy][sx]=bottom;
			}else {
				bottom= map[sy][sx];
				map[sy][sx]=0;
			}
		}else if(order==3) {
			int temp = up;
			up = top;
			top = down;
			down = bottom;
			bottom = temp;
			if(map[sy][sx]==0) {
				map[sy][sx]=bottom;
			}else {
				bottom= map[sy][sx];
				map[sy][sx]=0;
			}
		}else {
			int temp = down;
			down = top;
			top = up;
			up = bottom;
			bottom = temp;
			if(map[sy][sx]==0) {
				map[sy][sx]=bottom;
			}else {
				bottom= map[sy][sx];
				map[sy][sx]=0;
			}
		}
		System.out.println(top);
		
	}
}
