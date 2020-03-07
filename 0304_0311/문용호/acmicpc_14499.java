package acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class dice{
	int up;//상
	int down;//하
	int face;//전
	int back;//후
	int left;//좌
	int right;//우
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
	static int cur_x,cur_y;//주사위 하단의 좌표
	static dice d;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		while (st.hasMoreTokens()){
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());//지도크기 N,M
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());//주사위 좌표x,y
			k = Integer.parseInt(st.nextToken());//명령의 갯수
		}
		map = new int [N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//지도 값 입력
		d = new dice(0, 0, 0, 0, 0, 0);
		st = new StringTokenizer(br.readLine()," ");//명령 : 1동 2서 3북 4남
		for (int i = 0; i < k; i++) {
			simulate(Integer.parseInt(st.nextToken()));
		}
	}//end main
	
	private static void simulate(int dir) {
//		System.out.println(d.up+" "+d.down+" "+d.left+" "+d.right+" "+d.face+" "+d.back);
		//이동한 칸에 쓰여있는 수가 0이면, 주사위의 바닥면에 쓰여있는 수가 칸에 복사
		// 이동한 칸에 쓰여있는 수가 0이 아니면, 칸에 쓰여있는 수가 주사위 바닥면으로 복사, 칸의 수는 0으로
		//바깥으로 이동시키려는 경우는 해당 명령을 무시, 출력도 x
		switch (dir) {
		case 1://동
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
		case 2://서
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
		case 3://북
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
		case 4://남
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
