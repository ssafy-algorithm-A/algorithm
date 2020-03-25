import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 76ms

public class Baekjoon2579_계단오르기 {
	private static int N;
	private static int[] stair;
	private static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stair = new int[N+1];
		dp = new int[N+1];
		
		
		for (int i = 1; i < stair.length; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
		dp[0] = 0;
		dp[1] = stair[1];
		if(N >= 2) {
			dp[2] = stair[1]+stair[2];
		}
		
		// dp[3] = max(dp[1], dp[0]+계단[2]) + 계단[3]
		for (int i = 3; i < dp.length; i++) {
			dp[i] = Math.max(dp[i-2], dp[i-3]+stair[i-1])+stair[i];
		}
		
		System.out.print(dp[N]);
		
	}
}
