package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N_15684 {
	public static int[][] arr;
	public static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		arr = new int[H+1][N+1]; // 세로선 번호 홀수가 사다리, 짝수번은 가로선 넣는 곳
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken()); // 가로 번호
			int b = Integer.parseInt(st.nextToken()); // 세로 번호
			arr[a][b] = 1;
		}

		go(0,1);
		if(answer == Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);
	}
	
	public static void go(int count, int sx) {
		if(answer == 0 || answer == 1 || count > 3) return; // 종료조건
		if(count <= 3) { // 사다리 세운 수
			boolean check = true;
			for (int j = 1; j < arr[0].length; j++) {
				int sj = j;
				for (int i = 1; i < arr.length; i++) { // 가로선
					if(arr[i][sj] == 1) {
						sj += 1;
					}else if(arr[i][sj-1] == 1) {
						sj -= 1;
					}
				}
				if(j != sj) {
					check = false;
					break;
				}
			}
			if(check) {
				answer = Math.min(answer, count);
			}
		}
		
		for (int i = sx; i < arr.length; i++) { // 가로선
			for (int j = 1; j < arr[i].length-1; j++) {
				if(arr[i][j] == 0 && arr[i][j-1] == 0 && arr[i][j+1] == 0) { // 비어있음
					arr[i][j] = 1;
					go(count+1,i); 
					arr[i][j] = 0;
				}
			}
		}
	}

}
