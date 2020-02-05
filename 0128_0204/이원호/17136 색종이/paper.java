package beackj;
 

import java.io.FileNotFoundException;


import java.util.Scanner;

 

public class paper {

	

	static int arr[][] = new int[10][10];

	

	static int paper[][][] = {

			{

				{1,1,1,1,1},

				{1,1,1,1,1},

				{1,1,1,1,1},

				{1,1,1,1,1},

				{1,1,1,1,1}

			},

			{

				{1,1,1,1},

				{1,1,1,1},

				{1,1,1,1},

				{1,1,1,1},

			},

			{

				{1,1,1},

				{1,1,1},

				{1,1,1}

			},

			{

				{1,1},

				{1,1}

			},

			{

				{1}

			}	

	};

	static int paper_size[] = {25,16,9,4,1};

	

	static Fitloc fitarr[] = new Fitloc[25];

	static int fittop = -1;

	

	static int total_paper[] = new int[5];

 

	public static void main(String[] args) throws FileNotFoundException {

		

		Scanner sc = new Scanner(System.in);

 

		int arr_clone[][] = new int[10][10];

		// 배열에 넣기

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = sc.nextInt();
				arr_clone[i][j] = arr[i][j];
			}
		}

		// 여기위에서 종이 크기에따른 크기조절을 해야하네
		// 배열에서 5x5검사
		int min = 100;

		for (int j = 0; j < paper.length; j++) {
			total_paper = new int[5];
			start(j);

			int sum = 0;

			for (int i = 0; i < total_paper.length; i++) {

				sum += total_paper[i];

 

				if (total_paper[i] > 5) {
					sum = -1;
					break;
				}
			}

			

			for (int i = 0; i < arr_clone.length; i++) {
				for (int k = 0; k < arr_clone.length; k++) {
					arr[i][k] = arr_clone[i][k];
				}
			}
			if(sum != -1) {
				min = Math.min(sum, min);
			}
		}
		if(min == 100) {
			min = -1;
		}
		System.out.println(min);
		sc.close();
	}

	

	

	public static void start(int start) {

		

		

		for (int select_paper = start; select_paper < paper.length; select_paper++) {

 

			for (int i = 0; i <= 10 - paper[select_paper].length; i++) {

				for (int j = 0; j <= 10 - paper[select_paper].length; j++) {

					fir_start(i,j,select_paper);

				}

			}

		} // end of paper for

	}

	

	public static void fir_start(int i,int j,int select_paper) {

 

		int count = 0;

		boolean flag = false;

		

			for (int paper_row = 0; paper_row < paper[select_paper].length; paper_row++) {

				for (int paper_col = 0; paper_col < paper[select_paper].length; paper_col++) {

					

					

					if (arr[i + paper_row][j + paper_col] == paper[select_paper][paper_row][paper_col]) {// 종이에

						fitarr[++fittop] = new Fitloc(i + paper_row, j + paper_col);// 스택에 쌓는다

					}

					else {

						flag=true;

					}

					

				}

				if(flag)break;

			}

 

			if (flag == false) {// 맞으면 0으로 바꾸기

 

				int top = fittop;

 

				for (int k = 0; k <= top; k++) {

					arr[fitarr[k].x][fitarr[k].y] = 0;

				}

 

				fittop = -1;

 

				total_paper[Math.abs(select_paper - 4)]++;

 

			} else {

				fittop = -1;// 값이 같지않아도 top은 초기화

			}

 

 

	

		

		

	}

}

 

class Fitloc{

	

	int x;

	int y;

	

	public Fitloc(int x, int y) {

		super();

		this.x = x;

		this.y = y;

	}

 

	@Override

	public String toString() {

		return "fit_loc [x=" + x + ", y=" + y + "]";

	}

		

	

}