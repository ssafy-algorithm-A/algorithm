import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon15686_치킨배달 {
	private static int[][] map;
	private static int N;
	private static int M;
	private static int[] chiR;
	private static int[] chiC;
	private static int[] homeR;
	private static int[] homeC;
	
	private static int[] tChiR;
	private static int[] tChiC;
	
	private static int hNum;
	private static int cNum;
	private static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		// 집의 좌표
		homeR = new int[2*N];
		homeC = new int[2*N];
		
		hNum = 0; // 집의 개수
		
		// 치킨집의 좌표
		chiR = new int[13]; 
		chiC = new int[13];
		
		cNum = 0; // 치킨집의 개수
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(2*j) - '0';
				if(map[i][j] == 1) {
					homeR[hNum] = i;
					homeC[hNum++] = j;
				}
				else if(map[i][j] == 2) {
					chiR[cNum] = i;
					chiC[cNum++] = j;
				}
			}
		}
		
		// 조합으로 뽑은 갚을 저장할 배열		
		tChiR = new int[cNum];
		tChiC = new int[cNum];
		
		answer = Integer.MAX_VALUE;
		
		comb(cNum, M);
		
		System.out.println(answer);
		
		
	}



	// 조합 nCr
	public static void comb(int n, int r) {
		if(r == 0) { // 종료파트
			int chiRoad = 0;
			for (int i = 0; i < hNum; i++) {
				int sum = Integer.MAX_VALUE;
				for (int j = 0; j < M; j++) {
					int a = Math.abs(homeR[i]-tChiR[j]);
					int b = Math.abs(homeC[i]-tChiC[j]);
					sum = Math.min(sum, a+b);
				}
				chiRoad += sum;
			}
			
			answer = Math.min(answer, chiRoad);
			
		} else if(n<r) {
			return;
		} else { // 재귀파트
			tChiR[r-1] = chiR[n-1];
			tChiC[r-1] = chiC[n-1];
			comb(n-1,r-1);
			comb(n-1,r);
		}
	}

}
