
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14890 경사로

public class BaekJoon_14890_경사로 {

	static int N, L;
	static int[][] map, map2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		map2 = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				map2[j][i] = map[i][j];
			}
		}

		System.out.println(solving(map) + solving(map2));
	}
	
	public static int solving(int[][] map) {
		int answer = 0;
	
		Out : for (int i = 0; i < N; i++) {
			int len = 1;
			for (int j = 0; j < N - 1; j++) {

				// 길이가 같으면 연속된 길이를 표시하는 len 1 증가
				if (map[i][j] == map[i][j + 1]) len++;

				// 낮은곳 -> 높은곳
				else if (map[i][j + 1] - map[i][j] == 1) {
					if (len >= L) {
						len = 1;
					} else
						continue Out;

					// 높은곳 -> 낮은곳
				} else if (map[i][j] - map[i][j + 1] == 1) {
					
					if(j+1+L > N) continue Out;
					
					// 다음 칸부터 L만큼 같은 칸이 있는지 확인
					int value = map[i][j+1];
					for (int k = j + 1; k < j + 1 + L; k++) {
						if (map[i][k] != value) {
							continue Out;
						}
					}
					j += L-1;
					len = 0;
				} else {
					continue Out;
				}
			}
			answer++;
		}
		
		return answer;
	}
}
