import java.util.Scanner;

class BaekJoon_16234_인구이동 {

	static int N, L, R, COUNT=0, total, union, people;
	static int[][] arr;
	static boolean[][] visited, visited2;
	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static boolean flag;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		flag = true;

		while (flag) {
			COUNT++;
			flag = false;
			visited = new boolean[N][N];
			visited2 = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && !visited2[i][j]) {
						for (int k = 0; k < 4; k++) {
							int nx = i + dir[k][0];
							int ny = j + dir[k][1];
							if (isInside(nx, ny) && !visited[nx][ny] && !visited2[nx][ny] && Math.abs(arr[i][j] - arr[nx][ny]) >= L
									&& Math.abs(arr[i][j] - arr[nx][ny]) <= R) {
//								System.out.println("----------" + arr[i][j] + " 에서 DFS 진행중----------" + " 현재시간 : " + COUNT);
								total = 0; // 연합의 인구수
								union = 0; // 연합을 이루고 있는 칸의 개수
								DFS(i, j);
								people = total / union;
								DFS2(i, j);
								flag = true;
//								print();
								break;
							}
						}
					}
				}
			}
			
		}

		System.out.println(COUNT-1);
	}

	public static void DFS2(int x, int y) {
		visited2[x][y] = true;
		arr[x][y] = people;

		for (int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];

			if (isInside(nx, ny) && visited[nx][ny] && !visited2[nx][ny]) {
				DFS2(nx, ny);
			}
		}

	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void DFS(int x, int y) {
		visited[x][y] = true;
		total += arr[x][y];
		union++;

		for (int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];

			if (isInside(nx, ny) && !visited[nx][ny] && Math.abs(arr[x][y] - arr[nx][ny]) >= L && Math.abs(arr[x][y] - arr[nx][ny]) <= R) {
				DFS(nx, ny);
			}
		}
	}

	public static boolean isInside(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < N)
			return true;
		else
			return false;
	}
}
