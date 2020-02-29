package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N_14890 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][N];
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		for (int i = 0; i < arr.length; i++) { // 행
			int size = arr[i][0]; // 현재 칸의 높이
			int dx = i;
			int dy = 1;
			int count = 1; // 오르막길은 오르막길 판단 전 경사로 가능한 개수 세어야함 이를 위한 변수
			boolean check = true; // 경사로 세우는 것이 불가능한 경우를 체크하기 위한 변수
			while(true) { 
				if(dy >= arr[0].length || Math.abs(size-arr[dx][dy]) > 1) break;
				
				if(size > arr[dx][dy]) { // 내리막길	
					int temp_size = arr[dx][dy];
					int temp_count = 1;
					int ty = dy+1; // 다음 행
					while(true) {
						if(ty >= arr[0].length || temp_size != arr[dx][ty]) break;
						ty+=1; // 다음 열
						temp_count++;
					}
					if(temp_count < L || (dy+L < arr[0].length && temp_size-arr[dx][dy+L] < 0)) {
						check = false;
						break;
					}
					size = temp_size; // 경사로 세운 칸의 값
					count = temp_count-L; // 사용할 수 있는 경사로 개수에서 사용한 만큼을 뺀 개수
					dy = ty; // 다음 칸의 위치						
				}else if(size < arr[dx][dy]) { // 오르막길
					if(size != arr[dx][dy] && count < L) { 
						check = false;
						break;
					}
					size = arr[dx][dy]; // 다음 칸의 값
					count = 1; // 개수 1
					dy+=1; // 다음 행

				}else { // 같은 길
					count++;
					dy+=1; // 다음 행
				}
			}
			
			if(check && dy >= arr[0].length) answer++;
		}
		
		for (int i = 0; i < arr[0].length; i++) { // 열
			int size = arr[0][i];
			int dx = 1;
			int dy = i;
			int count = 1;
			boolean check = true;
			while(true) {
				if(dx >= arr.length || Math.abs(size-arr[dx][dy]) > 1) break;
				
				if(size > arr[dx][dy]) { // 내리막길
					int temp_size = arr[dx][dy];
					int temp_count = 1;
					int tx = dx+1; 
					while(true) {
						if(tx >= arr.length || temp_size != arr[tx][dy]) break;
						tx+=1; // 다음 행
						temp_count++;
					}
					if(temp_count < L || (dx+L < arr.length && temp_size-arr[dx+L][dy] < 0)) {
						check = false;
						break;
					}
					size = temp_size;						
					count = temp_count-L;
					dx = tx;
				}else if(size < arr[dx][dy]){ // 오르막길
					if(size != arr[dx][dy] && count < L) { // 경사로 가능한 지 판단
						check = false;
						break;
					}
					size = arr[dx][dy]; // 다음 칸의 값
					count = 1;	
					dx+=1; // 다음 열
				}else { // 같은 길
					count++;
					dx+=1; // 다음 열
				}
			}
			
			if(check && dx >= arr.length) answer++;
		}
		
		System.out.println(answer);
	}
	
	
	

}
