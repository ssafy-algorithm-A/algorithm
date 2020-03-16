import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ����ð� 88ms

public class Baekjoon2239_����1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] coin = new int[n]; // ���� ��ġ�� �����ϴ� �迭

		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		int[] dp = new int[k + 1];
		// dp[n] = n���� ����� ���� ������ ����� ��
		dp[0] = 1;

		
		/*
		 * ���� ��ġ�� 1,3,5 �϶� ������ ��� ���
		 * 1. ���� 1�� ���
		 * dp[1]~dp[k] = 1 ����� �� ��� �Ѱ���
		 * 2. ���� 3 �߰�
		 * dp[4] = dp[4](���� 1�� ������������ ����� ��) + dp[4 - 3��](���� 3�� ������� �� �߰��Ǵ� ����� ��)
		 * 
		 */
		for (int i = 0; i < n; i++) { // ������ �������� ������ ��츦 �����ؼ� �����ش�
			for (int j = 1; j <= k; j++) {
				if (j - coin[i] >= 0) {
					dp[j] += dp[j - coin[i]];
				}
			}
		}

		System.out.print(dp[k]);

	}

}
