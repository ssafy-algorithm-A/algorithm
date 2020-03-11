package 백준;

/*
 * memory : 30036KB
 * time : 308ms
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class problem17822원판돌리기 {
	static int R, C, num;
	static int[][] arr;
	static int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();

		arr = new int[R + 1][C];// 판순서 맞추려고 1 더함

		num = sc.nextInt();

		for (int i = 1; i <= R; i++) {// 첫번째 판부터 뿌리기
			for (int j = 0; j < C; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		for (int k = 0; k < num; k++) {
			move();// 판 돌리기
			boolean c = delete();// 같은거 삭제
			if (!c) {
				addAction();// 삭제한게 없으면 평균내서 +- 해주는거
			}

		}

		System.out.println(result());

		sc.close();
	}

	private static void move() {

		int cBoard = sc.nextInt();
		int cDir = sc.nextInt();
		int move = sc.nextInt() % C;

		for (int i = 1; i <= R; i++) {
			if (i % cBoard == 0) {// 판 선택
				// 방향선택
				if (cDir == 0) {// 시계방향
					for (int j = 0; j < move; j++) {
						int tmp = arr[i][C - 1];
						for (int step = C - 1; step > 0; step--) {//앞에서 뒤로 밀기
							arr[i][step] = arr[i][step - 1];
						}
						arr[i][0] = tmp;
					}
				} else if (cDir == 1) {// 반시계방향
					for (int j = 0; j < move; j++) {
						int tmp = arr[i][0];
						for (int step = 0; step < C - 1; step++) {//뒤에서 앞으로 밀기
							arr[i][step] = arr[i][step + 1];
						}
						arr[i][C - 1] = tmp;
					}
				}

			}
		}

	}

	private static boolean delete() {//bfs로 같은거 지우기

		boolean change = false;

		for (int i = 1; i <= R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] != 0) {

					Queue<Point> q = new LinkedList<>();
					int tmp = arr[i][j];
					arr[i][j] = 0;
					q.add(new Point(i, j));
					boolean flag = false;

					while (!q.isEmpty()) {
						Point p = q.poll();

						for (int d = 0; d < dir.length; d++) {
							int dx = p.x + dir[d][0];
							int dy = p.y + dir[d][1];

							//원판이라 처음이랑 끝이 연결
							
							if (dx < 1 && p.x != 1) {//첫번째 판과 마지막판은 연결 X
								dx = R;
							}
							if (dy < 0) {
								dy = C - 1;
							}
							if (dx > R && p.x != R) {//첫번째 판과 마지막판은 연결 X
								dx = 1;
							}
							if (dy >= C) {
								dy = 0;
							}

							if (dx < 1 || dy < 0 || dx > R || dy >= C)//예외 체크
								continue;

							if (arr[dx][dy] == tmp) {
								q.add(new Point(dx, dy));
								arr[dx][dy] = 0;
								flag = true;
							}
						}
					}
					if (flag) {
						arr[i][j] = 0;

						if (!change)
							change = true;

					} else {
						arr[i][j] = tmp;
					}

				}
			}
		}

		return change;

	}

	private static void addAction() {
		double sum = 0;
		double count = 0;

		for (int i = 1; i <= R; i++) {//0이 아닌수를 처음부터 관리하는게 더 좋을듯
			for (int j = 0; j < C; j++) {
				if (arr[i][j] != 0) {
					sum += arr[i][j];
					count++;
				}
			}
		}
		
		double avg = sum / count;//평균

		for (int i = 1; i <= R; i++) {//더하거나 빼주기
			for (int j = 0; j < C; j++) {
				if (arr[i][j] != 0) {
					if (arr[i][j] < avg) {
						arr[i][j]++;
					} else if (arr[i][j] > avg) {
						arr[i][j]--;
					}
				}
			}
		}

	}

	private static int result() {

		int sum = 0;

		for (int i = 1; i <= R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] != 0) {
					sum += arr[i][j];
				}
			}
		}

		return sum;
	}

}

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

}
