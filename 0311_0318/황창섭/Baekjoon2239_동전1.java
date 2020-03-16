import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실행시간 88ms

public class Baekjoon2239_동전1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] coin = new int[n]; // 동전 가치를 저장하는 배열

		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		int[] dp = new int[k + 1];
		// dp[n] = n원을 만들기 위해 가능한 경우의 수
		dp[0] = 1;

		
		/*
		 * 동전 가치가 1,3,5 일때 가능한 경우 계산
		 * 1. 동전 1만 사용
		 * dp[1]~dp[k] = 1 경우의 수 모두 한가지
		 * 2. 동전 3 추가
		 * dp[4] = dp[4](동전 1만 사용했을경우의 경우의 수) + dp[4 - 3원](동전 3을 사용했을 때 추가되는 경우의 수)
		 * 
		 */
		for (int i = 0; i < n; i++) { // 각각의 동전으로 가능한 경우를 누적해서 더해준다
			for (int j = 1; j <= k; j++) {
				if (j - coin[i] >= 0) {
					dp[j] += dp[j - coin[i]];
				}
			}
		}

		System.out.print(dp[k]);

	}

}
