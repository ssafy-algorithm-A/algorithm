import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1952_수영장 {
	private static int[] ticket; // 이용권 가격
	private static int[] year; // 12개월 이용 계획
	private static int answer;
	private static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ticket = new int[4];
			for (int i = 0; i < 4; i++) {
				ticket[i] = Integer.parseInt(st.nextToken());
			}

			year = new int[12];
			visited = new boolean[12];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < year.length; i++) {
				year[i] = Integer.parseInt(st.nextToken());
			}
			answer = ticket[3]; // 1년 이용권 가격으로 초기화

			// 1개월 이용권과 3개월 이용권의 조합
			for (int i = 0; i <= 12; i++) {
				for (int j = 0; j <= 4; j++) {
					if (i + 3 * j <= 12) { // 1개월 이용권과 3개월 이용권의 합이 12개월 이하이면
						calPrice(i, j, 0, 0, 0); // 가격 계산
					}
				}
			}
			System.out.println("#" + test_case + " " + answer);
		} // end of tc
	} // end of main

	/**
	 * @param month : 1개월 이용권의 수
	 * @param tMonth : 3개월 이용권의 수
	 * @param index : 1개월 이용권 조합을 위한 index
	 * @param index2 : 3개월 이용권 조합을 위한 index
	 * @param sum : 가격의 총합
	 */
	public static void calPrice(int month, int tMonth, int index, int index2, int sum) {
		if (month == 0 && tMonth == 0) { // 1개월 이용권과 3개월 이용권을 모두 사용했다면
			for (int i = 0; i < year.length; i++) {
				if (!visited[i]) {
					sum += year[i] * ticket[0]; 
					// 1개월과 3개월 이용권으로 채우지 못한 나머지 달은 1일 이용권으로 계산
				}
			}
			if (answer > sum)
				answer = sum;
		} else {
			for (int i = index; i < year.length; i++) {
					if (!visited[i] && month > 0) {
						visited[i] = true;
						calPrice(month - 1, tMonth, i, index2, sum + ticket[1]);
						visited[i] = false;
					}
			}
			for (int i = index2; i < year.length-2; i++) {
				if (!visited[i] && !visited[i + 1] && !visited[i + 2] && tMonth > 0) {
					visited[i] = true;
					visited[i + 1] = true;
					visited[i + 2] = true;
					calPrice(month, tMonth - 1, index, i, sum + ticket[2]);
					visited[i] = false;
					visited[i + 1] = false;
					visited[i + 2] = false;
				}
			}
		}

	}
}
