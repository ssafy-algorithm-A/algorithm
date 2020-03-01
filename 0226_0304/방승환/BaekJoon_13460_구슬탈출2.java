import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



// 13460 구슬 탈출 2

class BaekJoon_13460_구슬탈출2 {
	
	static int N, M, Rx, Ry, Bx, By, ANSWER = Integer.MAX_VALUE;
	static char[][] map;
	// 동 서 남 북
	static int[][] dir = {{}, {0,1},{0,-1},{1,0},{-1,0}};
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<str.length(); j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'R') {
					Rx = i;
					Ry = j;
				} else if(map[i][j] == 'B') {
					Bx = i;
					By = j;
				}
			}
		}
		
		for(int i=1; i<=4; i++) {
			// 1 : 동, 2 : 서, 3 : 남, 4 : 북
			bowling(Rx, Ry, Bx, By, 1, i);
		}
		
		if(ANSWER == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ANSWER);
	}

	public static void bowling(int rx, int ry, int bx, int by, int count, int d) {
		
		if(count >= ANSWER) return;
		if(count > 10) return;
		
		int nrx = rx;
		int nry = ry;
		
		int nbx = bx;
		int nby = by;
		
		boolean redIn = false;
		boolean blueIn = false;
		
		// 빨간공 움직이기
		while(map[nrx][nry] != '#') {
			if(map[nrx][nry] == 'O') {
				redIn = true;
				break;
			}
			nrx += dir[d][0];
			nry += dir[d][1];
		}
		nrx -= dir[d][0];
		nry -= dir[d][1];
	
		// 파란공 움직이기
		while(map[nbx][nby] != '#') {
			if(map[nbx][nby] == 'O') {
				blueIn = true;
				break;
			}
			nbx += dir[d][0];
			nby += dir[d][1];
		}
		nbx -= dir[d][0];
		nby -= dir[d][1];
		
		// 파란공이 빠졌으면
		if(blueIn) return;
		else {
			// 파란공이 안빠졌는데 빨간공이 빠졌으면
			if(redIn) {
				ANSWER = Math.min(ANSWER, count);
				return;
			}
		}
		
		// 빨간공과 파란공이 같은 위치에 있으면
		if (nrx == nbx && nry == nby) {
			int redLen = Math.abs(nrx-rx) + Math.abs(nry-ry);
			int blueLen = Math.abs(nbx-bx) + Math.abs(nby-by);
			
			// 움직인 거리가 많다는 뜻은 더 뒤에 있는 공이라는 뜻
			if(redLen > blueLen) {
				nrx -= dir[d][0];
				nry -= dir[d][1];
			} else {
				nbx -= dir[d][0];
				nby -= dir[d][1];
			}
		}
		
		for(int i=1; i<=4; i++) {
			if(i == d || i == reverse(i)) continue;
			bowling(nrx, nry, nbx, nby, count+1, i);
		}

	}
	
	public static int reverse(int x) {
		if(x == 1) return 2;
		else if( x == 2) return 1;
		else if( x == 3) return 4;
		else if(x == 4) return 3;
		return x;
	}
}
