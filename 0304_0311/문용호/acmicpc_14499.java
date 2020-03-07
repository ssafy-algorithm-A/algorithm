package acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class dice{
	int up;//��
	int down;//��
	int face;//��
	int back;//��
	int left;//��
	int right;//��
	public dice(int up,int down,int left,int right, int face, int back) {
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.face = face;
		this.back = back;
	}
}

public class acmicpc_14499 {
	static int [][] map;
	static int N,M,x,y,k;
	static int cur_x,cur_y;//�ֻ��� �ϴ��� ��ǥ
	static dice d;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		while (st.hasMoreTokens()){
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());//����ũ�� N,M
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());//�ֻ��� ��ǥx,y
			k = Integer.parseInt(st.nextToken());//����� ����
		}
		map = new int [N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//���� �� �Է�
		d = new dice(0, 0, 0, 0, 0, 0);
		st = new StringTokenizer(br.readLine()," ");//��� : 1�� 2�� 3�� 4��
		for (int i = 0; i < k; i++) {
			simulate(Integer.parseInt(st.nextToken()));
		}
	}//end main
	
	private static void simulate(int dir) {
//		System.out.println(d.up+" "+d.down+" "+d.left+" "+d.right+" "+d.face+" "+d.back);
		//�̵��� ĭ�� �����ִ� ���� 0�̸�, �ֻ����� �ٴڸ鿡 �����ִ� ���� ĭ�� ����
		// �̵��� ĭ�� �����ִ� ���� 0�� �ƴϸ�, ĭ�� �����ִ� ���� �ֻ��� �ٴڸ����� ����, ĭ�� ���� 0����
		//�ٱ����� �̵���Ű���� ���� �ش� ����� ����, ��µ� x
		switch (dir) {
		case 1://��
			y+=1;
			if (y > M-1) {
				y-=1;
				break;
			}
			int tmp_up1=d.up;
			d.up = d.left; d.left = d.down; d.down = d.right; d.right=tmp_up1;  
			if (map[x][y] == 0) {
				map[x][y] = d.down;
				System.out.println(d.up);
			} else {
				d.down = map[x][y];
				map[x][y] = 0;
				System.out.println(d.up);
			}
			break;
		case 2://��
			y-=1;
			if (y < 0) {
				y+=1;
				break;
			}
			int tmp_up2=d.up;
			d.up = d.right; d.right=d.down; d.down = d.left; d.left = tmp_up2;
			if (map[x][y] == 0) {
				map[x][y] = d.down;
				System.out.println(d.up);
			} else {
				d.down = map[x][y];
				map[x][y] = 0;
				System.out.println(d.up);
			}
			break;
		case 3://��
			x-=1;
			if (x < 0) {
				x+=1;
				break;
			}
			int tmp_up3=d.up;
			d.up = d.face; d.face=d.down; d.down = d.back; d.back = tmp_up3;
			if (map[x][y] == 0) {
				map[x][y] = d.down;
				System.out.println(d.up);
			} else {
				d.down = map[x][y];
				map[x][y] = 0;
				System.out.println(d.up);
			}
			break;
		case 4://��
			x+=1;
			if (x > N-1) {
				x-=1;
				break;
			}
			int tmp_up4=d.up;
			d.up = d.back; d.back=d.down; d.down = d.face; d.face = tmp_up4;
			if (map[x][y] == 0) {
				map[x][y] = d.down;
				System.out.println(d.up);
			} else {
				d.down = map[x][y];
				map[x][y] = 0;
				System.out.println(d.up);
			}
			break;
		}//end switch
	}//end simulate
	
}//end class
