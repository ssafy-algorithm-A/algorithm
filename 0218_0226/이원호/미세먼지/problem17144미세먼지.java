package beackj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import org.w3c.dom.ranges.RangeException;

/*
7 8 1
0 0 0 0 0 0 0 9
0 0 0 0 3 0 0 8
-1 0 5 0 0 0 22 0
-1 8 0 0 0 0 0 0
0 0 0 0 0 10 43 0
0 0 5 0 15 0 0 0
0 0 40 0 0 0 20 0
 */

public class problem17144미세먼지 {
	static int row, col, t;

	static int[][] arr;

	static int dir_x[] = { 0, -1, 0, 1 };
	static int dir_y[] = { -1, 0, 1, 0 };

	static int machine[][] = new int[2][2];
	static int macSize = 0;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		row = sc.nextInt();
		col = sc.nextInt();
		t = sc.nextInt();

		arr = new int[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int k = sc.nextInt();
				arr[i][j] = k;

				if (k == -1) {
					machine[macSize][0] = i;
					machine[macSize][1] = j;
					macSize++;
				}
			}
		}

		for (int i = 0; i < t; i++) {
			spread();
			upCleaner();
			downCleaner();
		}
		
		System.out.println(sumAll());
		
		sc.close();
	}

	public static int sumAll() {
		int sum = 2;
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				sum += arr[i][j];
			}
		}
		
		
		return sum;
	}

	public static void printArr(String s) {
		System.out.println(s);
		for (int a[] : arr)
			System.out.println(Arrays.toString(a));
		System.out.println();
	}

	static public void copy(int[][] c) {

		for (int i = 0; i < row; i++) {
			arr[i] = c[i].clone();
		}

	}

	static public void upCleaner() {

		int[] dir_x = { 0, -1, 0, 1 };
		int[] dir_y = { 1, 0, -1, 0 };
		int dircount = 0;

		int x = machine[0][0];
		int y = machine[0][1];
		int tmp = 0;

		y++;
		tmp = arr[x][y];
		arr[x][y] = 0;

		while (true) {
			try {
				int c = 0;
				x = x + dir_x[dircount];
				y = y + dir_y[dircount];

				if (arr[x][y] == -1)
					break;

				c = arr[x][y];
				arr[x][y] = tmp;
				tmp = c;

			} catch (ArrayIndexOutOfBoundsException e) {
				x = x - dir_x[dircount];
				y = y - dir_y[dircount];
				dircount++;
			}

		}

	}

	static public void downCleaner() {

		int[] dir_x = { 0, 1, 0, -1 };
		int[] dir_y = { 1, 0, -1, 0 };
		int dircount = 0;

		int x = machine[1][0];
		int y = machine[1][1];
		int tmp = 0;

		y++;
		tmp = arr[x][y];
		arr[x][y] = 0;

		while (true) {
			try {
				int c = 0;
				x = x + dir_x[dircount];
				y = y + dir_y[dircount];

				if (arr[x][y] == -1)
					break;

				c = arr[x][y];
				arr[x][y] = tmp;
				tmp = c;

			} catch (ArrayIndexOutOfBoundsException e) {
				x = x - dir_x[dircount];
				y = y - dir_y[dircount];
				dircount++;
			}

		}

	}

	static public void spread() {

		int[][] clone = new int[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				if (arr[i][j] != 0) {
					int size = arr[i][j];
					int spreadSize = size / 5;
					int count = 0;

					for (int k = 0; k < dir_x.length; k++) {
						int dx = i + dir_x[k];
						int dy = j + dir_y[k];

						if (spreadSize == 0)
							break;

						if (dx < 0 || dy < 0 || dx >= row || dy >= col || arr[dx][dy] == -1)
							continue;
						else {
							clone[dx][dy] += spreadSize;
							count++;
						}
					}
					clone[i][j] += size - (spreadSize * count);
				}
			}
		}

		copy(clone);

	}

}

class Dust {
	int x;
	int y;
	int size;

	public Dust(int x, int y, int size) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
	}

	@Override
	public String toString() {
		return "Dust [x=" + x + ", y=" + y + ", size=" + size + "]";
	}

}
