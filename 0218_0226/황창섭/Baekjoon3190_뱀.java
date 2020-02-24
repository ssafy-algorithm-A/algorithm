import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 84ms 13672kb
public class Baekjoon3190_�� {

	static int[] dr = { -1, 0, 1, 0 }; // �������
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 1][N + 1];
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 2;
		}

		map[1][1] = 1;
		int L = Integer.parseInt(br.readLine());
		ArrayList<int[]> info = new ArrayList<int[]>(); // ���� ��ȯ ���� �Է�
		// info.get(idx)[0] : �ð�, info.get(idx)[1] : ����

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			if (C == 'L') {
				info.add(new int[] { X, 0 });
			} else {
				info.add(new int[] { X, 1 });
			}
		}

		Deque<int[]> snake = new LinkedList<int[]>();
		snake.add(new int[] { 1, 1 }); // ���� �Ӹ� ��ġ
		int dest = 1; // ���� ����
		int answer = 0;

		for (int i = 0;;) {

			int[] arr = new int[2];
			if(i< info.size()) {
				arr = info.get(i);
			}
			
			answer++; // ���� ���� �ð�

			// �Ӹ� �κ� ���� ������
			int nextR = snake.getFirst()[0] + dr[dest];
			int nextC = snake.getFirst()[1] + dc[dest];

			// �ʰ�迡 ��ų� �ڱ� �ڽ��� ���� ������� break
			if (nextR > N || nextC > N || nextR < 1 || nextC < 1 || map[nextR][nextC] == 1) {
				break;
			}

			// �Ӹ��� ����� ������ ���
			if (map[nextR][nextC] == 2) {
				map[nextR][nextC] = 1; // ��� ��ġ�� ���� ��ġ�� ����
				snake.addFirst(new int[] { nextR, nextC }); // ���� �Ӹ� ��ġ ����
			} else if (map[nextR][nextC] == 0) { // ������� ���
				int[] tail = snake.removeLast(); // ���� ���� ��������
				map[tail[0]][tail[1]] = 0; // ������ġ �ʿ��� 0���� ����
				map[nextR][nextC] = 1;     // ���� ��ġ ǥ��
				snake.addFirst(new int[] { nextR, nextC }); // ���� �Ӹ� ��ġ ����
			}

			if (answer == arr[0]) { // ���� ��ȯ�� �ʿ��� ���
				if (arr[1] == 0) {
					dest = (dest + 3) % 4;
				} else {
					dest = (dest + 1) % 4;
				}
				i++; // ���� ��ȯ �� ���� info ������ �������� ���� i+1
			}
		}

		System.out.println(answer);

	} // end of main
} // end of class