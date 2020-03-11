import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon16234_인구이동 {
	private static int N;
	private static int L;
	private static int R;
	private static boolean[][] visited;
	private static int[][] map;
	private static int[][] copyMap;
	private static boolean check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N]; // 인구수를 저장한 배열
		copyMap = new int[N][N]; // 인구이동 중간과정을 저장할 배열
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copyMap[i][j] = map[i][j];
			}
		}

		int ans = 0;

		while (true) {

			cloneMap(); // map에 copyMap을 복사하는 함수

			check = false; // 한 번이라도 인구이동이 발생하면 true
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						visited[i][j] = true;
						bfs(i, j);
					}
				}
			}

			if (!check)
				break;
			ans++;
		}

		System.out.println(ans);

	}

	private static void cloneMap() {
		for (int r = 0; r < N; r++) {
			map[r] = copyMap[r].clone(); // deep copy : 내용 복사, 새 객체
		}
	}

	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>(); // bfs 사방탐색을 위한 큐
		q.offer(new int[] { r, c });
		ArrayList<int[]> arr = new ArrayList<int[]>(); // 탐색한 좌표를 저장하는 배열
		arr.add(new int[] { r, c });
		int sum = map[r][c];
		int cnt = 1;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc])
					continue;

				// 인구수 차이가 L 보다 크고 R보다 작다면
				if (Math.abs(map[nr][nc] - map[cur[0]][cur[1]]) >= L
						&& Math.abs(map[nr][nc] - map[cur[0]][cur[1]]) <= R) {
					visited[nr][nc] = true; // 방문체크
					arr.add(new int[] { nr, nc });	// 좌표 저장
					sum += map[nr][nc];	// 인구수 합 저장
					cnt++;	// 지역개수 증가
					q.offer(new int[] { nr, nc });
					check = true;
				}
			}
		}
		int union = sum / cnt;

		for (int[] is : arr) {
			copyMap[is[0]][is[1]] = union;
		}

	}

	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

}
