import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 84ms

public class Baekjoon1938_�볪���ű�� {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];
		boolean[][] rotateMap = new boolean[N][N];

		int[] logR = new int[3]; // �볪�� ��ǥ r
		int[] logC = new int[3]; // �볪�� ��ǥ c
		int lTop = -1;
		int rotate = 0; // 0:����  1:����
		
		int[] endR = new int[3]; // ���� ��ǥ r
		int[] endC = new int[3]; // ���� ��ǥ c
		int eRotate = 0;
		int eTop = -1;

		for (int i = 0; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < map.length; j++) {
				switch (str.charAt(j)) {
				case '0':
					map[i][j] = 0;
					break;
				case '1':
					map[i][j] = 1;
					break;
				case 'B':
					logR[++lTop] = i;
					logC[lTop] = j;
					break;
				case 'E':
					endR[++eTop] = i;
					endC[eTop] = j;
					break;
				}
			}
		}
		
		// �볪���� �� �� �ִ� ��ǥ���� �̸� Ȯ��
		// 9���� ĭ�� ��� 0 �϶� true
		// 0 0 0
		// 0 0 0
		// 0 0 0
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < N-1; j++) {
				boolean check = true;
				loop: for (int r = -1; r <= 1; r++) {
					for (int c = -1; c <= 1; c++) {
						if(map[i+r][j+c] == 1) {
							check = false;
							break loop;
						}
					}
				}
				
				if(check) {
					rotateMap[i][j] = true;
				}
			}
		}
		
		
		if(logR[0] == logR[1]) { // r ��ǥ�� ������ ����
			rotate = 1;
		}
		
		if(endR[0] == endR[1]) { // r ��ǥ�� ������ ����
			eRotate = 1;
		}
		
		// r, c, ����(����,����)
		boolean[][][] visited = new boolean[N][N][2];
		
		// queue ���� ����� ȸ��, �̵� ��� ����� �������� �ϹǷ�
		// ����� r,c �׸��� ���� �볪�� ����(����, ����), ���������� �̵�Ƚ��
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {logR[1], logC[1], rotate, 0});
		visited[logR[1]][logC[1]][rotate] = true;
		
		int answer = 0;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int curR = temp[0];
			int curC = temp[1];
			int curDest = temp[2];
			int curNum = temp[3];
			
			if(endR[1] == curR && endC[1] == curC && eRotate == curDest) {
				answer = curNum;
				break;
			}
			
			if(curDest == 0) { // ����
				if(curR-2 >= 0) { // �� ���� �̵�
					if(map[curR-2][curC] == 0 && !visited[curR-1][curC][0]) {
						visited[curR-1][curC][0] = true;
						q.offer(new int[] {curR-1, curC, 0, curNum+1});
					}
				}
				
				if(curR+2 < N) { // �Ʒ� ���� �̵�
					if(map[curR+2][curC] == 0 && !visited[curR+1][curC][0]) {
						visited[curR+1][curC][0] = true;
						q.offer(new int[] {curR+1, curC, 0, curNum+1});
					}
				}
				
				if(curC-1 >= 0) { // �� �̵�
					if(map[curR][curC-1] == 0 && map[curR-1][curC-1] == 0 && 
							map[curR+1][curC-1] == 0 && !visited[curR][curC-1][0]) {
						visited[curR][curC-1][0] = true;
						q.offer(new int[] {curR, curC-1, 0, curNum+1});
					}
				}
				
				if(curC+1 < N) { // �� �̵�
					if(map[curR][curC+1] == 0 && map[curR-1][curC+1] == 0 && 
							map[curR+1][curC+1] == 0 && !visited[curR][curC+1][0]) {
						visited[curR][curC+1][0] = true;
						q.offer(new int[] {curR, curC+1, 0, curNum+1});
					}
				}
				
				if(rotateMap[curR][curC] && !visited[curR][curC][1]) { // ȸ��
					visited[curR][curC][1] = true;
					q.offer(new int[] {curR, curC, 1, curNum+1});
				}
				
				
			}
			else { // ����
				if(curR-1 >= 0) { // �� ���� �̵�
					if(map[curR-1][curC-1] == 0 && map[curR-1][curC] == 0 &&
							map[curR-1][curC+1] == 0 && !visited[curR-1][curC][1]) {
						visited[curR-1][curC][1] = true;
						q.offer(new int[] {curR-1, curC, 1, curNum+1});
					}
				}
				
				if(curR+1 < N) { // �Ʒ�
					if(map[curR+1][curC-1] == 0 && map[curR+1][curC] == 0 &&
							map[curR+1][curC+1] == 0 && !visited[curR+1][curC][1]) {
						visited[curR+1][curC][1] = true;
						q.offer(new int[] {curR+1, curC, 1, curNum+1});
					}
				}
				
				if(curC-2 >= 0) { // ����
					if(map[curR][curC-2] == 0 && !visited[curR][curC-1][1]) {
						visited[curR][curC-1][1] = true;
						q.offer(new int[] {curR, curC-1, 1, curNum+1});
					}
				}
				
				if(curC+2 < N) { // ������
					if(map[curR][curC+2] == 0 && !visited[curR][curC+1][1]) {
						visited[curR][curC+1][1] = true;
						q.offer(new int[] {curR, curC+1, 1, curNum+1});
					}
				}
				
				if(rotateMap[curR][curC] && !visited[curR][curC][0]) { // ȸ��
					visited[curR][curC][0] = true;
					q.offer(new int[] {curR, curC, 0, curNum+1});
				}
			}
			
		} // end of while
		
		System.out.print(answer);
	}

}
