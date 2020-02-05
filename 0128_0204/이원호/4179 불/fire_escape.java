package beackj;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class fire_escape {

	static Fire[] fire_stack = new Fire[100];

	static int fire_top = -1;

	static int dir_x[] = { 0, -1, 0, 1 };

	static int dir_y[] = { -1, 0, 1, 0 };

	static char arr[][];

	static Human human;

	static int count = 0;
	
	
	

	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);

		int x = sc.nextInt();

		int y = sc.nextInt();

		arr = new char[x + 2][y + 2];

		for (char c[] : arr)//벽을 주위에 세운다
			Arrays.fill(c, 'E');

		for (int i = 1; i < arr.length - 1; i++) {

			String s = sc.next();

			for (int j = 1; j < arr[0].length - 1; j++) { //fire_stack과 human에 정보를 담는다.

				arr[i][j] = s.charAt(j - 1);

				if (arr[i][j] == 'F')

					fire_stack[++fire_top] = new Fire(i, j);

				else if (arr[i][j] == 'J')

					human = new Human(i, j);

			}

		}

		while (true) {

			if (j_move() == -1) {
				count++;
				System.out.println(count);
				break;
			}

			else if (j_move() == -2) {
				System.out.println("IMPOSSIBLE");
				break;
			}

		}

		sc.close();

	}

	public static int j_move() {

		int x = human.x;

		int y = human.y;

		int count_fire = 0;

		for (int k = 0; k < dir_x.length; k++) {

			if (arr[x + dir_x[k]][y + dir_y[k]] == '.') {

				arr[x][y] = '.';

				human.x = x + dir_x[k];

				human.y = y + dir_y[k];

				arr[human.x][human.y] = 'J';

				count++;

				fire_move();
//				show();
				j_move();

			}

			else if (arr[x + dir_x[k]][y + dir_y[k]] == 'F' || arr[x + dir_x[k]][y + dir_y[k]] == '#') {
				count_fire++;
			}

			else if (arr[x + dir_x[k]][y + dir_y[k]] == 'E') {
				return -1;
			}

		}

		if (count_fire == 4) {
			return -2;
		}

		return 0;

	}

	public static void fire_move() {

		Fire[] fire_tmp = new Fire[fire_top + 1];

		for (int i = 0; i <= fire_top; i++) {

			fire_tmp[i] = fire_stack[i];// 꺼내서 넣으면서 낮추기

		}

		fire_top = -1;

		for (int i = 0; i < fire_tmp.length; i++) {

			int x = fire_tmp[i].x;

			int y = fire_tmp[i].y;

			for (int k = 0; k < dir_x.length; k++) {

				if (x + dir_x[k] >= 1 && y + dir_y[k] >= 1 && x + dir_x[k] < arr.length - 1
						&& y + dir_y[k] < arr[0].length - 1) {// 조건

					if (arr[x + dir_x[k]][y + dir_y[k]] == '.') {// 벽이아니면
						arr[x + dir_x[k]][y + dir_y[k]] = 'F';
						fire_stack[++fire_top] = new Fire(x + dir_x[k], y + dir_y[k]);

					}

				}

			}

		}

	}

	public static void show() {

		for (char c[] : arr)

			System.out.println(Arrays.toString(c));

		System.out.println();

	}

}

class Human {

	int x;

	int y;

	public Human(int x, int y) {

		super();

		this.x = x;

		this.y = y;

	}

}

class Fire {

	int x;

	int y;

	public Fire(int x, int y) {

		super();

		this.x = x;

		this.y = y;

	}

	@Override

	public String toString() {

		return "Fire [x=" + x + ", y=" + y + "]";

	}

}