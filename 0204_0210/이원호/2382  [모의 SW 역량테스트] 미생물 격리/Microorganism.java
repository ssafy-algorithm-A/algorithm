package beackj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Microorganism {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("input.txt"));

		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int size = sc.nextInt();
			int time = sc.nextInt();
			int cluster = sc.nextInt();
			// (상: 1, 하: 2, 좌: 3, 우: 4)

			String arr[][] = new String[size][size];
			Micro mi_arr[] = new Micro[cluster];

			// 초기화
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					if (i == 0 || i == arr.length - 1 || j == 0 || j == arr.length - 1) {
						arr[i][j] = "M";
					} else {
						arr[i][j] = ".";
					}
				}
			}

			for (int i = 0; i < cluster; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				mi_arr[i] = new Micro(i + "", x, y, sc.nextInt(), sc.nextInt());

				arr[x][y] = mi_arr[i].name;
			}
			// 초기화,입력 받아오기 끝

			System.out.println("init");
			for (String[] a : arr)
				System.out.println(Arrays.toString(a));
			System.out.println();

			for (Micro m : mi_arr)
				System.out.println(m.toString());

			for (int times = 1; times <= time; times++) {

				System.out.println(times);
				for (int i = 0; i < mi_arr.length; i++) {

					if (mi_arr[i].number != 0) {
						int x = mi_arr[i].x;
						int y = mi_arr[i].y;

						int dir_x = mi_arr[i].dir_arr[0];
						int dir_y = mi_arr[i].dir_arr[1];

						if (arr[x + dir_x][y + dir_y].equals("M")) {
							mi_arr[i].number /= 2;
							mi_arr[i].dir_arr[0] *= -1;
							mi_arr[i].dir_arr[1] *= -1;

							mi_arr[i].x = x + dir_x;
							mi_arr[i].y = y + dir_y;

							if (arr[x][y].equals(mi_arr[i].name))
								arr[x][y] = ".";
							
							System.out.println( i +" M 들어감");
						} else if (!arr[x + dir_x][y + dir_y].equals(".")) {

							if (Integer.valueOf(mi_arr[i].name) < Integer.valueOf(arr[x + dir_x][y + dir_y])) {
								arr[x + dir_x][y + dir_y] = mi_arr[i].name;

								mi_arr[i].x = x + dir_x;
								mi_arr[i].y = y + dir_y;

								if (arr[x][y].equals(mi_arr[i].name))
									arr[x][y] = ".";
								
							} else if (Integer.valueOf(mi_arr[i].name) > Integer.valueOf(arr[x + dir_x][y + dir_y])){

								Micro preMi = mi_arr[Integer.valueOf(arr[x + dir_x][y + dir_y])];

								if (preMi.number > mi_arr[i].number) {
									mi_arr[i].dir_arr[0] = preMi.dir_arr[0];
									mi_arr[i].dir_arr[1] = preMi.dir_arr[1];
								}

								mi_arr[i].number += preMi.number;

								preMi.number = 0;

								arr[x + dir_x][y + dir_y] = mi_arr[i].name;
								mi_arr[i].x = x + dir_x;
								mi_arr[i].y = y + dir_y;

								if (arr[x][y].equals(mi_arr[i].name))
									arr[x][y] = ".";
								
								System.out.println(preMi.name + i +"합쳐짐");
								
							}
						} else {

							arr[x + dir_x][y + dir_y] = mi_arr[i].name;

							mi_arr[i].x = x + dir_x;
							mi_arr[i].y = y + dir_y;

							if (arr[x][y].equals(mi_arr[i].name))
								arr[x][y] = ".";

						}
					}

				}

				for (String[] a : arr)
					System.out.println(Arrays.toString(a));
				System.out.println();

				for (Micro m : mi_arr)
					System.out.println(m.toString());
			}

			int sum = 0;
			for (Micro m : mi_arr)
				sum += m.number;

			System.out.printf("#%d %d\n", test_case, sum);

		}

	}

}

class Micro {
	String name;
	int x;
	int y;
	int number;
	int[] dir_arr = new int[2];
	int dir;

	public Micro(String name, int x, int y, int number, int dir) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
		this.number = number;
		this.dir = dir;

		if (dir == 1) {
			this.dir_arr[0] = -1;
			this.dir_arr[1] = 0;
		} else if (dir == 2) {
			this.dir_arr[0] = 1;
			this.dir_arr[1] = 0;

		} else if (dir == 3) {
			this.dir_arr[0] = 0;
			this.dir_arr[1] = -1;

		} else if (dir == 4) {
			this.dir_arr[0] = 0;
			this.dir_arr[1] = 1;

		}

	}

	@Override
	public String toString() {

		String d = "";

		if (dir == 1) {
			d = "위";
		} else if (dir == 2) {
			d = "아래";
		} else if (dir == 3) {
			d = "좌";
		} else if (dir == 4) {
			d = "우";
		}
		return "Micro [name=" + name + ", x=" + x + ", y=" + y + ", number=" + number + ", dir_arr="
				+ Arrays.toString(dir_arr) + d + "]";
	}

}
