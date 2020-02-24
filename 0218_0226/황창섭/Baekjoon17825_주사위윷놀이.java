import java.util.Scanner;

// 164ms 16624kb
public class Baekjoon17825_주사위윷놀이 {
	private static int answer;
	private static int[] dice; // 주사위 눈 정보
	private static Horse[] horse; // 말의 점수와 위치
	private static Node[] node; // map의 형태
	private static boolean[] visited; // 현재 위치에 말이 있는지 없는지 확인

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
			this.next = next; // 빨간 화살표 방향
			this.next2 = next2; // 파란 화살표 방향
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

		// 3개의 파란색 칸, next2 : 파란 화살표 방향 노드
		node[5].next2 = node[21]; // val 10->13
		node[10].next2 = node[28]; // val 20->22
		node[15].next2 = node[27]; // val 30->28

		//// 도착 이전의 노드 ////
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
		if (diceIdx == 10) { // 주사위를 10번 전부 굴렸을 경우
			int sum = 0;
			// 말들이 모은 점수 합산
			for (int i = 0; i < horse.length; i++) {
				sum += horse[i].totalNum;
			}
			if (answer < sum)
				answer = sum;
		} else {
			for (int j = 0; j < horse.length; j++) {
				if (horse[j].x == node[32]) { // 현재 말의 위치가 도착지점일 경우
					continue;
				}

				Node k = horse[j].x; // 현재 말의 위치 가져오기 (1)
				visited[k.index] = false; // 현재 말의 위치 발판 false (2)

				// 말의 이동 시작이 파란색 칸의 경우
				if (k == node[5] || k == node[10] || k == node[15]) {
					horse[j].x = moveDice(k, dice[diceIdx], true);
				} else {
					horse[j].x = moveDice(k, dice[diceIdx], false);
				}
				// horse[j].x 에는 이제 주사위 눈 만큼 이동한 위치가 저장된다
				
				// 이동한 위치에 다른말이 있을 경우
				if (visited[horse[j].x.index]) {
					visited[k.index] = true; // (2)
					horse[j].x = k; // 이동하기 전 위치로 복귀 (1) 
					continue;
				}

				// 이동에 성공했을 경우
				horse[j].totalNum += horse[j].x.val; // 현재 발판의 값 합치기
				if (horse[j].x != node[32]) { // 도착 발판은 여러 말이 들어올 수 있으므로
					visited[horse[j].x.index] = true;  // 도착 발판이 아닌 경우만 true 표시
				}
				dfs(diceIdx + 1);
				visited[k.index] = true; // 원본 복원 (2)
				visited[horse[j].x.index] = false; // 원본 복원
				horse[j].totalNum -= horse[j].x.val; // 원본 복원
				horse[j].x = k; // 원본 복원 (1)

			}

		}
	}

	/**
	 * @param k 현재 노드 위치
	 * @param moveCnt 남은 움직임 수
	 * @param check next로 갈지 next2로 갈지 확인하지 위한 변수
	 * @return
	 */
	public static Node moveDice(Node k, int moveCnt, boolean check) {
		if (moveCnt == 0) {
			return k;
		}

		if (check) { // 파란색 칸에서 시작했을 경우
			k = moveDice(k.next2, moveCnt - 1, false);
		} else { // 빨간 화살표를 따라가야하는 경우
			if (k == node[32]) { // 현재 위치가 도착위치일 경우
				return k;
			} else {
				k = moveDice(k.next, moveCnt - 1, false);
			}
		}

		return k;
	}

}