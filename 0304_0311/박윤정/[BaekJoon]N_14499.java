package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N_14499 {
	static int[][] dot = {{0,1},{0,-1},{-1,0},{1,0}}; // �����ϳ���
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		int[] dir_num = new int[5]; // �ֻ��� �ܸ� ��ȣ
		int[] dir_no = new int[6]; // �ܸ� ��ȣ�� ���� ���� ��ȣ
		dir_num[0] = 3; // ��
		dir_num[1] = 4; // ��
		dir_num[2] = 2; // ��
		dir_num[3] = 5; // ��
		dir_num[4] = 1; // ���
		int na = 6;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) { // ���
			int dir = Integer.parseInt(st.nextToken());
			
			x += dot[dir-1][0];
			y += dot[dir-1][1];
			
			// ���� ����� ��� ����
			if(x < 0 || y < 0 || x >= arr.length || y >= arr[0].length) {
				x -= dot[dir-1][0]; 
				y -= dot[dir-1][1];
				continue;
			}
			
			// �ֻ��� ��ġ�� ���� �ܸ� ��ȣ ���ϱ�
			if(dir == 4 || dir == 2) { // �� or ��
				int center = dir_num[4];
				dir_num[4] = dir_num[dir-2]; // ��� : �� �� or ��� : �� ��
				dir_num[dir-2] = na; // ���� : ������ �� or ���� : ������ �� 
				na = dir_num[dir-1]; // ������ : ���� �� or ������ : �� ��
				dir_num[dir-1] = center; // ���� : ��� �� or ���� : ��� ��
			}else { // �� or ��
				int center = dir_num[4];
				dir_num[4] = dir_num[dir]; // ��� : �� �� or ��� : �� ��
				dir_num[dir] = na; // ���� : ������  or ���� : ������ ��
				na = dir_num[dir-1]; // ������ : �� �� or ������ : �� ��
				dir_num[dir-1] = center; // ���� : ��� �� or ���� : ��� ��
			}

			// �ٴ� �鿡 �ʿ� �ִ� ���� �ű��, ��ܿ� ������ �� ���
			if(arr[x][y] == 0) { // �̵� ĭ ���� 0�̸� �ֻ����ٴڸ� -> ĭ
				arr[x][y] = dir_no[na-1];
			}else { // 0�� �ƴ� ��� ĭ-> �ֻ����ٴڸ�
				if(dir_num[4] == 1) { // ���� 1�̸� 6
					dir_no[5] = arr[x][y];
				}else if(dir_num[4] == 2) { // 2�̸� 5
					dir_no[4] = arr[x][y];
				}else if(dir_num[4] == 3){ // 3�̸� 4
					dir_no[3] = arr[x][y];
				}else if(dir_num[4] == 4) { // 4�̸� 3
					dir_no[2] = arr[x][y];
				}else if(dir_num[4] == 5) { // 5�̸� 2
					dir_no[1] = arr[x][y];
				}else { // 6�̸� 1
					dir_no[0] = arr[x][y];
				}
				arr[x][y] = 0; // ĭ�� �����ִ� ���� 0����
			}
			System.out.println(dir_no[dir_num[4]-1]);
			
		}
	}

}
