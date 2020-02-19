package 백준;

 

import java.io.FileNotFoundException;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Scanner;

 

public class problem17143낚시왕 {

 

	static int row;

	static int col;

	

	static int top;

 

	public static void main(String[] args) throws FileNotFoundException {

 

		Scanner sc = new Scanner(System.in);

 

		row = sc.nextInt() + 1;

		col = sc.nextInt() + 1;

 

		int shark = sc.nextInt();

 

		int arr[][] = new int[row][col];

 

		Shark[] hm = new Shark[10001];

		top = 0;

 

		for (int i = 0; i < shark; i++) {

 

			int row1 = sc.nextInt();

			int col1 = sc.nextInt();

			int v1 = sc.nextInt();

			int dir = sc.nextInt();

			int size1 = sc.nextInt();

 

			if (dir == 1 || dir == 2) {

				v1 %= (row - 2) * 2;

			} else if (dir == 3 || dir == 4) {

				v1 %= (col - 2) * 2;

			}

			hm[++top] = new Shark(row1, col1, v1, dir, size1);

		}

 

		// 상어 위치 배열에 넣기

		

		for (int i = 1; i <= top; i++) {

			arr[hm[i].row][hm[i].col] = i;

		}

		

	

			

 

		int sum = 0;

		for (int j = 1; j < col; j++) {// 오른쪽으로 이동

 

			// 잡는다.

			for (int i = 0; i < row; i++) {

				if (arr[i][j] != 0) {

					sum += hm[arr[i][j]].size;

 

//					System.out.println(arr[i][j]);

//					System.out.println(hm.get(arr[i][j]).size);

 

					hm[arr[i][j]].size = 0;

					break;

				}

			}

			

			// 이동시킨다.

			hm = move(hm);

 

			arr = new int[row][col];// 초기화

 

//			 상어 위치 배열에 넣기

//			ArrayList<Integer> remove = new ArrayList<>();

 

			for (int i = 1; i <= top; i++) {

				if(hm[i].size != 0) {

					

					int row = hm[i].row;

					int col = hm[i].col;

 

					if (arr[row][col] == 0) {

						arr[row][col] = i;

					} else {

						if (hm[arr[row][col]].size > hm[i].size) {

							

							hm[i].size = 0;

						}

						else if (hm[arr[row][col]].size < hm[i].size) {

							hm[arr[row][col]].size = 0;

							arr[row][col] = i;

						}

					}

					

				}

				

			}

 

		}

 

		System.out.println(sum);

	}

 

	public static Shark[] move(Shark[] hm) {

 

		for (int i = 1; i <= top; i++) {

			for (int j = 0; j < hm[i].v; j++) {

 

				int dx = hm[i].row;

				int dy = hm[i].col;

 

				if (hm[i].dir == 1) {// 위

					if (dx == 1) {

						hm[i].dir_x *= -1;

						hm[i].dir_y *= -1;

						hm[i].dir = 2;

					}

				} else if (hm[i].dir == 2) {// 아래

					if (dx == row - 1) {

						hm[i].dir_x *= -1;

						hm[i].dir_y *= -1;

						hm[i].dir = 1;

					}

				}

				if (hm[i].dir == 3) {// 오른쪽

					if (dy == col - 1) {

						hm[i].dir_x *= -1;

						hm[i].dir_y *= -1;

						hm[i].dir = 4;

					}

				}

				if (hm[i].dir == 4) {// 왼

					if (dy == 1) {

						hm[i].dir_x *= -1;

						hm[i].dir_y *= -1;

						hm[i].dir = 3;

					}

				}

				hm[i].row = hm[i].row + hm[i].dir_x;

				hm[i].col = hm[i].col + hm[i].dir_y;

			}

		}

		return hm;

	}

}

 

class Shark {

	int row;

	int col;

	int v;

	int dir;

	int dir_x;

	int dir_y;

	int size;

	

	public Shark(int row, int col, int v, int dir, int size) {

		super();

		this.row = row;

		this.col = col;

		this.v = v;

		this.size = size;

		this.dir = dir;

 

		if (dir == 1) {// 위

 

			dir_x = -1;

			dir_y = 0;

 

		} else if (dir == 2) {// 아래

 

			dir_x = 1;

			dir_y = 0;

 

		} else if (dir == 3) {// 오른쪽

 

			dir_x = 0;

			dir_y = 1;

 

		} else if (dir == 4) {// 왼쪽

 

			dir_x = 0;

			dir_y = -1;

 

		}

 

	}

 

	@Override

	public String toString() {

		return "Shark [row=" + row + ", col=" + col + ", v=" + v + ", dir=" + dir + ", dir_x=" + dir_x + ", dir_y="

				+ dir_y + ", size=" + size + "]";

	}

 

}