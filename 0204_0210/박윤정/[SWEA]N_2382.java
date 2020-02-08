package SSAFY;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class N_2382 {
	public static int[][] dot = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();

			Queue<Monster> queue = new LinkedList<>();
			for (int i = 0; i < K; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int num = sc.nextInt();
				int dir = sc.nextInt(); // 상1 하2 좌3 우4
				queue.add(new Monster(x, y, num, dir));
			}

			for (int i = 0; i < M; i++) {
				PriorityQueue<Monster> pq = new PriorityQueue<>();
				while (!queue.isEmpty()) {
					Monster m = queue.poll();
					m.x += dot[m.dir - 1][0];
					m.y += dot[m.dir - 1][1];

					if (m.x == 0 || m.y == 0 || m.x == N - 1 || m.y == N - 1) { // 약품 칠해진 부분
						if (m.num == 1) { // 1마리가 약품으로 가면 죽음
							continue;
						}
						m.num /= 2;

						if (m.dir == 1 || m.dir == 3) {
							m.dir += 1;
						} else {
							m.dir -= 1;
						}
					}

					pq.add(m);
				}

				HashMap<String, Monster> hm = new HashMap<>();
				while (!pq.isEmpty()) { // 겹치는 것 죽이기
					Monster m = pq.poll();
					if (hm.containsKey(m.x + "," + m.y)) {
						Monster bigm = hm.get(m.x + "," + m.y);
						bigm.num += m.num;
						hm.put(m.x + "," + m.y, bigm);
					} else {
						hm.put(m.x + "," + m.y, m);
					}
				}
				queue = new LinkedList<>(hm.values());
			}
			
			int sum = 0;
			for(Monster m : queue) {
				sum += m.num;
			}
			System.out.println("#" + test_case + " " + sum);
		}
	}

	public static class Monster implements Comparable<Monster> {
		int x, y, num, dir;

		public Monster(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}

		public int compareTo(Monster m) {
			if (this.num > m.num) {
				return -1;
			} else if (this.num < m.num) {
				return 1;
			}

			return 0;
		}
	}
}
