package BaekJoon;
// 164ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class N_17822 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken()); // X의 배수
			int D = Integer.parseInt(st.nextToken()); // 방향 0,1
			int K = Integer.parseInt(st.nextToken()); // K번 이동

			int[] map = new int[M+1];
			// 회전하기
			for (int j = 1; j <= N; j++) { 
				if(j % X == 0) { // 번호가 X의 배수인 원판
					if(D == 0) { // 시계방향
						int k;
						for (k = (1+K); k <= M; k++) {
							map[k] = arr[j][k-K];
						}
						int index = 1;
						while(true) {
							map[index++] = arr[j][k-K];
							if((k-K) == M) break;
							k++;
						}
					}else { // 반시계방향
						int k;
						for (k = (1+K); k <= M; k++) {
							map[k-K] = arr[j][k];
						}
						int index = 1;
						while(true) {
							map[k-K] = arr[j][index++];
							if((k-K) == M) break;
							k++;
						}
					}
					
					for (int j2 = 1; j2 <= M; j2++) {
						arr[j][j2] = map[j2];
					}
				}
			}
			
			// 인접한 수이면서 같은 수 찾기
 			HashSet<String> hs = new HashSet<>();
			for (int j = 1; j <= N; j++) { // 반지름 j인 원
				for (int j2 = 1; j2 <= M; j2++) { // 번째 수
					if(arr[j][j2] == -1) continue; // 빈공간은 넘기기
					boolean check = false; // 인접한지 체크
					// 원판끼리 검사
					if(j == 1) {
						if(arr[j][j2] == arr[j+1][j2]) {
							hs.add((j+1)+","+j2);
							check = true;
						}	
					}else if(j == N) {
						if(arr[j][j2] == arr[j-1][j2]) {
							hs.add((j-1)+","+j2);
							check = true;
						}
					}else {
						if(arr[j][j2] == arr[j+1][j2]) {
							hs.add((j+1)+","+j2);
							check = true;
						}
						if(arr[j][j2] == arr[j-1][j2]) {
							hs.add((j-1)+","+j2);
							check = true;
						}				
					}
					
					// 한 원판 인접한 수 찾기
					if(j2 == 1) {
						if(arr[j][j2] == arr[j][j2+1]) {
							hs.add(j+","+(j2+1));
							check = true;
						}
						if(arr[j][j2] == arr[j][M]) {
							hs.add(j+","+M);
							check = true;
						}	
					}else if(j2 == M) {
						// M 검사
						if(arr[j][j2] == arr[j][j2-1]) {
							hs.add(j+","+(j2-1));
							check = true;
						}
						if(arr[j][j2] == arr[j][1]) {
							hs.add(j+","+1);
							check = true;
						}
					}else {
						if(arr[j][j2] ==  arr[j][j2+1]) {
							hs.add(j+","+(j2+1));
							check = true;
						}
						if(arr[j][j2] == arr[j][j2-1]) {
							hs.add(j+","+(j2-1));
							check = true;
						}	
					}
					
					if(check) { // 인접한 수이며 같은 수 있으면
						hs.add(j+","+j2);
					}
				}
			}
			
			if(hs.size() > 0) { // 인접한 수이며 같은 수 있으면
				for (String str : hs) {
					String[] temp = str.split(",");
					int x = Integer.parseInt(temp[0]);
					int y = Integer.parseInt(temp[1]);
					arr[x][y] = -1;
				}
			}else {
				int sum = 0;
				int num = 0;
				for (int a = 1; a <= N; a++) { // 평균을 위한 sum, num 구하기
					for (int b = 1; b <= M; b++) {
						if(arr[a][b] == -1) continue; // 빈공간 거르기
						sum += arr[a][b];
						num++;
					}
				}
				
				double avg;
				if(num == 0) { // 0/0
					avg = 0;
				}else { // 평균 구하기
					avg = (double)sum / num;
				}
				
				for (int a = 1; a <= N; a++) {
					for (int b = 1; b <= M; b++) {
						if(arr[a][b] == -1) continue; // 빈공간 거르기
						if(arr[a][b] > avg) { // 평균보다 크면 -1
							arr[a][b] -= 1;
						}else if(arr[a][b] < avg) { // 평균보다 크면 +1
							arr[a][b] += 1;
						}
					}
				}
			}
			
		}
		
		int sum = 0;
		for (int a = 1; a <= N; a++) { // 총합 구하기
			for (int b = 1; b <= M; b++) {
				if(arr[a][b] == -1) continue; // 빈공간 거르기
				sum += arr[a][b];
			}
		}
		System.out.println(sum);
	}

}
