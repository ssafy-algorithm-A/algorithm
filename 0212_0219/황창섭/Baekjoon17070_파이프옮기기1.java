import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon17070_파이프옮기기1 {
	private static int[][] map;
	private static int N;
	private static int answer;
	private static boolean[][][] visited;
	// 각 상태별로 현재 위치에 도달했는지 확인하는 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[3][N][N]; // 0:가로 1:세로 2:대각선
		answer = 0;

		for (int i = 0; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < map.length; j++) {
				map[i][j] = str.charAt(j * 2) - '0';
			}
		}
		visited[0][0][1] = true;

		dfs(0, 1, 0);
		System.out.println(answer);
	}

	/**
	 * @param hR    : 파이프 앞부분 r
	 * @param hC    : 파이프 앞부분 c
	 * @param state : 현재 파이프의 상태 0=> 가로 1=> 세로 2=> 대각선
	 */
	public static void dfs(int hR, int hC, int state) {
		if (hR == N - 1 && hC == N - 1) { // 도착점에 도착했다면
			answer++;
			return;
		}

		if (state == 0) { // 가로상태
			// 오른쪽으로 밀때
			if (hR < N && hC + 1 < N && !visited[0][hR][hC + 1] && map[hR][hC + 1] == 0) {
				dfs(hR, hC + 1, 0);
			}

			// 대각선 방향으로 바뀔때
			if (hR + 1 < N && hC + 1 < N && !visited[2][hR + 1][hC + 1] && map[hR + 1][hC + 1] == 0
					&& map[hR + 1][hC] == 0 && map[hR][hC + 1] == 0) {
				dfs(hR + 1, hC + 1, 2);
			}

		} else if (state == 1) { // 세로상태
			// 아래로 밀때
			if (hR + 1 < N && hC < N && !visited[1][hR + 1][hC] && map[hR + 1][hC] == 0) {
				dfs(hR + 1, hC, 1);
			}
			// 대각선 방향으로 바뀔때
			if (hR + 1 < N && hC + 1 < N && !visited[2][hR + 1][hC + 1] && map[hR + 1][hC + 1] == 0
					&& map[hR + 1][hC] == 0 && map[hR][hC + 1] == 0) {
				dfs(hR + 1, hC + 1, 2);
			}
		} else {
			// 가로상태로 바뀔때
			if (hR < N && hC + 1 < N && !visited[0][hR][hC + 1] && map[hR][hC + 1] == 0) {
				dfs(hR, hC + 1, 0);
			}
			// 세로상태로 바뀔때
			if (hR + 1 < N && hC < N && !visited[1][hR + 1][hC] && map[hR + 1][hC] == 0) {
				dfs(hR + 1, hC, 1);
			}
			// 대각선 뱡향으로 진행할 때
			if (hR + 1 < N && hC + 1 < N && !visited[2][hR + 1][hC + 1] && map[hR + 1][hC + 1] == 0
					&& map[hR + 1][hC] == 0 && map[hR][hC + 1] == 0) {
				dfs(hR + 1, hC + 1, 2);
			}
		}

	}

}