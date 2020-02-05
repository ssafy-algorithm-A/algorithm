import java.util.Scanner;

/**
 * 디저트 카페
 */
public class Swexpert2105_디저트카페 {
	static int[] dr = { 1, 1, -1, -1 };
	static int[] dc = { 1, -1, -1, 1 };
	static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			answer = -1;
			int N = sc.nextInt();
			int[][] map = new int[N + 2][N + 2]; // 맵 index 1~N

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			// 오른쪽아래, 왼쪽아래, 왼쪽위, 오른쪽위 순으로 탐색 
			for (int i = 1; i <= N - 1; i++) {
				for (int j = 2; j <= N - 1; j++) {
					boolean[] desert = new boolean[101]; // 겹치는 디저트가 있는지 확인하기 위한 배열
					boolean[][] visited = new boolean[N + 2][N + 2]; // 이미 방문한 카페인지 확인하기 위한 배열

					if (i + dr[0] > N || i + dr[0] < 1 || j + dc[0] > N || j + dc[0] < 1)
						continue;

					if (map[i][j] == map[i + dr[0]][j + dc[0]])
						continue;
					
					// 현재 위치에서 오른쪽 아래로 내려갈 수 있는지 먼저 검사후 탐색시작

					desert[map[i][j]] = true; 
					visited[i][j] = true;
					dfs(map, visited, i + dr[0], j + dc[0], 2, i, j, 0, desert);
				}

			}

			System.out.println("#" + test_case + " " + answer);

		}
	}

	/**
	 * @param count 현재까지 탐색한 카페 수
	 * @param startR 최종 목적지
	 * @param startC 최종 목적지
	 * @param idx 한번 방향을 바꾼 후 이전 방향으로 탐색할 수 없도록 설정하기 위한 변수
	 * @param desert
	 */
	static void dfs(int[][] map, boolean[][] visited, int curR, int curC, int count, int startR, int startC, int idx, boolean[] desert) {

		visited[curR][curC] = true;
		desert[map[curR][curC]] = true;

		// idx 부터 탐색함으로서 꼬불꼬불 탐색을 할수 없게 방지
		for (int i = idx; i < 4; i++) {
			int nextR = curR + dr[i];
			int nextC = curC + dc[i];

			if (!visited[nextR][nextC] && map[nextR][nextC] != 0 && !desert[map[nextR][nextC]])
				dfs(map, visited, nextR, nextC, count + 1, startR, startC, i, desert);
			else if (nextR == startR && nextC == startC) {
				if (answer < count && count != 2)
					answer = count;
			}
		}

		visited[curR][curC] = false;
		desert[map[curR][curC]] = false;

	}

}
