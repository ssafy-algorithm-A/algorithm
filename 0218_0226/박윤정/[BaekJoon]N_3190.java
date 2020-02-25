package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N_3190 {
	public static int[][] dot = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[x - 1][y - 1] = -1; // ����� -1�� ǥ��
		}
		
		arr[0][0] = 1; // ���� ù��° ��ġ�� 0,0 ����
		int L = Integer.parseInt(br.readLine());
		int[] time = new int[L]; // �ð� ��Ƶ� �迭
		char[] goD = new char[L]; // �ð��� ���� �̵� ���� ��Ƶ� �迭
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			time[i] = x;
			goD[i] = c;
		}
		
		int sx = 0; // ���� ��ġ
		int sy = 0; // ���� ��ġ
		int answer = 0; // �ð�
		int dir = 1; // ���� ����
		int dx = sx; // �̵��� ��ġ
		int dy = sy; // �̵��� ��ġ
		int sdir = 1; // ���� ���� 
		int index = 0; // �־��� �ð��� ���� ��Ƴ��� �迭 üũ�� �ε���
		List<DOT> list = new ArrayList<>(); // ������ ��ġ�� ������ �������ִµ� �ʿ��� ����Ʈ
		while(true) {
			answer++;
			dx += dot[dir][0]; // �̵��� ��ġ ����
			dy += dot[dir][1]; // �̵��� ��ġ ����

			if (dx < 0 || dy < 0 || dx >= arr.length || dy >= arr[0].length || arr[dx][dy] == 1) {
				break;
			} // ������ ����ų� �ڽ��� ���� �ε����� ���� ����

			if (arr[dx][dy] == 0) { // ����� ���ٸ�
				arr[sx][sy] = 0; // ���� ����ֱ�
				if(list.size() == 0) { // ����Ʈ�� ����ִٸ� ���� ��ġ�� ���� ���� �ʿ� ����
					sx += dot[dir][0];
					sy += dot[dir][1];	
				}else { // ����Ʈ�� ������� �ʴٸ� ���� ��ġ�� ���� ���� �ʿ���
					DOT d = list.get(0); 
					if(sx == d.x && sy == d.y) {
						sdir = d.dir;	
						list.remove(0);
					}	
					sx += dot[sdir][0];
					sy += dot[sdir][1];	
				}
			}
			arr[dx][dy] = 1; // ��� ��������

			if(index < time.length && answer == time[index]) { // �־��� �ð��� ���� ��Ƴ��� �迭 �ε��� üũ �� �ð��� �ش��ϴ� ��� ��������
				if (goD[index] == 'L') { // L�̸� ���� 90��, D�̸� ������ 90��
					dir = (dir + 3) % 4;
				} else {
					dir = (dir + 1) % 4;
				}
				list.add(new DOT(dx,dy,dir)); // �Ӹ� ���� ��ȯ�� �����Ƿ� ������ �� ��ġ�� ���� ��� ���� ������ �ٲپ� �־�� ��, ����Ʈ�� ���������� ��� ����
				index++; // ���� �ε�����
			}
			
		}

		System.out.println(answer);
	}
	
	public static class DOT{ // x,y ��ġ�� �����ϸ� dir�������� ����
		int x,y,dir;
		
		public DOT(int x,int y,int dir) {
			this.x= x;
			this.y = y;
			this.dir= dir;
		}
	}
}
