package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class bac3190_뱀_S1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N;
	static int K;
	static int L;
	
	static int arr[][];
	static int time[];	

	
	//상 우 하 좌
	static int dx[] = {0,0,1,0,-1};
	static int dy[] = {0,-1,0,1,0};
	
	
	//K 사과개수
	//K개의 입력
	//L 이동 횟수
	//변환
	
	//문제좀잘읽자!!
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];
		time = new int[10001];
		
		K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i<K; i++) {
			st  = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] =2;
		}
		
		L = Integer.parseInt(br.readLine());
		for(int i = 0; i<L; i++) {
			st  = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int op = 0;
			switch(st.nextToken()) {
			case "D":
				op =1;
				break;
			case "L":
				op =-1;
				break;
			}
			time[idx] = op;
		}
		
		
		int d = 2;
		int op = 0;
		int answer = 0;
		int cur_x = 1;
		int cur_y = 1;
		int new_x;
		int new_y;
		
		Queue<Point> Q = new LinkedList<>();
		Q.add(new Point(cur_x, cur_y));
		
		//상 우 하 좌
		while(true) {
			
			//d가 오른쪽
			if(time[answer] != 0) {
				op = time[answer];			
				d += op;
				if(d == 0)
					d = 4;
				if(d == 5)
					d = 1;
			}
			
			new_x = cur_x + dy[d];
			new_y = cur_y + dx[d];
			
			//1)벽이거나
			if((new_x <1 || new_x>N || new_y<1 || new_y>N)) {
				//System.out.println("벽");
				break;
			}
			//꼬리면
			if(Q.contains(new Point(new_x, new_y))) {
				//System.out.println("꼬리");
				break;
			}
			//2)사과면
			if(arr[new_x][new_y] == 2) {
				//System.out.println("사과");
				arr[new_x][new_y] = 0;
				Q.offer(new Point(new_x, new_y));			
			}
			else {
				//System.out.println("그냥");
				Q.offer(new Point(new_x, new_y));
				Q.poll();
			}
			/*
			for(Point p : Q) {
				System.out.print(p.x + " - "+ p.y + " - " + d + " - " + " | ");
			}
			*/
			cur_x = new_x;
			cur_y = new_y;	
			answer++;
		}
		
		System.out.println(answer+1);
		
	}
	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return (x +"," +y).hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Point) {
				Point tmp = (Point) obj;
				return (x == tmp.x) && (y == tmp.y);
			}
			return false;
		}
		
		
	}

}


