import java.util.Scanner;

// 164ms 16624kb
public class Baekjoon17825_�ֻ��������� {
	private static int answer;
	private static int[] dice; // �ֻ��� �� ����
	private static Horse[] horse; // ���� ������ ��ġ
	private static Node[] node; // map�� ����
	private static boolean[] visited; // ���� ��ġ�� ���� �ִ��� ������ Ȯ��

	static class Horse {
		public Horse(Node x, int totalNum) {
			this.x = x;
			this.totalNum = totalNum;
		}

		Node x;
		int totalNum;
	}

	static class Node {
		int val;
		int index;
		Node next;
		Node next2;

		public Node(int val, int index, Node next, Node next2) {
			this.val = val;
			this.index = index;
			this.next = next; // ���� ȭ��ǥ ����
			this.next2 = next2; // �Ķ� ȭ��ǥ ����
		}
	}

	public static void main(String[] args) {
		visited = new boolean[33];
		Scanner sc = new Scanner(System.in);
		node = new Node[33];
		for (int i = 0; i < node.length; i++) {
			node[i] = new Node(0, i, null, null);
		}
		for (int i = 0; i <= 19; i++) {
			node[i].val = i * 2;
			node[i].next = node[i + 1];
		}

		node[21].val = 13;
		node[21].next = node[22];
		node[22].val = 16;
		node[22].next = node[23];
		node[23].val = 19;
		node[23].next = node[24];

		node[24].val = 25;
		node[24].next = node[30];

		node[27].val = 28;
		node[27].next = node[26];
		node[26].val = 27;
		node[26].next = node[25];
		node[25].val = 26;
		node[25].next = node[24];

		node[28].val = 22;
		node[28].next = node[29];
		node[29].val = 24;
		node[29].next = node[24];
		node[30].val = 30;
		node[30].next = node[31];
		node[31].val = 35;
		node[31].next = node[20];

		// 3���� �Ķ��� ĭ, next2 : �Ķ� ȭ��ǥ ���� ���
		node[5].next2 = node[21]; // val 10->13
		node[10].next2 = node[28]; // val 20->22
		node[15].next2 = node[27]; // val 30->28

		//// ���� ������ ��� ////
		node[20].val = 40;
		node[20].next = node[32];

		dice = new int[10];
		horse = new Horse[4];
		for (int i = 0; i < horse.length; i++) {
			horse[i] = new Horse(node[0], 0);
		}

		for (int i = 0; i < dice.length; i++) {
			dice[i] = sc.nextInt();
		}

		dfs(0);

		System.out.println(answer);

	}

	public static void dfs(int diceIdx) {
		if (diceIdx == 10) { // �ֻ����� 10�� ���� ������ ���
			int sum = 0;
			// ������ ���� ���� �ջ�
			for (int i = 0; i < horse.length; i++) {
				sum += horse[i].totalNum;
			}
			if (answer < sum)
				answer = sum;
		} else {
			for (int j = 0; j < horse.length; j++) {
				if (horse[j].x == node[32]) { // ���� ���� ��ġ�� ���������� ���
					continue;
				}

				Node k = horse[j].x; // ���� ���� ��ġ �������� (1)
				visited[k.index] = false; // ���� ���� ��ġ ���� false (2)

				// ���� �̵� ������ �Ķ��� ĭ�� ���
				if (k == node[5] || k == node[10] || k == node[15]) {
					horse[j].x = moveDice(k, dice[diceIdx], true);
				} else {
					horse[j].x = moveDice(k, dice[diceIdx], false);
				}
				// horse[j].x ���� ���� �ֻ��� �� ��ŭ �̵��� ��ġ�� ����ȴ�
				
				// �̵��� ��ġ�� �ٸ����� ���� ���
				if (visited[horse[j].x.index]) {
					visited[k.index] = true; // (2)
					horse[j].x = k; // �̵��ϱ� �� ��ġ�� ���� (1) 
					continue;
				}

				// �̵��� �������� ���
				horse[j].totalNum += horse[j].x.val; // ���� ������ �� ��ġ��
				if (horse[j].x != node[32]) { // ���� ������ ���� ���� ���� �� �����Ƿ�
					visited[horse[j].x.index] = true;  // ���� ������ �ƴ� ��츸 true ǥ��
				}
				dfs(diceIdx + 1);
				visited[k.index] = true; // ���� ���� (2)
				visited[horse[j].x.index] = false; // ���� ����
				horse[j].totalNum -= horse[j].x.val; // ���� ����
				horse[j].x = k; // ���� ���� (1)

			}

		}
	}

	/**
	 * @param k ���� ��� ��ġ
	 * @param moveCnt ���� ������ ��
	 * @param check next�� ���� next2�� ���� Ȯ������ ���� ����
	 * @return
	 */
	public static Node moveDice(Node k, int moveCnt, boolean check) {
		if (moveCnt == 0) {
			return k;
		}

		if (check) { // �Ķ��� ĭ���� �������� ���
			k = moveDice(k.next2, moveCnt - 1, false);
		} else { // ���� ȭ��ǥ�� ���󰡾��ϴ� ���
			if (k == node[32]) { // ���� ��ġ�� ������ġ�� ���
				return k;
			} else {
				k = moveDice(k.next, moveCnt - 1, false);
			}
		}

		return k;
	}

}