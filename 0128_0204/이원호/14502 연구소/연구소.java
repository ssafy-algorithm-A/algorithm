package beackj;

import java.util.Scanner;

public class 연구소 {

	static Influ[] inf_ar;

	static int inf_top;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int row = sc.nextInt();

		int col = sc.nextInt();

		int arr[][] = new int[row][col];

		for (int i = 0; i < arr.length; i++) {

			for (int j = 0; j < arr[0].length; j++) {

				arr[i][j] = sc.nextInt();

			}

		}

		Wall[] wall_ar = new Wall[100];

		int wall_top = -1;

		inf_ar = new Influ[100];

		inf_top = -1;

		for (int i = 0; i < row; i++) {

			for (int j = 0; j < col; j++) {

				if (arr[i][j] == 0)

					wall_ar[++wall_top] = new Wall(i, j);

				else if (arr[i][j] == 2) {

					inf_ar[++inf_top] = new Influ(i, j);

				}

			}

		}

		int max = 0;

		for (int i = 0; i <= wall_top; i++) {
			for (int j = 0; j <= wall_top; j++) {
				if (i != j) {
					for (int k = 0; k <= wall_top; k++) {
						if (i != k && j != k) {
							max = Math.max(inf(copy_arr(arr), wall_ar[i], wall_ar[j], wall_ar[k], inf_ar), max);
						}
					}
				}
			}
		}

		System.out.println(max);

		sc.close();

	}

	static int[][] copy_arr(int[][] arr) {

		int copy_arr[][] = new int[arr.length][arr[0].length];

		for (int i = 0; i < copy_arr.length; i++) {
			for (int j = 0; j < copy_arr[0].length; j++) {
				copy_arr[i][j] = arr[i][j];
			}
		}
		return copy_arr;
	}

	static int inf(int[][] clone, Wall wall_ar, Wall wall_ar2, Wall wall_ar3, Influ[] inf_ar) {

		clone[wall_ar.getX()][wall_ar.getY()] = 1;
		clone[wall_ar2.getX()][wall_ar2.getY()] = 1;
		clone[wall_ar3.getX()][wall_ar3.getY()] = 1;

		int count = 0;

		for (int i = 0; i <= inf_top; i++) {

			infection(inf_ar[i].x, inf_ar[i].y, clone, 0);
			count = count_0(clone);
			if (count == 0) {
				break;
			}
		}

		return count;

	}

	static int count_0(int[][] clone) {

		int count = 0;

		for (int i = 0; i < clone.length; i++) {
			for (int j = 0; j < clone[i].length; j++) {
				if (clone[i][j] == 0)
					count++;
			}
		}

		return count;
	}

	static void infection(int x, int y, int[][] clone, int end) {

		int dir_x[] = { 0, -1, 0, 1 };

		int dir_y[] = { -1, 0, 1, 0 };

		for (int i = 0; i < dir_x.length; i++) {

			if (i == 0 && y + dir_y[i] >= 0) {

				if (clone[x + dir_x[i]][y + dir_y[i]] == 0) {

					clone[x + dir_x[i]][y + dir_y[i]] = 2;

					infection(x + dir_x[i], y + dir_y[i], clone, 0);

				}

			} else if (i == 1 && x + dir_x[i] >= 0) {

				if (clone[x + dir_x[i]][y + dir_y[i]] == 0) {

					clone[x + dir_x[i]][y + dir_y[i]] = 2;

					infection(x + dir_x[i], y + dir_y[i], clone, 0);

				}

			} else if (i == 2 && y + dir_y[i] < clone.length) {

				if (clone[x + dir_x[i]][y + dir_y[i]] == 0) {

					clone[x + dir_x[i]][y + dir_y[i]] = 2;

					infection(x + dir_x[i], y + dir_y[i], clone, 0);

				}

			} else if (i == 3 && x + dir_x[i] < clone.length) {

				if (clone[x + dir_x[i]][y + dir_y[i]] == 0) {

					clone[x + dir_x[i]][y + dir_y[i]] = 2;

					infection(x + dir_x[i], y + dir_y[i], clone, 0);

				}

			}
		}
		
		

	}

}

class Wall {

	int x;

	int y;

	public int getX() {

		return x;

	}

	public void setX(int x) {

		this.x = x;

	}

	public int getY() {

		return y;

	}

	public void setY(int y) {

		this.y = y;

	}

	public Wall(int x, int y) {

		super();

		this.x = x;

		this.y = y;

	}

	@Override

	public String toString() {

		return "Wall [x=" + x + ", y=" + y + "]";

	}

}

class Influ {

	int x;

	int y;

	public int getX() {

		return x;

	}

	public int getY() {

		return y;

	}

	public void setX(int x) {

		this.x = x;

	}

	public void setY(int y) {

		this.y = y;

	}

	public Influ(int x, int y) {

		super();

		this.x = x;

		this.y = y;

	}

	@Override

	public String toString() {

		return "Influ [x=" + x + ", y=" + y + "]";

	}

}