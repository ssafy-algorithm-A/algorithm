import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon17143_낚시왕 {
	private static int[][] map; // 격자판
	private static int answer;
	private static int M; // 상어의 수
	private static int C; // 격자판의 크기
	private static int R; // 격자판의 크기

	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };

	static class Shark {
		int r;
		int c;
		int speed; // 속력
		int dest; // 방향
		int size; // 크기
		int index; // 상어 번호

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
				s %= (2*R-2); // 현재위치 그대로 돌아오는 경우는 생각할필요 없으므로 %
			}
			else {
				s %= (2*C-2);
			}
			// ex) 가로가 5인 맵에서 자기자신으로 돌아오는 경우는 8번 이동
			// 즉 1번 이동이나 9번 이동이나 993번 이동 모두 같은 위치로 이동한다
			int z = Integer.parseInt(st.nextToken());
			map[r-1][c-1] = i;
			shark[i] = new Shark(r-1, c-1, s, d, z, i);
		}

		int curPos = 0; // 현재 낚시왕의 위치
		while (curPos < C) { // 낚시왕이 맵 끝까지 갈때까지
			// 낚시하기
			for (int i = 0; i < R; i++) {
				if (map[i][curPos] > 0) {
					answer += shark[map[i][curPos]].size;
					isDead[map[i][curPos]] = true; // 잡은 상어는 죽임
					map[i][curPos] = 0;
					break;
				}
			}
			
			// 상어 이동
			for (int i = 1; i <= M; i++) {
				if (!isDead[i]) { // 안죽은 상어들만 검사
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
						
						// 맵경계가 아니면 위치변경
						if (tR < R && tR >= 0 && tC < C && tC >= 0) {
							nR = tR;
							nC = tC;
						}
						else { // 방향 전환 후 위치 변경
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
					
					// 같은 위치에 위치한 상어들 중 작은 상어를 죽인다
					if(map[nR][nC] < i && map[nR][nC] != 0) { // 상어가 있을경우
						if(shark[map[nR][nC]].size < shark[i].size) { // 작은 상어 죽이기
							isDead[map[nR][nC]] = true; // 죽었다고 표시
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
			
			curPos++; // 낚시왕 위치 이동

		} // end of while

		System.out.println(answer);
	}

}