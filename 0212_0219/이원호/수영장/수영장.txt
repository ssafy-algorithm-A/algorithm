import java.io.File;

import java.io.FileNotFoundException;

import java.util.Scanner;

 

public class Solution {

	static int day, month, quart, year;

	static int allDay, allMonth;

 

	static int arr[];

 

	static int min;

 

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

 

		int T = sc.nextInt();

 

		for (int test_case = 1; test_case <= T; test_case++) {

 

			min = Integer.MAX_VALUE;

 

			day = sc.nextInt();

			month = sc.nextInt();

			quart = sc.nextInt();

			year = sc.nextInt();

 

			arr = new int[13];

			for (int i = 1; i < arr.length; i++) {

				int k = sc.nextInt();

				arr[i] = k;

				allDay += k;

				if (k != 0) {

					allMonth++;

				}

				;

			}

 

			pool(0, 0, 0, 0, 1);

			System.out.printf("#%d %d\n", test_case, Math.min(min, year));

//			System.out.println(Arrays.toString(arr));

//			System.out.println(allDay);

//			System.out.println(allMonth);

 

		}

		sc.close();

	}

 

	private static void pool(int q, int m, int d, int sum, int idx) {

		if (13 <= idx) {

			min = Math.min(min, sum);

			return;

 

		} else {

			pool(q, m, d + 1, sum + (day * arr[idx]), idx + 1);

			pool(q, m + 1, d, sum + month, idx + 1);

			if (idx <= 10)

				if (arr[idx] !=0 || arr[idx + 1] !=0 || arr[idx + 2]!=0)

					pool(q + 1, m, d, sum + quart, idx + 3);

		}

 

	}

 

}