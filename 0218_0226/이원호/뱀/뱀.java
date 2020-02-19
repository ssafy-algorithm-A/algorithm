package beackj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class problem3190뱀 {

	static int size, apple_num;
	static int arr[][];
	static Queue<Dot> q = new LinkedList<Dot>();

	static int[] dir_x = { -1, 0, 1, 0 };
	static int[] dir_y = { 0, 1, 0, -1 };

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(new File("input.txt"));

		size = sc.nextInt() + 2;

		apple_num = sc.nextInt();

		arr = new int[size][size];
		// arr[1][1] = 1;
		q.add(new Dot(1, 1, 1));

		for (int i = 0; i < apple_num; i++) {
			arr[sc.nextInt()][sc.nextInt()] = 2;
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
					arr[i][j] = 9;
				}
			}
		} // 입력 받기
//		printArr(arr, "init");

		int num = sc.nextInt();

		int cnt = 0;

		startLoop: for (int i = 0; i < num; i++) {
			
			int c = cnt;

			int p = sc.nextInt();
			for (int t = 0; t < p - c; t++) {// 해당 방향으로 p초 움직이기
				cnt++;
				int[][] clone = cloneArr();

				int qsize = q.size();
				boolean sizeUp = false;//꼬리 늘리는 플래그
				
				int headDir = q.peek().dir;
				boolean dirChange = true;
				
				
				for (int k = 0; k < qsize; k++) {
					
					
					Dot d = q.poll();
					
					int x = d.x;
					int y = d.y;
					int dir = d.dir;
					
					if(dirChange) {
						if(headDir != d.dir) {
							dir = headDir;
							dirChange = false;
						}
					}

					

					int dx = x + dir_x[d.dir];
					int dy = y + dir_y[d.dir];

					if (arr[dx][dy] == 9 || arr[dx][dy] == 1) {
						break startLoop;
					}

					clone[dx][dy] = 1;
					q.add(new Dot(dx, dy, dir));

					if (arr[dx][dy] == 2) {
						arr[dx][dy] = 0;
						sizeUp = true;
					}
					if (sizeUp && k == qsize - 1) {
						arr[dx][dy] = 0;
						clone[x][y] = 1;// 이거 잘못함
						q.add(new Dot(x, y, dir));
						sizeUp = false;
					}

				}
				printArr(clone, cnt + "");
			}
			char dir = sc.next().charAt(0);
			q.peek().dir = changeDir(q.peek(), dir);
			System.out.println("############################");
		}
		System.out.println(cnt);
		sc.close();
	}

	public static int[][] cloneArr() {

		int[][] arrClone = new int[size][size];

		for (int i = 0; i < size; i++) {
			arrClone[i] = arr[i].clone();
		}
		return arrClone;

	}

	public static void printArr(int[][] arr, String s) {
		System.out.println(s);
		for (int a[] : arr)
			System.out.println(Arrays.toString(a));
		System.out.println();
	}

	public static int changeDir(Dot d, char dir) {

		if (dir == 'D') {// 오른쪽
			d.dir++;
			if (d.dir == 4)
				d.dir = 0;

		} else if (dir == 'L') {// 왼쪽
			d.dir--;
			if (d.dir == -1)
				d.dir = 3;
		}
		return d.dir;
	}
}

class Dot {
	int x;
	int y;
	int dir;

	public Dot(int x, int y, int dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}