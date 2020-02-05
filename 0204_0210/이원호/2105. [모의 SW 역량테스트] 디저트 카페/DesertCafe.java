package beackj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class DesertCafe {

	static int arr[][];

	static int dirX[] = { -1, -1, 1, 1 };
	static int dirY[] = { -1, 1, 1, -1 };
	
	static int startX;
	static int startY;

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(new File("input.txt"));

		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			int size = sc.nextInt();

			arr = new int[size][size];

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			for (int a[] : arr)
				System.out.println(Arrays.toString(a));

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					
					startX = i;
					startY = j;

					Stack<Cafe> st = new Stack<Cafe>();
					boolean check[][] = new boolean[size][size];
					
					
					find_desert(i, j, check, st);
				}
			}

		}
		sc.close();
	}

	public static void find_desert(int x, int y, boolean[][] check, Stack<Cafe> st) {

		if(!st.isEmpty()) {
		}
		
		for (int i = 0; i < dirX.length; i++) {
			int dx = x + dirX[i];
			int dy = y + dirY[i];
			
			

			if (dx < 0 || dy < 0 || dx >= arr.length || dy >= arr.length || check[dx][dy] == true)
				continue;

			else {
				st.add(new Cafe(x, y, i,arr[x][y]));
				check[dx][dy] = true;
				find_desert(dx,dy,check,st);
			}
		}
		
		for(Cafe c : st)
			System.out.println(c.toString());
		System.out.println();

	}

}

class Cafe {
	int x;
	int y;
	int dir;
	int value;

	public Cafe(int x, int y, int dir,int value) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Cafe [x=" + x + ", y=" + y + ", dir=" + dir + ", value=" + value + "]";
	}

	
}
