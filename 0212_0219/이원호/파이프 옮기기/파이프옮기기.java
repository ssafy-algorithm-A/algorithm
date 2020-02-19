package 백준;

 

import java.io.File;

import java.io.FileNotFoundException;

import java.util.Arrays;

import java.util.Scanner;

 

public class problem17070파이프옮기기 {

 

	static int size;

 

	static int count;

 

	static boolean arr[][];

 

	public static void main(String[] args) throws FileNotFoundException {

 

		Scanner sc = new Scanner(System.in);

 

		size = sc.nextInt() + 1;

		arr = new boolean[size][size];

 

		for (int i = 1; i < arr.length; i++) {

			for (int j = 1; j < arr.length; j++) {

				int input = sc.nextInt();

				if (input == 1) {

					arr[i][j] = true;

				}

			}

		}

 

 

 

		Pipe p = new Pipe(1, 2, 1);

 

		move(p);

 

		System.out.println(count);

 

		sc.close();

 

	}

 

	private static void move(Pipe p) {

 

		

		int x = p.x;

		int y = p.y;

 

		if (x == size - 1 && y == size - 1) {

			count++;

			return;

		} else {

 

			if (p.dir == 1) {// 가로

 

				for (int i = 0; i < 2; i++) {

					if (i == 0) {

 

						int dx = x;

						int dy = y + 1;

 

						if (dx >= size || dy >= size)// 0보다 작아질일 없으니까 뒤로만 검사

							continue;

 

						if (check(new Pipe(dx, dy, p.dir), 1)) {// 가로

 

							p.x = dx;

							p.y = dy;

							p.dir = 1;

							move(p);

						}

					} else {

						int dx = x + 1;

						int dy = y + 1;

 

						if (dx >= size || dy >= size)

							continue;

 

						if (check(new Pipe(dx, dy, p.dir), 2)) {// 대각선

							p.x = dx;

							p.y = dy;

							p.dir = 3;

							move(p);

						}

					}

 

				}

 

			} else if (p.dir == 2) {// 세로

 

				for (int i = 0; i < 2; i++) {

					if (i == 0) {

 

						int dx = x + 1;

						int dy = y;

 

						if (dx >= size || dy >= size)

							continue;

 

						if (check(new Pipe(dx, dy, p.dir), 1)) {// 세로

							p.x = dx;

							p.y = dy;

							p.dir = 2;

							move(p);

						}

					} else {

 

						int dx = x + 1;

						int dy = y + 1;

 

						if (dx >= size || dy >= size)

							continue;

						if (check(new Pipe(dx, dy, p.dir), 2)) {// 대각선

							p.x = dx;

							p.y = dy;

							p.dir = 3;

							move(p);

						}

					}

				}

 

			} else if (p.dir == 3) {// 대각선

 

				for (int i = 0; i < 3; i++) {

					if (i == 0) {

 

						int dx = x;

						int dy = y + 1;

 

						if (dx >= size || dy >= size)

							continue;

 

						if (check(new Pipe(dx, dy, p.dir), 1)) {// 가로

							p.x = dx;

							p.y = dy;

							p.dir = 1;

							move(p);

						}

					} else if (i == 1) {

 

						int dx = x + 1;

						int dy = y;

 

						if (dx >= size || dy >= size)

							continue;

 

						if (check(new Pipe(dx, dy, p.dir), 1)) {// 세로

							p.x = dx;

							p.y = dy;

							p.dir = 2;

							move(p);

						}

					} else {

 

						int dx = x + 1;

						int dy = y + 1;

 

						if (dx >= size || dy >= size)

							continue;

 

						if (check(new Pipe(dx, dy, p.dir), 2)) {// 대각선

							p.x = dx;

							p.y = dy;

							p.dir = 3;

							move(p);

						}

					}

				}

 

			}

		}

 

	}

 

	private static boolean check(Pipe p, int i) {

 

		if (i == 1) {// 하나만 체크

			if (arr[p.x][p.y] == false) {

				return true;

			} else {

				return false;

			}

 

		} else {// 끝점 기준으로 위 옆 체크

 

			if (arr[p.x][p.y] == false && arr[p.x - 1][p.y] == false && arr[p.x][p.y - 1] == false) {

				return true;

			} else {

				return false;

			}

 

		}

	}

}

 

class Path {

	int x;

	int y;

 

	@Override

	public String toString() {

		return "Path [x=" + x + ", y=" + y + "]";

	}

 

	public Path(int x, int y) {

		super();

		this.x = x;

		this.y = y;

	}

 

}

 

class Pipe {

	int x;

	int y;

	int dir;

 

	public Pipe(int x, int y, int dir) {

		super();

		this.x = x;

		this.y = y;

		this.dir = dir;

	}

 

	@Override

	public String toString() {

		return "Pipe [x=" + x + ", y=" + y + ", dir=" + dir + "]";

	}

 

}