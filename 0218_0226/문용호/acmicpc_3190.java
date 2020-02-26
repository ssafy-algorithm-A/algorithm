package acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class time{
	int t;
	String dir;
	public time(int t,String dir) {
		this.t = t;
		this.dir = dir;
	}
}

class snake{
	int x;
	int y;
	public snake(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class acmicpc_3190 {
	static int [][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(),"");
				
		int N = Integer.parseInt(st.nextToken());//보드의 크기
		map = new int [N][N];
		map[0][0] = 2;
		st = new StringTokenizer(br.readLine(),"");
		int K = Integer.parseInt(st.nextToken());//사과의 위치
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a-1][b-1] = 1;
		}// 사과의 위치 입력
		st = new StringTokenizer(br.readLine(),"");
		int L = Integer.parseInt(st.nextToken());//뱀의 방향 변환 횟수
		time [] timeArr = new time[L];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			String d= st.nextToken();
			timeArr[i] = new time(a,d);
		}//뱀의 방향 변환 정보 입력
		int timeArrIdx = 0;
		String curDir = "R";
		int time = 0;
		int snake_head_x=0;
		int snake_head_y=0;
		Queue<snake> q = new LinkedList<snake>();
		q.add(new snake(0,0));
		snake s;
		//큐를 써서, 사과 먹으면 큐에 add만, 사과 안먹으면, 큐의 poll해서 poll한값의 x좌표 y좌표를 0으로 만들어줌
		while(true) {
			//time이 증가할때마다 뱀이 이동.
			time++;
			//break조건 : 뱀의 head가 2에 닿거나, 벽에 닿을 경우
//			System.out.println(snake_head_x+" "+snake_head_y+" "+curDir);
			if (curDir.equals("R")) {//오른쪽 보고있음
				//사과가 있으면, 사과 없어지고, 꼬리 움직이지 않음
				//사과가 없으면, 기존 꼬리위치의 값 0으로 바꿔줌
				snake_head_y++;
				if ( snake_head_x < 0 || snake_head_x >= N || snake_head_y <0 || snake_head_y >= N) break;
				if (map[snake_head_x][snake_head_y] == 2) break;
				if (map[snake_head_x][snake_head_y] == 1) {//사과 있으면?
					map[snake_head_x][snake_head_y] = 2;
					q.add(new snake(snake_head_x,snake_head_y));
				} else if (map[snake_head_x][snake_head_y] == 0) {
					s= q.poll();
					map[s.x][s.y] = 0;
					map[snake_head_x][snake_head_y] = 2;
					q.add(new snake(snake_head_x,snake_head_y));
				}
			} else if (curDir.equals("L")) {
				snake_head_y--;
				if ( snake_head_x < 0 || snake_head_x >= N || snake_head_y <0 || snake_head_y >= N) break;
				if (map[snake_head_x][snake_head_y] == 2) break;
				if (map[snake_head_x][snake_head_y] == 1) {//사과 있으면?
					map[snake_head_x][snake_head_y] = 2;					
					q.add(new snake(snake_head_x,snake_head_y));
				} else if (map[snake_head_x][snake_head_y] == 0) {
					s= q.poll();
					map[s.x][s.y] = 0;
					map[snake_head_x][snake_head_y] = 2;
					q.add(new snake(snake_head_x,snake_head_y));
				}
			} else if (curDir.equals("U")) {
				snake_head_x--;
				if (snake_head_x < 0 || snake_head_x >= N || snake_head_y <0 || snake_head_y >= N) break;
				if (map[snake_head_x][snake_head_y] == 2) break;
				if (map[snake_head_x][snake_head_y] == 1) {//사과 있으면?
					map[snake_head_x][snake_head_y] = 2;
					q.add(new snake(snake_head_x,snake_head_y));
				} else if (map[snake_head_x][snake_head_y] == 0) {
					s= q.poll();
					map[s.x][s.y] = 0;
					map[snake_head_x][snake_head_y] = 2;			
					q.add(new snake(snake_head_x,snake_head_y));
				}
			} else if (curDir.equals("D")) {
				snake_head_x++;
				if ( snake_head_x < 0 || snake_head_x >= N || snake_head_y <0 || snake_head_y >= N) break;
				if (map[snake_head_x][snake_head_y] == 2) break;
				if (map[snake_head_x][snake_head_y] == 1) {//사과 있으면?
					map[snake_head_x][snake_head_y] = 2;
					q.add(new snake(snake_head_x,snake_head_y));
				} else if (map[snake_head_x][snake_head_y] == 0) {
					s= q.poll();
					map[s.x][s.y] = 0;
					map[snake_head_x][snake_head_y] = 2;
					q.add(new snake(snake_head_x,snake_head_y));
				}
			}
			//time이 timeArrIdx의 time이랑 같아지면, 방향 바꿔줌
			//L 왼쪽, D 오른쪽
			if (time == timeArr[timeArrIdx].t) {
				if(timeArr[timeArrIdx].dir.equals("L")) {
					if (curDir.equals("R")) curDir = "U";
					else if (curDir.equals("U")) curDir = "L";
					else if (curDir.equals("L")) curDir = "D";
					else curDir = "R";
				} else {
					if (curDir.equals("R")) curDir = "D";
					else if (curDir.equals("D")) curDir = "L";
					else if (curDir.equals("L")) curDir = "U";
					else curDir = "R";
				}
				timeArrIdx++;
				if (timeArrIdx >= timeArr.length) timeArrIdx = timeArr.length-1;
			}
		}//end while
		System.out.println(time);
	}//end main
}//end class
