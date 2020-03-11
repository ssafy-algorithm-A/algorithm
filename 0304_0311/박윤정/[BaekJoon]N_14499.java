package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N_14499 {
	static int[][] dot = {{0,1},{0,-1},{-1,0},{1,0}}; // 동서북남순
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		int[] dir_num = new int[5]; // 주사위 단면 번호
		int[] dir_no = new int[6]; // 단면 번호에 따른 실제 번호
		dir_num[0] = 3; // 동
		dir_num[1] = 4; // 서
		dir_num[2] = 2; // 북
		dir_num[3] = 5; // 남
		dir_num[4] = 1; // 가운데
		int na = 6;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) { // 명령
			int dir = Integer.parseInt(st.nextToken());
			
			x += dot[dir-1][0];
			y += dot[dir-1][1];
			
			// 범위 벗어나면 명령 무시
			if(x < 0 || y < 0 || x >= arr.length || y >= arr[0].length) {
				x -= dot[dir-1][0]; 
				y -= dot[dir-1][1];
				continue;
			}
			
			// 주사위 위치에 따른 단면 번호 구하기
			if(dir == 4 || dir == 2) { // 남 or 서
				int center = dir_num[4];
				dir_num[4] = dir_num[dir-2]; // 가운데 : 북 값 or 가운데 : 동 값
				dir_num[dir-2] = na; // 북쪽 : 나머지 값 or 동쪽 : 나머지 값 
				na = dir_num[dir-1]; // 나머지 : 남쪽 값 or 나머지 : 서 값
				dir_num[dir-1] = center; // 남쪽 : 가운데 값 or 서쪽 : 가운데 값
			}else { // 북 or 동
				int center = dir_num[4];
				dir_num[4] = dir_num[dir]; // 가운데 : 남 값 or 가운데 : 서 값
				dir_num[dir] = na; // 남쪽 : 나머지  or 서쪽 : 나머지 값
				na = dir_num[dir-1]; // 나머지 : 북 값 or 나머지 : 동 값
				dir_num[dir-1] = center; // 북쪽 : 가운데 값 or 동쪽 : 가운데 값
			}

			// 바닥 면에 맵에 있는 숫자 옮기기, 상단에 쓰여진 값 출력
			if(arr[x][y] == 0) { // 이동 칸 수가 0이면 주사위바닥면 -> 칸
				arr[x][y] = dir_no[na-1];
			}else { // 0이 아닌 경우 칸-> 주사위바닥면
				if(dir_num[4] == 1) { // 위가 1이면 6
					dir_no[5] = arr[x][y];
				}else if(dir_num[4] == 2) { // 2이면 5
					dir_no[4] = arr[x][y];
				}else if(dir_num[4] == 3){ // 3이면 4
					dir_no[3] = arr[x][y];
				}else if(dir_num[4] == 4) { // 4이면 3
					dir_no[2] = arr[x][y];
				}else if(dir_num[4] == 5) { // 5이면 2
					dir_no[1] = arr[x][y];
				}else { // 6이면 1
					dir_no[0] = arr[x][y];
				}
				arr[x][y] = 0; // 칸에 쓰여있는 수는 0으로
			}
			System.out.println(dir_no[dir_num[4]-1]);
			
		}
	}

}
