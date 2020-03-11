import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon14499_주사위굴리기 {
	private static int N;
	private static int M;
	private static int x;
	private static int y;
	
	private static int[] dr = {0,0,0,-1,1}; // 1: 동, 2: 서, 3: 북, 4: 남
	private static int[] dc = {0,1,-1,0,0};
	private static int K;
	private static int[] diceIdx = {0,1,2,3,4,5}; // 계속 변경될 주사위의 인덱스를 표시하기 위한 배열
	// diceIdx[0] -> 주사위 윗면, diceIdx[5] -> 주사위 아랫면

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
		
		// 명령들을 담은 배열
		int[] com = new int[K];
		String str = br.readLine();
		for (int i = 0; i < K; i++) {
			com[i] = str.charAt(i*2) - '0';
		}
		
		int[] dice = new int[6];
		
		
		for (int i = 0; i < K; i++) {
			
			// 주사위를 굴린다
			int nX = x + dr[com[i]];
			int nY = y + dc[com[i]];
			
			// 맵 경계로 넘어가면 아무런 조작 하지않고 다음으로 스킵
			if(nX < 0 || nY <0 || nX >= N || nY >= M) continue;
			
			// 굴린 방향에 따라 인덱스를 변경
			changeDice(com[i]);
			
			// 움직인 칸에 쓰인 수가 0
			if(map[nX][nY] == 0) {
				map[nX][nY] = dice[diceIdx[5]];
			}
			// 움직인 칸에 쓰인 수가 0이 아닌 수
			else {
				dice[diceIdx[5]] = map[nX][nY];
				map[nX][nY] = 0;
			}
			
			System.out.print(dice[diceIdx[0]] + "\n");
			x = nX;
			y = nY;
		}
		
		
	}

	// 주사위의 위 아래 옆의 인덱스를 변경하는 함수
	// d : 방향(동서남북)
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
