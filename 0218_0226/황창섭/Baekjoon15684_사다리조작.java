import java.util.Scanner;

// 1584ms
public class Baekjoon15684_사다리조작 {
	private static int[][] ladder;
	private static int N; // 가로선
	private static int M; // 세로선
	private static int H; // 위치의 개수

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		
		ladder = new int[N+1][H+1];
		for (int i = 0; i < M; i++) {
			int a =sc.nextInt();
			int b= sc.nextInt();
			ladder[b][a] = 1;
			// b번 세로선과 b+1 세로선을 a번 위치에서 연결
		}
		
		if(dfs(0, 0, 1, 1)) { // 추가 가로선 0
			System.out.println(0);
		} else if(dfs(0, 1, 1, 1)) { // 추가 가로선 1
			System.out.println(1);
		} else if(dfs(0, 2, 1, 1)) { // 추가 가로선 2
			System.out.println(2);
		} else if(dfs(0, 3, 1, 1)) { // 추가 가로선 3
			System.out.println(3);
		} else { // 불가능한 경우
			System.out.println(-1);
		}
	}

	/**
	 * @param curL 현재 설치한 가로선의 수
	 * @param desL 설치해야할 가로선의 수
	 * @return
	 */
	public static boolean dfs(int curL, int desL, int n, int h) {
		if(curL == desL) {
			if(checkLadder()) {
				return true;
			}
			return false;
		} else {
			for (int i = n; i < N; i++) {
				for (int j = h; j <= H; j++) {
					if(ladder[i][j] == 0) { // 현재 위치에 가로선이 없으면
						if(i-1 > 0 && ladder[i-1][j] == 1) // 이전 세로선에 가로선이 있으면
							continue;
						if(i+1 <= N && ladder[i+1][j] == 1) // 다음 세로선에 가로선이 있으면
							continue;
						
						ladder[i][j] = 1;
						if(dfs(curL+1, desL, n, h+1)) {
							return true;
						}
						ladder[i][j] = 0;
					}
				}
			}
		}
		return false;
	}

	// 모든 사다리가 제대로 연결되었는지 확인
	public static boolean checkLadder() {
		for(int i=1; i<=N; i++) {
			int r = i; // 현재 시작 위치
			for(int j=1; j<=H; j++) {
				if(ladder[r][j] == 1) { // 현재 세로선이 연결되어있으면
					r = r+1; // 다음 세로선으로
				}
				else if(ladder[r-1][j] == 1) { // 이전 세로선이 연결되어있으면
					r = r-1; // 이전 세로선으로
				}
			}
			if(r != i) { // 사다리를 내려와서 시작위치로 오지 못했다면
				return false;
			}
		}
		
		return true;
	}

}
