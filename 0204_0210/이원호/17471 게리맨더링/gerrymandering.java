package beackj;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class gerrymandering {

	static int arr[][];

	static int weight[];

	static int sum;

	static int min = 999;

	static int size;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		size = sc.nextInt() + 1;

		weight = new int[size];

		for (int i = 1; i < size; i++) {

			weight[i] = sc.nextInt();

			sum += weight[i];

		}

		arr = new int[size][size];

		for (int i = 1; i < size; i++) {

			int s = sc.nextInt();

			for (int j = 0; j < s; j++) {

				int a = sc.nextInt();

				arr[i][a] = 1;

				arr[a][i] = 1;

			}

		}

		for (int i = 1; i <= size - 1; i++) {
			
//			System.out.printf("%d ################################\n",i);
			boolean check[] = new boolean[size];

			dfs(check.clone(), i);

		}

		if (min == 999) {

			System.out.println(-1);

		} else {

			System.out.println(min);

		}

	}

	public static void dfs(boolean[] check, int s) {

		int n = check.length - 1;

		Stack<Integer> st = new Stack<Integer>();

		int arr1[] = new int[n];

		int ar1Top = -1;

		int count = 0;

		st.push(s);

		count++;

		check[s] = true;

		arr1[++ar1Top] = s;

		Result k = new Result();

		for (int i = 1; i <= n; i++) {
			if (check[i] == false) {
				k = dfs_sub(check.clone(), i);
				break;
			}
		}

		int sumsub = 0;

		if (count + k.count == size - 1) {
			for (int i = 0; i < arr1.length; i++) {
				sumsub += weight[arr1[i]];
			}
			int dif = Math.abs(Math.abs(sum - sumsub) - sumsub);
			min = Math.min(min, dif);
		}
		
//		System.out.println("Arr1 Array");
//		System.out.println(Arrays.toString(arr1));
//		System.out.println("K Array");
//		System.out.println(Arrays.toString(k.gu));
//		System.out.println("count : "+count);
//		System.out.println("k count : "+k.count);

//		System.out.println();

		while (!st.isEmpty()) {

			int v = st.peek();

			boolean falg = false;

			for (int i = 1; i <= n; i++) {

				if (arr[v][i] == 1 && !check[i]) {

					st.push(i);

					count++;

					arr1[++ar1Top] = i;
					
					
					for (int j = 1; j <= n; j++) {
						if (!check[j]) {

							k = dfs_sub(check.clone(), j);

							sumsub = 0;

							if (count + k.count == size - 1) {

								for (int ii = 0; ii < arr1.length; ii++) {
									sumsub += weight[arr1[ii]];
								}
								int dif = Math.abs(Math.abs(sum - sumsub) - sumsub);
								min = Math.min(min, dif);
							}
//							System.out.println("Arr1 Array");
//							System.out.println(Arrays.toString(arr1));
//							System.out.println("count : "+count);
//							System.out.println("k : "+k.count);
//							System.out.println("K Array");
//							System.out.println(Arrays.toString(k.gu));
//							System.out.println();
						}
						
						
					}

					check[i] = true;

					falg = true;

					boolean flagGo = false;
					

					
					if (flagGo) {}
				}

			}

			if (!falg) {

				st.pop();

			}

		}

	}

	public static Result dfs_sub(boolean[] check, int s) {

		int n = check.length - 1;

		Stack<Integer> st = new Stack<Integer>();

		int arr1[] = new int[n];

		int ar1Top = -1;

		int count = 0;

		st.push(s);

		count++;

		check[s] = true;

		arr1[++ar1Top] = s;

		while (!st.isEmpty()) {

			int v = st.peek();

			boolean falg = false;

			for (int i = 1; i <= n; i++) {

				if (arr[v][i] == 1 && !check[i]) {

					st.push(i);

					count++;

					arr1[++ar1Top] = i;

					check[i] = true;

					falg = true;

					break;

				}

			}

			if (!falg) {

				st.pop();

			}

		}

		return new Result(count, arr1);

	}

}

class Result {

	int count;

	int[] gu;

	public Result(int count, int[] gu) {

		super();
		this.count = count;
		this.gu = gu;

	}

	public Result() {
		super();
	}

}
