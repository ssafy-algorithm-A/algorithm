package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N_17144 {
	public static int[][] dot = {{0,1},{-1,0},{0,-1},{1,0}}; // 우상좌하
	public static int[][] dot2 = {{0,1},{1,0},{0,-1},{-1,0}}; // 우하좌상
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		DOT[] cleaner = new DOT[2];
		Queue<DOT> queue = new LinkedList<>();
		int cleaner_index = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < C; j++) {
				int value = Integer.parseInt(st.nextToken());
				if(value > 0) {
					queue.add(new DOT(i,j,value));
				}else if(value == -1) {
					cleaner[cleaner_index++] = new DOT(i,j,value);
				}
			}
		}
		
		int answer = 0;
		int[][] temp = new int[R][C];
		while(true) {
			int[][] arr = new int[R][C];
			arr[cleaner[0].x][cleaner[0].y] = -1;
			arr[cleaner[1].x][cleaner[1].y] = -1;
			while(!queue.isEmpty()) {
				int count = 0;
				DOT d = queue.poll();
				int spread = d.num/5;
				if(spread != 0) {
					for (int i = 0; i < dot.length; i++) {
						int dx = d.x + dot[i][0];
						int dy = d.y + dot[i][1];
						
						if(dx < 0 || dy < 0 || dx >= arr.length || dy >= arr[0].length || arr[dx][dy] == -1) continue;
						
						count++;
						arr[dx][dy] += spread;
					}		
				}
				int na_dust = d.num - spread * count;
				arr[d.x][d.y] += na_dust; 
			}
			
			for (int i = 0; i < arr.length; i++) {
				System.arraycopy(arr[i], 0, temp[i], 0, arr[i].length);
			}

			for (int i = 0; i < cleaner.length; i++) {
				int dir = 0;
				int px = cleaner[i].x + dot2[dir][0];
				int py = cleaner[i].y + dot2[dir][1];
				temp[px][py] = 0;
				if(i == 0) {
					while(true) {
						int dx = px + dot[dir][0];
						int dy = py + dot[dir][1];
						
						if(dx == cleaner[i].x && dy == cleaner[i].y) break;
						if(dx < 0 || dy < 0 || dx >= arr.length || dy>= arr[0].length) {
							dir+=1; // 방향 바꾸기
							continue;
						}
						
						temp[dx][dy] = arr[px][py];
						px = dx;
						py = dy;
					}
				}else {
					while(true) {
						int dx = px + dot2[dir][0];
						int dy = py + dot2[dir][1];
						
						if(dx == cleaner[i].x && dy == cleaner[i].y) break;
						if(dx < 0 || dy < 0 || dx >= arr.length || dy>= arr[0].length) {
							dir+=1; // 방향 바꾸기
							continue;
						}
						
						temp[dx][dy] = arr[px][py];
						px = dx;
						py = dy;
					}
				}
			}
			
			T--; // 시간 감소
			
			if(T == 0) {
				for (int i = 0; i < arr.length; i++) {
					for (int j = 0; j < arr[0].length; j++) {
						answer += temp[i][j];
					}
				}	
				break;
			}else {
				for (int i = 0; i < arr.length; i++) {
					for (int j = 0; j < arr[0].length; j++) {
						if(temp[i][j] > 0) {
							queue.add(new DOT(i,j,temp[i][j]));
						}
					}
				}				
			}

		}
		System.out.println(answer+2);
		
	}
	
	public static class DOT{
		int x,y,num;
		
		public DOT(int x,int y,int num) {
			this.x= x;
			this.y=y;
			this.num = num;
		}
	}
}
