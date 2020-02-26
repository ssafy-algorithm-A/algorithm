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
				
		int N = Integer.parseInt(st.nextToken());//������ ũ��
		map = new int [N][N];
		map[0][0] = 2;
		st = new StringTokenizer(br.readLine(),"");
		int K = Integer.parseInt(st.nextToken());//����� ��ġ
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a-1][b-1] = 1;
		}// ����� ��ġ �Է�
		st = new StringTokenizer(br.readLine(),"");
		int L = Integer.parseInt(st.nextToken());//���� ���� ��ȯ Ƚ��
		time [] timeArr = new time[L];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			String d= st.nextToken();
			timeArr[i] = new time(a,d);
		}//���� ���� ��ȯ ���� �Է�
		int timeArrIdx = 0;
		String curDir = "R";
		int time = 0;
		int snake_head_x=0;
		int snake_head_y=0;
		Queue<snake> q = new LinkedList<snake>();
		q.add(new snake(0,0));
		snake s;
		//ť�� �Ἥ, ��� ������ ť�� add��, ��� �ȸ�����, ť�� poll�ؼ� poll�Ѱ��� x��ǥ y��ǥ�� 0���� �������
		while(true) {
			//time�� �����Ҷ����� ���� �̵�.
			time++;
			//break���� : ���� head�� 2�� ��ų�, ���� ���� ���
//			System.out.println(snake_head_x+" "+snake_head_y+" "+curDir);
			if (curDir.equals("R")) {//������ ��������
				//����� ������, ��� ��������, ���� �������� ����
				//����� ������, ���� ������ġ�� �� 0���� �ٲ���
				snake_head_y++;
				if ( snake_head_x < 0 || snake_head_x >= N || snake_head_y <0 || snake_head_y >= N) break;
				if (map[snake_head_x][snake_head_y] == 2) break;
				if (map[snake_head_x][snake_head_y] == 1) {//��� ������?
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
				if (map[snake_head_x][snake_head_y] == 1) {//��� ������?
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
				if (map[snake_head_x][snake_head_y] == 1) {//��� ������?
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
				if (map[snake_head_x][snake_head_y] == 1) {//��� ������?
					map[snake_head_x][snake_head_y] = 2;
					q.add(new snake(snake_head_x,snake_head_y));
				} else if (map[snake_head_x][snake_head_y] == 0) {
					s= q.poll();
					map[s.x][s.y] = 0;
					map[snake_head_x][snake_head_y] = 2;
					q.add(new snake(snake_head_x,snake_head_y));
				}
			}
			//time�� timeArrIdx�� time�̶� ��������, ���� �ٲ���
			//L ����, D ������
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
