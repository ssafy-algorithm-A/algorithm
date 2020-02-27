package main.java.Temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bac14500_테트로미노_G5 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N,M;
	static int arr[][];

	//ㅡ ㅁ ㄴ ㄹ ㅜ
	//상 우 하 좌(시계)
	static int dx[] = {0,1,0,-1,1};
	static int dy[] = {-1,0,1,0,1};

	static int figure1[] = {1,1,1};
	static int figure3[] = {2,2,1};
	static int figure4[] = {2,1,2};
	static int figure5[] = {1,2,1};

	static int figure3_s[] = {2,2,3};
	static int figure4_s[] = {2,3,2};
	static int figure5_s[] = {3,2,3};

	static int answer = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1][M+1];

		//배열 입력
		for(int i =1; i<=N; i++){
			st = new StringTokenizer(br.readLine());
			int idx = 1;
			while(st.hasMoreTokens()) {
				arr[i][idx++] = Integer.parseInt(st.nextToken());
			}
		}

		solution();
		System.out.println(answer);
	}

	private static void solution() {
		for(int i =1; i<=N; i++)
			for(int j=1; j<=M; j++) sol1(i,j,0);
	}

	//ㅡ ㅁ ㄴ ㄹ ㅜ
	//오른쪽, 오른쪽, 오른쪽 | 2,2,2 -> 3,3,3
	//오른쪽, 오른 아래, 아래 |
	//아래, 아래, 오른쪽  # 왼  왼 아래 -> 위 위 왼 -> 오 오 위 | 3,3,2 -> 4,4,3 -> 1,1,4 -> 2,2,1
	//아래, 오른, 아래    # 대칭은 좌우 | 3,2,3 -> 2,1,2 -> 1,4,1 -> 4,3,4
	// 오른, 아래|오른 | 2,3|2 -> 1,2|1 -> 4,1|4 -> 3,4|3
	private static void sol1(int x, int y, int cnt) {
		int t_result = 0;
		int result = 0;
		//f1
		t_result = Cal(x,y,figure1,0);
		result = Math.max(result, t_result);

		//f3 + 대칭
		t_result = Cal(x,y,figure3,0);
		result = Math.max(result, t_result);
		t_result = Cal(x,y,figure3_s,0);
		result = Math.max(result, t_result);

		//f4 + 대칭
		t_result = Cal(x,y,figure4,0);
		result = Math.max(result, t_result);
		t_result = Cal(x,y,figure4_s,0);
		result = Math.max(result, t_result);

		//f5
		t_result = Cal(x,y,figure5,1);
		result = Math.max(result, t_result);
		t_result = Cal(x,y,figure5_s,1);
		result = Math.max(result, t_result);

		//f2 + 대칭
		t_result = 0;
		if(isRange(x+1,y+1)) {
			t_result += arr[x][y];
			t_result += arr[x + dy[1]][y + dx[1]];
			t_result += arr[x + dy[2]][y + dx[2]];
			t_result += arr[x + dy[4]][y + dx[4]];
		}
		result = Math.max(result, t_result);
		answer = Math.max(answer, result);

	}

	private static int Cal(int x, int y, int figure[], int op){
		int t_result = 0;
		int result = 0;
		int t_x = x;
		int t_y = y;

		for(int j = 0; j<=3; j++) {
			t_result = 0;
			if (op == 0) {
				t_result += arr[x][y];
				t_x = x;
				t_y = y;
				for (int i = 0; i < 3; i++) {
					if (isRange(t_x + dy[(figure[i]+j)%4], t_y + dx[(figure[i]+j)%4])) {
						t_result += arr[t_x + dy[(figure[i]+j)%4]][t_y + dx[(figure[i]+j)%4]];
						t_x += dy[(figure[i]+j)%4];
						t_y += dx[(figure[i]+j)%4];
					} else {
						t_result = 0;
						break;
					}
				}
			}

			//ㅜ인 경우 중간에서 갈리기 때문에, 갈리는 부분도 영역에 포함하는지
			if (op == 1) {
				if (isRange(t_x + dy[(figure[1]+j)%4] + dy[(figure[0]+j)%4], t_y + dx[(figure[1]+j)%4] + dx[(figure[0]+j)%4])
						&& isRange(t_x + dy[(figure[2]+j)%4] + dy[(figure[0]+j)%4], t_y + dx[(figure[2]+j)%4] + dx[(figure[0]+j)%4])
				) {
					t_result += arr[t_x][t_y];
					t_result += arr[t_x + dy[(figure[0]+j)%4]][t_y + dx[(figure[0]+j)%4]];
					t_x += dy[(figure[0]+j)%4];
					t_y += dx[(figure[0]+j)%4];

					t_result += arr[t_x + dy[(figure[1]+j)%4]][t_y + dx[(figure[1]+j)%4]];
					t_result += arr[t_x + dy[(figure[2]+j)%4]][t_y + dx[(figure[2]+j)%4]];
				} else {
					t_result = 0;
				}
			}
			result = Math.max(t_result, result);
		}
		return result;
	}

	private static boolean isRange(int x, int y){
		if(x>=1 && y>=1 && x<=N && y<=M){
			return true;
		}
		return false;
	}
}
