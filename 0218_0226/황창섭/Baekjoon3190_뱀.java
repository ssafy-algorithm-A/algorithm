import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 84ms 13672kb
public class Baekjoon3190_뱀 {

	static int[] dr = { -1, 0, 1, 0 }; // 상우하좌
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 1][N + 1];
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 2;
		}

		map[1][1] = 1;
		int L = Integer.parseInt(br.readLine());
		ArrayList<int[]> info = new ArrayList<int[]>(); // 방향 전환 정보 입력
		// info.get(idx)[0] : 시간, info.get(idx)[1] : 방향

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			if (C == 'L') {
				info.add(new int[] { X, 0 });
			} else {
				info.add(new int[] { X, 1 });
			}
		}

		Deque<int[]> snake = new LinkedList<int[]>();
		snake.add(new int[] { 1, 1 }); // 뱀의 머리 위치
		int dest = 1; // 뱀의 방향
		int answer = 0;

		for (int i = 0;;) {

			int[] arr = new int[2];
			if(i< info.size()) {
				arr = info.get(i);
			}
			
			answer++; // 현재 게임 시간

			// 머리 부분 정보 꺼내기
			int nextR = snake.getFirst()[0] + dr[dest];
			int nextC = snake.getFirst()[1] + dc[dest];

			// 맵경계에 닿거나 자기 자신의 몸에 닿을경우 break
			if (nextR > N || nextC > N || nextR < 1 || nextC < 1 || map[nextR][nextC] == 1) {
				break;
			}

			// 머리가 사과로 도착할 경우
			if (map[nextR][nextC] == 2) {
				map[nextR][nextC] = 1; // 사과 위치를 뱀의 위치로 설정
				snake.addFirst(new int[] { nextR, nextC }); // 뱀의 머리 위치 삽입
			} else if (map[nextR][nextC] == 0) { // 빈공간일 경우
				int[] tail = snake.removeLast(); // 꼬리 정보 가져오기
				map[tail[0]][tail[1]] = 0; // 꼬리위치 맵에서 0으로 변경
				map[nextR][nextC] = 1;     // 뱀의 위치 표시
				snake.addFirst(new int[] { nextR, nextC }); // 뱀의 머리 위치 삽입
			}

			if (answer == arr[0]) { // 방향 전환이 필요한 경우
				if (arr[1] == 0) {
					dest = (dest + 3) % 4;
				} else {
					dest = (dest + 1) % 4;
				}
				i++; // 방향 전환 후 다음 info 정보를 가져오기 위해 i+1
			}
		}

		System.out.println(answer);

	} // end of main
} // end of class