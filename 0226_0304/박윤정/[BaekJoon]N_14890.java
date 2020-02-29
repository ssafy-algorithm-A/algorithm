package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N_14890 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][N];
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		for (int i = 0; i < arr.length; i++) { // ��
			int size = arr[i][0]; // ���� ĭ�� ����
			int dx = i;
			int dy = 1;
			int count = 1; // ���������� �������� �Ǵ� �� ���� ������ ���� ������� �̸� ���� ����
			boolean check = true; // ���� ����� ���� �Ұ����� ��츦 üũ�ϱ� ���� ����
			while(true) { 
				if(dy >= arr[0].length || Math.abs(size-arr[dx][dy]) > 1) break;
				
				if(size > arr[dx][dy]) { // ��������	
					int temp_size = arr[dx][dy];
					int temp_count = 1;
					int ty = dy+1; // ���� ��
					while(true) {
						if(ty >= arr[0].length || temp_size != arr[dx][ty]) break;
						ty+=1; // ���� ��
						temp_count++;
					}
					if(temp_count < L || (dy+L < arr[0].length && temp_size-arr[dx][dy+L] < 0)) {
						check = false;
						break;
					}
					size = temp_size; // ���� ���� ĭ�� ��
					count = temp_count-L; // ����� �� �ִ� ���� �������� ����� ��ŭ�� �� ����
					dy = ty; // ���� ĭ�� ��ġ						
				}else if(size < arr[dx][dy]) { // ��������
					if(size != arr[dx][dy] && count < L) { 
						check = false;
						break;
					}
					size = arr[dx][dy]; // ���� ĭ�� ��
					count = 1; // ���� 1
					dy+=1; // ���� ��

				}else { // ���� ��
					count++;
					dy+=1; // ���� ��
				}
			}
			
			if(check && dy >= arr[0].length) answer++;
		}
		
		for (int i = 0; i < arr[0].length; i++) { // ��
			int size = arr[0][i];
			int dx = 1;
			int dy = i;
			int count = 1;
			boolean check = true;
			while(true) {
				if(dx >= arr.length || Math.abs(size-arr[dx][dy]) > 1) break;
				
				if(size > arr[dx][dy]) { // ��������
					int temp_size = arr[dx][dy];
					int temp_count = 1;
					int tx = dx+1; 
					while(true) {
						if(tx >= arr.length || temp_size != arr[tx][dy]) break;
						tx+=1; // ���� ��
						temp_count++;
					}
					if(temp_count < L || (dx+L < arr.length && temp_size-arr[dx+L][dy] < 0)) {
						check = false;
						break;
					}
					size = temp_size;						
					count = temp_count-L;
					dx = tx;
				}else if(size < arr[dx][dy]){ // ��������
					if(size != arr[dx][dy] && count < L) { // ���� ������ �� �Ǵ�
						check = false;
						break;
					}
					size = arr[dx][dy]; // ���� ĭ�� ��
					count = 1;	
					dx+=1; // ���� ��
				}else { // ���� ��
					count++;
					dx+=1; // ���� ��
				}
			}
			
			if(check && dx >= arr.length) answer++;
		}
		
		System.out.println(answer);
	}
	
	
	

}
