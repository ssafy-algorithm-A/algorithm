import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon17070_�������ű��1 {
	private static int[][] map;
	private static int N;
	private static int answer;
	private static boolean[][][] visited;
	// �� ���º��� ���� ��ġ�� �����ߴ��� Ȯ���ϴ� �迭

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[3][N][N]; // 0:���� 1:���� 2:�밢��
		answer = 0;

		for (int i = 0; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < map.length; j++) {
				map[i][j] = str.charAt(j * 2) - '0';
			}
		}
		visited[0][0][1] = true;

		dfs(0, 1, 0);
		System.out.println(answer);
	}

	/**
	 * @param hR    : ������ �պκ� r
	 * @param hC    : ������ �պκ� c
	 * @param state : ���� �������� ���� 0=> ���� 1=> ���� 2=> �밢��
	 */
	public static void dfs(int hR, int hC, int state) {
		if (hR == N - 1 && hC == N - 1) { // �������� �����ߴٸ�
			answer++;
			return;
		}

		if (state == 0) { // ���λ���
			// ���������� �ж�
			if (hR < N && hC + 1 < N && !visited[0][hR][hC + 1] && map[hR][hC + 1] == 0) {
				dfs(hR, hC + 1, 0);
			}

			// �밢�� �������� �ٲ�
			if (hR + 1 < N && hC + 1 < N && !visited[2][hR + 1][hC + 1] && map[hR + 1][hC + 1] == 0
					&& map[hR + 1][hC] == 0 && map[hR][hC + 1] == 0) {
				dfs(hR + 1, hC + 1, 2);
			}

		} else if (state == 1) { // ���λ���
			// �Ʒ��� �ж�
			if (hR + 1 < N && hC < N && !visited[1][hR + 1][hC] && map[hR + 1][hC] == 0) {
				dfs(hR + 1, hC, 1);
			}
			// �밢�� �������� �ٲ�
			if (hR + 1 < N && hC + 1 < N && !visited[2][hR + 1][hC + 1] && map[hR + 1][hC + 1] == 0
					&& map[hR + 1][hC] == 0 && map[hR][hC + 1] == 0) {
				dfs(hR + 1, hC + 1, 2);
			}
		} else {
			// ���λ��·� �ٲ�
			if (hR < N && hC + 1 < N && !visited[0][hR][hC + 1] && map[hR][hC + 1] == 0) {
				dfs(hR, hC + 1, 0);
			}
			// ���λ��·� �ٲ�
			if (hR + 1 < N && hC < N && !visited[1][hR + 1][hC] && map[hR + 1][hC] == 0) {
				dfs(hR + 1, hC, 1);
			}
			// �밢�� �������� ������ ��
			if (hR + 1 < N && hC + 1 < N && !visited[2][hR + 1][hC + 1] && map[hR + 1][hC + 1] == 0
					&& map[hR + 1][hC] == 0 && map[hR][hC + 1] == 0) {
				dfs(hR + 1, hC + 1, 2);
			}
		}

	}

}