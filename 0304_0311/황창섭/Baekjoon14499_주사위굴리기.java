import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon14499_�ֻ��������� {
	private static int N;
	private static int M;
	private static int x;
	private static int y;
	
	private static int[] dr = {0,0,0,-1,1}; // 1: ��, 2: ��, 3: ��, 4: ��
	private static int[] dc = {0,1,-1,0,0};
	private static int K;
	private static int[] diceIdx = {0,1,2,3,4,5}; // ��� ����� �ֻ����� �ε����� ǥ���ϱ� ���� �迭
	// diceIdx[0] -> �ֻ��� ����, diceIdx[5] -> �ֻ��� �Ʒ���

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str= br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j*2) - '0';
			}
		}
		
		// ��ɵ��� ���� �迭
		int[] com = new int[K];
		String str = br.readLine();
		for (int i = 0; i < K; i++) {
			com[i] = str.charAt(i*2) - '0';
		}
		
		int[] dice = new int[6];
		
		
		for (int i = 0; i < K; i++) {
			
			// �ֻ����� ������
			int nX = x + dr[com[i]];
			int nY = y + dc[com[i]];
			
			// �� ���� �Ѿ�� �ƹ��� ���� �����ʰ� �������� ��ŵ
			if(nX < 0 || nY <0 || nX >= N || nY >= M) continue;
			
			// ���� ���⿡ ���� �ε����� ����
			changeDice(com[i]);
			
			// ������ ĭ�� ���� ���� 0
			if(map[nX][nY] == 0) {
				map[nX][nY] = dice[diceIdx[5]];
			}
			// ������ ĭ�� ���� ���� 0�� �ƴ� ��
			else {
				dice[diceIdx[5]] = map[nX][nY];
				map[nX][nY] = 0;
			}
			
			System.out.print(dice[diceIdx[0]] + "\n");
			x = nX;
			y = nY;
		}
		
		
	}

	// �ֻ����� �� �Ʒ� ���� �ε����� �����ϴ� �Լ�
	// d : ����(��������)
	private static void changeDice(int d) {
		int temp;
		switch(d) {
		case 1:
			temp = diceIdx[0];
			diceIdx[0] = diceIdx[3];
			diceIdx[3] = diceIdx[5];
			diceIdx[5] = diceIdx[2];
			diceIdx[2] = temp;
			break;			
		case 2:
			temp = diceIdx[0];
			diceIdx[0] = diceIdx[2];
			diceIdx[2] = diceIdx[5];
			diceIdx[5] = diceIdx[3];
			diceIdx[3] = temp;
			break;
		case 3:
			temp = diceIdx[0];
			diceIdx[0] = diceIdx[4];
			diceIdx[4] = diceIdx[5];
			diceIdx[5] = diceIdx[1];
			diceIdx[1] = temp;
			break;
		case 4:
			temp = diceIdx[0];
			diceIdx[0] = diceIdx[1];
			diceIdx[1] = diceIdx[5];
			diceIdx[5] = diceIdx[4];
			diceIdx[4] = temp;
			break;
			
		}
		
	}
	
	
	
}
