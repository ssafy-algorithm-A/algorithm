
package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bac17471 {

	static int arr[];
	static int visited[][];
	static boolean vertex_visited[];
	static boolean visited_BFS[];
	static boolean vertex[][];
	static int N;

	static int False_L;
	static int True_L;
	static int answer =Integer.MAX_VALUE;
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// 초기화, N = 지역수, M = 해당 지역 연결점
		N = sc.nextInt();
		visited = new int[N + 1][N + 1];
		arr = new int[N + 1];
		vertex = new boolean[N + 1][N + 1];
		vertex_visited = new boolean[N + 1];
		visited_BFS = new boolean[N + 1];

		// 인원수
		for (int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}

		// 맵핑
		for (int i = 1; i <= N; i++) {
			int M = sc.nextInt();
			for (int j = 1; j <= M; j++) {
				int j_tmp = sc.nextInt();

				vertex[i][j_tmp] = true;
				vertex[j_tmp][i] = true;
			}
		}

		// 조합
		// 1부터~10까지의 지역의 조합을 구하자.
		solution();
		if(answer == Integer.MAX_VALUE)
			answer = -1;
		System.out.println(answer);

	}

	// 중복되지않게하기
	public static void solution() {
		Combination(1);
	}

	public static void Combination(int start) {

		if (start == (N + 1)) {
			False_L = Integer.MAX_VALUE;
			True_L = Integer.MAX_VALUE;

			//모두 이어지는 선거구 일경우
			if (isLinked(true) && isLinked(false)) {
				//System.out.println("끝끝" +True_L + "-" +  False_L);
				answer = Math.min(answer, Math.abs(True_L - False_L));
				//System.out.println(answer);
				return;
			}

			return;
		}

		// 이 정점을 가지고 갈지 안가지고 갈지.
		vertex_visited[start] = true;
		Combination(start + 1);
		vertex_visited[start] = false;
		Combination(start + 1);

	}

	//5 2 3 4 1 2
	// true는 첫번째 선거구, false는 두번째 선거구
	public static boolean isLinked(boolean AB) {
		boolean result = true;
		
		// AL리스트에 나누어진 조합으로 BFS돌리기 위함.
		ArrayList<Integer> AL_idx = new ArrayList<>();

		// 도시들을 추가.
		for (int i = 1; i <= N; i++) {
			if (vertex_visited[i] == AB)
				AL_idx.add(i);
		}

		/*System.out.println(AB);
		for (int i : AL_idx) {
			System.out.print("-" + i + " ");
		}
		System.out.println();
		System.out.println();*/
		

		// AL_idx가 없을 경우
		if (AL_idx.size() == 0 || AL_idx.size() == N)
			return false;

		Arrays.fill(visited_BFS, false);
		result = BFS(AL_idx, AB);

		return result;
	}

	public static Boolean BFS(ArrayList<Integer> AL , boolean AB) {
		boolean result = true;
		int count = 0;
		Queue<Integer> Qu = new LinkedList<>();
		Qu.clear();
		//System.out.println(AB + " -- " + AL);
		// 시작점
		Qu.add(AL.get(0));
		visited_BFS[AL.get(0)] = true;
		
		
		while (!Qu.isEmpty()) {
			// 방문점은 true로
			int src = Qu.poll(); //src는 123456~
			//System.out.print(src + " ");
			count += arr[src];
			//System.out.print(count + "-" + src + " ");
			
			
			for (int j = 0; j < AL.size(); j++) {
				if (vertex[src][AL.get(j)]  && !visited_BFS[AL.get(j)]) {// 연결되있다면
					Qu.add(AL.get(j));
					visited_BFS[AL.get(j)] = true;
				}
			}
		}

		for (int i=0; i<AL.size(); i++) {
			if (!visited_BFS[AL.get(i)]) {
				
				result = false;
				count = Integer.MAX_VALUE;
			}
		}
		
		//True | False
		if(AB) True_L = count;
		if(!AB) False_L = count;
		
		//System.out.println(AB + " : " + result + "-" + count);
		
		return result;
	}

	class Side {
		int src;
		int dsc;

		Side(int src, int dsc) {
			this.src = src;
			this.dsc = dsc;
		}
	}

}