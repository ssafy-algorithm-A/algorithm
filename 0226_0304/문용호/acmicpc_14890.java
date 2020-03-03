package acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class acmicpc_14890 {
	static int [][] map;
	static int N,L;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int [N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int idx = 0;
			while(st.hasMoreTokens()) {
				map[i][idx++] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = 0;
		//가로줄에대해서 탐색
		for (int i = 0; i < map.length; i++) {
			int [] tmp1 = new int [N];
			int [] tmp2 = new int [N];
			for (int j = 0; j < map.length; j++) {
				tmp1[j] = map[i][j];
				tmp2[j] = map[j][i];
			}
//			System.out.print("가로 : "+Arrays.toString(tmp1)+" ");
//			System.out.print("세로 : "+Arrays.toString(tmp2)+" ");
			if (run(tmp1)) {
//				System.out.println(Arrays.toString(tmp1));
				ans++;
			}
			if (run(tmp2)) {
//				System.out.println(Arrays.toString(tmp2));
				ans++;
			}
		}//end outer for
		System.out.println(ans);
	}//end main
	public static boolean run(int[] tmp) {
		boolean [] visited = new boolean [N]; // 경사로가 있는지 여부를 확인할 배열 
		for (int i = 0; i < N-1; i++) {
			if (tmp[i] == tmp[i+1]) { //경사가 같은 경우
				continue;
				
			} if (Math.abs(tmp[i]-tmp[i+1]) > 1){ // 크기 1보다 큰경우
				return false;
				
			}if (tmp[i] -1 == tmp[i+1]) { //경사가 낮아지는 경우
				for (int j = i+1; j <= i+L; j++) {
					if (j >= N || tmp[i+1] != tmp[j] || visited[j] == true) return false;
					visited[j] = true;
				}
			} else if (tmp[i]+1 == tmp[i+1]) {//경사 높아지는 경우
				for (int j = i; j > i-L; j--) {
					if (j < 0 || tmp[i] != tmp[j] || visited[j] == true) return false;
					visited[j] = true;
				}
			}
		}//end for
		return true;
	}//end run
}//end class
