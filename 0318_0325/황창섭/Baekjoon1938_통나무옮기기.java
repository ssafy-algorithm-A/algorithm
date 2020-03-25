import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 84ms

public class Baekjoon1938_통나무옮기기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];
		boolean[][] rotateMap = new boolean[N][N];

		int[] logR = new int[3]; // 통나무 좌표 r
		int[] logC = new int[3]; // 통나무 좌표 c
		int lTop = -1;
		int rotate = 0; // 0:세로  1:가로
		
		int[] endR = new int[3]; // 도착 좌표 r
		int[] endC = new int[3]; // 도착 좌표 c
		int eRotate = 0;
		int eTop = -1;

		for (int i = 0; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < map.length; j++) {
				switch (str.charAt(j)) {
				case '0':
					map[i][j] = 0;
					break;
				case '1':
					map[i][j] = 1;
					break;
				case 'B':
					logR[++lTop] = i;
					logC[lTop] = j;
					break;
				case 'E':
					endR[++eTop] = i;
					endC[eTop] = j;
					break;
				}
			}
		}
		
		// 통나무가 돌 수 있는 좌표들을 미리 확인
		// 9개의 칸이 모두 0 일때 true
		// 0 0 0
		// 0 0 0
		// 0 0 0
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < N-1; j++) {
				boolean check = true;
				loop: for (int r = -1; r <= 1; r++) {
					for (int c = -1; c <= 1; c++) {
						if(map[i+r][j+c] == 1) {
							check = false;
							break loop;
						}
					}
				}
				
				if(check) {
					rotateMap[i][j] = true;
				}
			}
		}
		
		
		if(logR[0] == logR[1]) { // r 좌표가 같으면 가로
			rotate = 1;
		}
		
		if(endR[0] == endR[1]) { // r 좌표가 같으면 가로
			eRotate = 1;
		}
		
		// r, c, 상태(가로,세로)
		boolean[][][] visited = new boolean[N][N][2];
		
		// queue 저장 방식은 회전, 이동 모두 가운데를 기준으로 하므로
		// 가운데의 r,c 그리고 현재 통나무 상태(가로, 세로), 마지막으로 이동횟수
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {logR[1], logC[1], rotate, 0});
		visited[logR[1]][logC[1]][rotate] = true;
		
		int answer = 0;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int curR = temp[0];
			int curC = temp[1];
			int curDest = temp[2];
			int curNum = temp[3];
			
			if(endR[1] == curR && endC[1] == curC && eRotate == curDest) {
				answer = curNum;
				break;
			}
			
			if(curDest == 0) { // 세로
				if(curR-2 >= 0) { // 위 방향 이동
					if(map[curR-2][curC] == 0 && !visited[curR-1][curC][0]) {
						visited[curR-1][curC][0] = true;
						q.offer(new int[] {curR-1, curC, 0, curNum+1});
					}
				}
				
				if(curR+2 < N) { // 아래 방향 이동
					if(map[curR+2][curC] == 0 && !visited[curR+1][curC][0]) {
						visited[curR+1][curC][0] = true;
						q.offer(new int[] {curR+1, curC, 0, curNum+1});
					}
				}
				
				if(curC-1 >= 0) { // 좌 이동
					if(map[curR][curC-1] == 0 && map[curR-1][curC-1] == 0 && 
							map[curR+1][curC-1] == 0 && !visited[curR][curC-1][0]) {
						visited[curR][curC-1][0] = true;
						q.offer(new int[] {curR, curC-1, 0, curNum+1});
					}
				}
				
				if(curC+1 < N) { // 우 이동
					if(map[curR][curC+1] == 0 && map[curR-1][curC+1] == 0 && 
							map[curR+1][curC+1] == 0 && !visited[curR][curC+1][0]) {
						visited[curR][curC+1][0] = true;
						q.offer(new int[] {curR, curC+1, 0, curNum+1});
					}
				}
				
				if(rotateMap[curR][curC] && !visited[curR][curC][1]) { // 회전
					visited[curR][curC][1] = true;
					q.offer(new int[] {curR, curC, 1, curNum+1});
				}
				
				
			}
			else { // 가로
				if(curR-1 >= 0) { // 위 방향 이동
					if(map[curR-1][curC-1] == 0 && map[curR-1][curC] == 0 &&
							map[curR-1][curC+1] == 0 && !visited[curR-1][curC][1]) {
						visited[curR-1][curC][1] = true;
						q.offer(new int[] {curR-1, curC, 1, curNum+1});
					}
				}
				
				if(curR+1 < N) { // 아래
					if(map[curR+1][curC-1] == 0 && map[curR+1][curC] == 0 &&
							map[curR+1][curC+1] == 0 && !visited[curR+1][curC][1]) {
						visited[curR+1][curC][1] = true;
						q.offer(new int[] {curR+1, curC, 1, curNum+1});
					}
				}
				
				if(curC-2 >= 0) { // 왼쪽
					if(map[curR][curC-2] == 0 && !visited[curR][curC-1][1]) {
						visited[curR][curC-1][1] = true;
						q.offer(new int[] {curR, curC-1, 1, curNum+1});
					}
				}
				
				if(curC+2 < N) { // 오른쪽
					if(map[curR][curC+2] == 0 && !visited[curR][curC+1][1]) {
						visited[curR][curC+1][1] = true;
						q.offer(new int[] {curR, curC+1, 1, curNum+1});
					}
				}
				
				if(rotateMap[curR][curC] && !visited[curR][curC][0]) { // 회전
					visited[curR][curC][0] = true;
					q.offer(new int[] {curR, curC, 0, curNum+1});
				}
			}
			
		} // end of while
		
		System.out.print(answer);
	}

}
