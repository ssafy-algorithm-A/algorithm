package SW;

 

import java.io.FileNotFoundException;

import java.util.Scanner;

 

public class problem4008숫자만들기2 {

 

	static int n;

	static int max;

	static int min;

 

	static int arr[];

 

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

 

		int T = sc.nextInt();

 

		for (int test_case = 1; test_case <= T; test_case++) {

 

			max = Integer.MIN_VALUE;

			min = Integer.MAX_VALUE;

 

			n = sc.nextInt();

 

			int s = sc.nextInt();

			int m = sc.nextInt();

			int mul = sc.nextInt();

			int div = sc.nextInt();

 

			arr = new int[n];

			for (int i = 0; i < arr.length; i++) {

				arr[i] = sc.nextInt();

			}

//			System.out.println(n);

//			System.out.printf("%d %d %d %d\n", s, m, mul, div);

//			System.out.println(Arrays.toString(arr));

 

			cal(s, m, mul, div, arr[0], 1);

 

			System.out.printf("#%d %d\n", test_case, max - min);

		}

		sc.close();

	}

 

	private static void cal(int s, int m, int mul, int div, int result, int index) {

//		System.out.println("n = " + n);

//		System.out.println("index :" + index);

//		System.out.printf("s : %d,m : %d,mul : %d,div : %d\n", s, m, mul, div);

//		System.out.println();

 

		if (n == index) {

			max = Math.max(max, result);

			min = Math.min(min, result);

			return;

		} else {

 

			for (int i = 0; i < 4; i++) {

 

				if (i == 0) {

					if (s > 0) {

						cal(s - 1, m, mul, div, result + arr[index], index + 1);

					}

				} else if (i == 1) {

					if (m > 0) {

						cal(s, m - 1, mul, div, result - arr[index], index + 1);

					}

 

				} else if (i == 2) {

					if (mul > 0) {

						cal(s, m, mul - 1, div, result * arr[index], index + 1);

					}

				} else if (i == 3) {

					if (div > 0) {

						cal(s, m, mul, div - 1, result / arr[index], index + 1);

					}

				}

 

			}

		}

	}

}