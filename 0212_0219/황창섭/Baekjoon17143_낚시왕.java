import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon17143_���ÿ� {
	private static int[][] map; // ������
	private static int answer;
	private static int M; // ����� ��
	private static int C; // �������� ũ��
	private static int R; // �������� ũ��

	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };

	static class Shark {
		int r;
		int c;
		int speed; // �ӷ�
		int dest; // ����
		int size; // ũ��
		int index; // ��� ��ȣ

		public Shark(int r, int c, int speed, int dest, int size, int index) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dest = dest;
			this.size = size;
			this.index = index;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = 0;

		map = new int[R][C];
		Shark[] shark = new Shark[M + 1];
		boolean[] isDead = new boolean[M + 1];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if(d == 1 || d == 2) {
				s %= (2*R-2); // ������ġ �״�� ���ƿ��� ���� �������ʿ� �����Ƿ� %
			}
			else {
				s %= (2*C-2);
			}
			// ex) ���ΰ� 5�� �ʿ��� �ڱ��ڽ����� ���ƿ��� ���� 8�� �̵�
			// �� 1�� �̵��̳� 9�� �̵��̳� 993�� �̵� ��� ���� ��ġ�� �̵��Ѵ�
			int z = Integer.parseInt(st.nextToken());
			map[r-1][c-1] = i;
			shark[i] = new Shark(r-1, c-1, s, d, z, i);
		}

		int curPos = 0; // ���� ���ÿ��� ��ġ
		while (curPos < C) { // ���ÿ��� �� ������ ��������
			// �����ϱ�
			for (int i = 0; i < R; i++) {
				if (map[i][curPos] > 0) {
					answer += shark[map[i][curPos]].size;
					isDead[map[i][curPos]] = true; // ���� ���� ����
					map[i][curPos] = 0;
					break;
				}
			}
			
			// ��� �̵�
			for (int i = 1; i <= M; i++) {
				if (!isDead[i]) { // ������ ���鸸 �˻�
					int nR;
					int nC;
					nR = shark[i].r;
					nC = shark[i].c;
					if(map[nR][nC] == i) {
						map[nR][nC] = 0;
					}
					
					for (int sp = 0; sp < shark[i].speed; sp++) {
						
						int tR = nR + dr[shark[i].dest];
						int tC = nC + dc[shark[i].dest];
						
						// �ʰ�谡 �ƴϸ� ��ġ����
						if (tR < R && tR >= 0 && tC < C && tC >= 0) {
							nR = tR;
							nC = tC;
						}
						else { // ���� ��ȯ �� ��ġ ����
							if(shark[i].dest == 1) {
								shark[i].dest = 2;
								nR = nR + dr[shark[i].dest];
								nC = nC + dc[shark[i].dest];
							} else if(shark[i].dest == 2) {
								shark[i].dest = 1;
								nR = nR + dr[shark[i].dest];
								nC = nC + dc[shark[i].dest];
								
							} else if(shark[i].dest == 3) {
								shark[i].dest = 4;
								nR = nR + dr[shark[i].dest];
								nC = nC + dc[shark[i].dest];
							}
							else {
								shark[i].dest = 3;
								nR = nR + dr[shark[i].dest];
								nC = nC + dc[shark[i].dest];
							}
						}
					}
					shark[i].r = nR;
					shark[i].c = nC;
					
					// ���� ��ġ�� ��ġ�� ���� �� ���� �� ���δ�
					if(map[nR][nC] < i && map[nR][nC] != 0) { // �� �������
						if(shark[map[nR][nC]].size < shark[i].size) { // ���� ��� ���̱�
							isDead[map[nR][nC]] = true; // �׾��ٰ� ǥ��
							map[nR][nC] = i;
						}
						else {
							isDead[i] = true;
						}
					}
					else {
						map[nR][nC] = i;
					}
				}
			}
			
			curPos++; // ���ÿ� ��ġ �̵�

		} // end of while

		System.out.println(answer);
	}

}