package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N_2293 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int[] dp = new int[K+1];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		dp[0] = 1; //최초 시작점
        for(int i = 0 ; i < N ; i++) {
            for(int j = 1 ; j <= K ; j++) {
                if(j - arr[i] >= 0) dp[j] += dp[j - arr[i]];
            }
        }
		
		System.out.println(dp[K]);
	}
	
}
