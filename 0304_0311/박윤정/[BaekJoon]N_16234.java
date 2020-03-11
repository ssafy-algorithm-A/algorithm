package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N_16234 {
	public static int[][] arr;	
	public static int[][] dot = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int[][] union;
	public static int L,R,total;
	public static Queue<DOT> people;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		people = new LinkedList<>();
		int answer = 0;
		while(true) {
			union = new int[N][N];
			int union_num = 1;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if(union[i][j] == 0) {
						total = 0; // 총 인구수
						go(i, j, union_num);
						union_num++;
						if(people.size() <= 1) {
							people = new LinkedList<>();
							continue;
						}
						int people_num = total/people.size();
						while(!people.isEmpty()) { // 인구수 값 변경
							DOT d = people.poll();
							arr[d.x][d.y] = people_num;
						}
					}
				}
			}

			if(union_num-1 == N*N) break; // 국가수가 총 이차원배열 수와 같으면 인구 이동 없으므로 빠져나가기
			answer++;
		}
		System.out.println(answer);
		
	}
	
	public static void go(int x, int y,int union_num) {
		people.add(new DOT(x,y));
		union[x][y] = union_num;
		total += arr[x][y];
		for (int i = 0; i < dot.length; i++) {
			int dx = x + dot[i][0];
			int dy = y + dot[i][1];
			
			if(dx <0 || dy<0 || dx >=arr.length || dy>=arr[0].length || union[dx][dy] != 0
					|| Math.abs(arr[dx][dy] - arr[x][y]) < L || Math.abs(arr[dx][dy] - arr[x][y]) > R) continue;
			
			go(dx, dy, union_num);
		}
	}
	
	public static class DOT{
		int x,y;
		
		public DOT(int x,int y) {
			this.x= x;
			this.y =y;
		}
	}
}
