package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N_3190 {
	public static int[][] dot = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[x - 1][y - 1] = -1; // 사과를 -1로 표시
		}
		
		arr[0][0] = 1; // 뱀의 첫번째 위치는 0,0 고정
		int L = Integer.parseInt(br.readLine());
		int[] time = new int[L]; // 시간 담아둔 배열
		char[] goD = new char[L]; // 시간에 따른 이동 방향 담아둔 배열
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			time[i] = x;
			goD[i] = c;
		}
		
		int sx = 0; // 꼬리 위치
		int sy = 0; // 꼬리 위치
		int answer = 0; // 시간
		int dir = 1; // 현재 방향
		int dx = sx; // 이동할 위치
		int dy = sy; // 이동할 위치
		int sdir = 1; // 꼬리 방향 
		int index = 0; // 주어진 시간과 방향 담아놓은 배열 체크할 인덱스
		List<DOT> list = new ArrayList<>(); // 꼬리의 위치와 방향을 설정해주는데 필요한 리스트
		while(true) {
			answer++;
			dx += dot[dir][0]; // 이동할 위치 설정
			dy += dot[dir][1]; // 이동할 위치 설정

			if (dx < 0 || dy < 0 || dx >= arr.length || dy >= arr[0].length || arr[dx][dy] == 1) {
				break;
			} // 범위를 벗어나거나 자신의 몸에 부딪히면 게임 종료

			if (arr[dx][dy] == 0) { // 사과가 없다면
				arr[sx][sy] = 0; // 꼬리 비워주기
				if(list.size() == 0) { // 리스트가 비어있다면 꼬리 위치와 방향 설정 필요 없음
					sx += dot[dir][0];
					sy += dot[dir][1];	
				}else { // 리스트가 비어있지 않다면 꼬리 위치와 방향 설정 필요함
					DOT d = list.get(0); 
					if(sx == d.x && sy == d.y) {
						sdir = d.dir;	
						list.remove(0);
					}	
					sx += dot[sdir][0];
					sy += dot[sdir][1];	
				}
			}
			arr[dx][dy] = 1; // 사과 없어지고

			if(index < time.length && answer == time[index]) { // 주어진 시간과 방향 담아놓은 배열 인덱스 체크 후 시간에 해당하는 명령 가져오기
				if (goD[index] == 'L') { // L이면 왼쪽 90도, D이면 오른쪽 90도
					dir = (dir + 3) % 4;
				} else {
					dir = (dir + 1) % 4;
				}
				list.add(new DOT(dx,dy,dir)); // 머리 방향 전환을 했으므로 꼬리가 그 위치에 왔을 경우 기존 방향을 바꾸어 주어야 함, 리스트에 순차적으로 담아 놓기
				index++; // 다음 인덱스로
			}
			
		}

		System.out.println(answer);
	}
	
	public static class DOT{ // x,y 위치에 도달하면 dir방향으로 가라
		int x,y,dir;
		
		public DOT(int x,int y,int dir) {
			this.x= x;
			this.y = y;
			this.dir= dir;
		}
	}
}
