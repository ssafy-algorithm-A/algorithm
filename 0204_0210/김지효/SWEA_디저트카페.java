import java.util.Scanner;

public class SWEA_디저트카페 {
	static int [][]Map;
	static int N;
	
	static int L_row = 0; 
	static int L_col = 0;
	static int R_row = 0;
	static int R_col = 0;
	static int M_row = 0;
	static int M_col = 0;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int TC = scan.nextInt();
		
		for (int testcase = 1; testcase <= TC; testcase++) {
			N = scan.nextInt(); //Map의 크기
			Map = new int[N][N];
			for (int i = 0; i < Map.length; i++) {
				for (int j = 0; j < Map.length; j++) {
					Map[i][j] = scan.nextInt();
				}
			}
			/*for (int i = 0; i < Map.length; i++) {
				for (int j = 0; j < Map.length; j++) {
					System.out.print(Map[i][j] + " ");
				}
				System.out.println();
			}*/
			int max = -1;
			//디저트 개수는 1~100 이다.
			for (int i = 0; i < Map.length-2; i++) {
				for (int j = 1; j < Map.length-1; j++) {
					//Map전체를 돌면서 기준점들에 따라서 더해준다. 1~N-1
					
					//여기서 부터 반복문은 대각선으로 가는 것이다.
					//왼쪽 대각선은 길이가 1 ~ 열 크기만큼만 커질 수 있다.
					//왼쪽대각선이 1 일때 오른쪽대각선의 최대까지 보고 
					//2일때 또 최대로 보고 형식으로 한다.
					
					
					//왼쪽 오른쪽 좌표 기준의 마지막들을 가리킨다.
					
					// 왼 1 오 1  --> 왼1 오2 --> 왼2 오1 --> 왼2 오2
					//위의 가정대로면 왼은 현재 좌표의 열 좌표 크기만큼이 최대이다.
					int result = 0;
					for (int k = 1; k <= j; k++) {
						L_row = i+k;
						L_col = j-k;
						if(!check(L_row, L_col)) continue;
						for(int z = 1; z <= N-j-1; z++) {
							R_row = i + z;
							R_col = j + z;
							M_row = L_row + z;
							M_col = L_col + z;
							//이제 4번의 경로를 돌아본다.

							if(!check(R_row, R_col) || !check(M_row, M_col)) continue;
							result = LoopCross(i,j);

							
							if(max < result) max = result;
						}
					}
				
				}
			}
			System.out.println("#"+testcase + " " + max);
			
			
			
		}

	}
	public static int LoopCross(int S_row,int S_col) {
		boolean []visit = new boolean[101]; //방문했던 디저트인지 확인해준다.
		//true이면 방문했던 디저트
		int cnt = 1;
		visit[Map[S_row][S_col]] = true; //첫 시작점 숫자를 방문
		int temp_row = S_row, temp_col = S_col;
		int sum = Map[S_row][S_col];
		//왼쪽 대각선
		for (int i = 0; i < Map.length; i++) {
			//L의 기준점에 도착하면 종료
			if(temp_row == L_row && temp_col == L_col) break;
			else {
				temp_row++; temp_col--;
				cnt++;
				if(visit[Map[temp_row][temp_col]]) return -1;
				visit[Map[temp_row][temp_col]] = true;
			}
		}

		//오른쪽 대각선
		temp_row = S_row; temp_col = S_col;
		for (int i = 0; i < Map.length; i++) {
			if(temp_row == R_row && temp_col == R_col) break;
			else {
				temp_row++; temp_col++;
				cnt++;
				//방문했으면 리턴-1
				if(visit[Map[temp_row][temp_col]]) return -1;
				visit[Map[temp_row][temp_col]] = true;
			}
		}
		
		//오른쪽에서 왼쪽 아래 대각선
		temp_row = R_row+1; temp_col = R_col-1;
		for (int i = 0; i < Map.length; i++) {
			if(temp_row == M_row && temp_col == M_col) {
				if(visit[Map[temp_row][temp_col]]) return -1;
				visit[Map[temp_row][temp_col]] = true;
				cnt++;
				break;
			}
			else {
				if(visit[Map[temp_row][temp_col]]) return -1;
				visit[Map[temp_row][temp_col]] = true;
				temp_row++; temp_col--;
				cnt++;
				
				
			}
		}

		//왼쪽에서 오른쪽 아래 대각선
		temp_row = L_row+1; temp_col = L_col+1;
		for (int i = 0; i < Map.length; i++) {
			if(temp_row == M_row && temp_col == M_col) {
				visit[Map[temp_row][temp_col]] = true;
				break;
			}
			else {
				if(visit[Map[temp_row][temp_col]]) return -1;
				visit[Map[temp_row][temp_col]] = true;
				temp_row++; temp_col++;
				cnt++;
				
				
			}
		}
		return cnt;
	}
	public static boolean check(int row, int col) {
		if(row < 0 || col < 0 || row > N-1 || col > N-1) return false;
		return true;
	}

}
