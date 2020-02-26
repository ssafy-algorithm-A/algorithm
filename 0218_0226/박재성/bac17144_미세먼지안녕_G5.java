package main.java.Temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bac17144_미세먼지안녕_G5 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int R, C, T;
	static int arr[][];
	static int count_arr[][];
	static Queue<Dust> Q = new LinkedList<>();
	static Dust Stone = new Dust(-1,-1,-1);
	static int answer = 0;

	static int Gong_i;
	static int Gong_j;
	static int length;


	//상하좌우
	static int dx[] = {0,0,0,-1,1};
	static int dy[] = {0,-1,1,0,0};


	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()){
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
		}

		arr = new int[R+1][C+1];
		count_arr = new int[R+1][C+1];
		int idx = 0;

		for(int i=1; i<=R; i++){
			st = new StringTokenizer(br.readLine());
			int col = 1;
			while(st.hasMoreTokens()){

				int count = Integer.parseInt(st.nextToken());
				length = 1;
				if(count == -1){
					length++;
					Gong_i = i;
					Gong_j = col;
				}
				Q.add(new Dust(i,col,count));
				col++;
			}
		}

		solution();

	}

	private static void result() {
		for(int i =1; i<=R; i++){
			for(int j =1; j<=C; j++){
				if(count_arr[i][j] != -1 && count_arr[i][j] != 0)
					answer += count_arr[i][j];
			}
		}
		System.out.println(answer);
	}

	public static void solution(){
		int time = 0;
		Q.add(Stone);
		while(!Q.isEmpty()){
			//큐를 다시 채워주는 과정
			if(Q.peek().equals(Stone)){
				Q.poll();

				//
				//pprint();
				move();
				for(int i= Gong_i-length ; i<=Gong_i; i++)
					count_arr[i][Gong_j] = -1;
				//pprint();

				//
				makeQ();
				Q.add(Stone);


				//시간이 되면 끝내기
				time++;
				if(time == T){
					result();
					break;
				}

				//초기화
				for(int r[] : count_arr){
					Arrays.fill(r, 0);
				}
				for(int i= Gong_i-length ; i<=Gong_i; i++)
					count_arr[i][Gong_j] = -1;

			}
			Dust tmp = Q.poll();
			int current_cnt = tmp.count;
			int dis_cnt = current_cnt/5;
			for(int i =1; i<=4; i++){
				int new_x = tmp.x + dy[i];
				int new_y = tmp.y + dx[i];
				//범위 안.
				if(new_x >=1 && new_y >=1 && new_x<=R && new_y <=C){
					//환풍기가 아니면
					if(count_arr[new_x][new_y] != -1 ){
						count_arr[new_x][new_y] += dis_cnt;
						current_cnt -= dis_cnt;
					}
				}
			}
			count_arr[tmp.x][tmp.y] += current_cnt;


		}

	}

	private static void makeQ() {
		for(int i=1; i<=R; i++){
			for(int j=1; j<=C; j++){
				if(count_arr[i][j] != -1 && count_arr[i][j] != 0){
					Q.add(new Dust(i,j,count_arr[i][j]));
				}
			}
		}
	}

	//노가다
	public static void move(){
		int x;
		int y;
		//상 우 하 좌 | 1 4 2 3
		int arr[] = {0,1,4,2,3};
		x=Gong_i-(length)-1;//4 - 2
		y=Gong_j;//1
		for(int i=1; i<=4; i++){
			int new_x = x+dy[arr[i]];
			int new_y = y+dx[arr[i]];
			if(!(new_x>=1 && new_y>=1 && new_x<=R && new_y<=C) || (new_x == Gong_i-(length)+1 && new_y == C))
				continue;
			else {
			//	System.out.println(x + "," + y );
				if( count_arr[new_x][new_y] != -1)
					count_arr[x][y] = count_arr[new_x][new_y];
				else
					count_arr[x][y] = 0;
				x = new_x;
				y = new_y;
				i--;
			}
		}


		//하 우 상 좌 | 2 4 1 3
		int arr2[] = {0,2,4,1,3};
		x=Gong_i+1; // 4 +1
		y=Gong_j; // 1
		for(int i=1; i<=4; i++){
			int new_x = x+dy[arr2[i]];
			int new_y = y+dx[arr2[i]];
			if(!(new_x>=1 && new_y>=1 && new_x<=R && new_y<=C) || (new_x == Gong_i-1 && new_y == C)) {
				continue;
			}
			else {
				if( count_arr[new_x][new_y] != -1)
					count_arr[x][y] = count_arr[new_x][new_y];
				else
					count_arr[x][y] = 0;
					x = new_x;
					y = new_y;
					i--;

			}
		}
	}

	public static class Dust{
		int x;
		int y;
		int count;

		public Dust(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}

		@Override
		public boolean equals(Object o) {
			if(o instanceof Dust){
				Dust tmp = (Dust) o;
				return(x==tmp.x) && (y==tmp.y) && (count==tmp.count);
			}
			return false;
		}

		@Override
		public int hashCode() {
			return (x+","+y+","+count).hashCode();
		}
	}


}
