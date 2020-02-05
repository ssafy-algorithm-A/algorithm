import java.util.LinkedList;
import java.util.Scanner;

public class Baekjoon17471_게리맨더링 {
	static int[][] map; // 인접행렬
	static int[] people; // 사람 수를 담기위한 배열

	static int answer=1001;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		map = new int[N + 1][N + 1];
		people = new int[N + 1];

		for (int i = 1; i < people.length; i++)
			people[i] = sc.nextInt();
		

		for (int i = 1; i < map.length; i++) {
			int linkNum = sc.nextInt();
			for (int j = 0; j < linkNum; j++) {
				int vertex = sc.nextInt();
				map[i][vertex] = 1;
			}
		}

		makeSet();
		
		if(answer == 1001)
			System.out.println(-1);
		else
			System.out.println(answer);

	}
	
	static void makeSet() {
		// makeSet으로 모든 경우의 조합을 생성한후
		for (int i = 1; i < 1<< map.length - 1; i++) {
			boolean[] arr = new boolean[map.length];
			String str = Integer.toBinaryString(i);
			int setNum = 0;
			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(str.length() - j - 1) == '1') {
					arr[j+1] = true;
					setNum++;
				}
			}
			bfs(arr, setNum); // bfs에 넣어 탐색
		}
	} // end of makeSet

	
	/**
	 * 현재 만들어진 set과 나머지 set들이 연결되는지 확인하고
	 * 인구수 비교하여 최소값을 넣는 함수
	 */
	static void bfs(boolean[] arr, int setNum) {
		LinkedList<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[map.length];
		
		for (int i = 1; i < arr.length; i++) {
			if (arr[i]) {
				q.add(i);
				visited[i] = true;
				break;
			}
		}
		
		int linkedNum = 1;
		int curPeople = 0;
		
		while (!q.isEmpty()) {
			int curIdx = q.poll();
			curPeople += people[curIdx];

			for (int i = 1; i < map.length; i++) {
				if (!visited[i] && map[curIdx][i] == 1 && arr[i]) {
					q.add(i);
					visited[i] = true;
					linkedNum++;					
				} // end of if
			} // end of for
		}
		
		if(linkedNum != setNum)
			return;
		
		
		for (int i = 1; i < arr.length; i++) {
			if (!arr[i]) {
				q.add(i);
				visited[i] = true;
				break;
			}
		}
		
		int linkedNum2 = 1;
		int curPeople2 = 0;
		
		while (!q.isEmpty()) {
			int curIdx = q.poll();
			curPeople2 += people[curIdx];

			for (int i = 1; i < map.length; i++) {
				if (!visited[i] && map[curIdx][i] == 1 && !arr[i]) {
					q.add(i);
					visited[i] = true;
					linkedNum2++;					
				} // end of if
			} // end of for
		}
		
		if(linkedNum+linkedNum2 == map.length-1) {
			if(curPeople > curPeople2) {
				if(answer > curPeople-curPeople2)
					answer = curPeople-curPeople2;
			}
			else {
				if(answer > curPeople2-curPeople)
					answer = curPeople2-curPeople;
			}
		}
	} // end of bfs	
}