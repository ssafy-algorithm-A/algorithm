package SWEA;

import java.util.Scanner;

public class N_4008 {
	public static int[] num;
	public static int[] operator;
	public static int max;
	public static int min;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			operator = new int[4];
			num = new int[N];
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			for (int i = 0; i < operator.length; i++) {
				operator[i] = sc.nextInt();
			}
			for (int i = 0; i < num.length; i++) {
				num[i] = sc.nextInt();
			}
			
			int[] count = new int[4];
			calculator(count,0,num[0]);
			System.out.println("#" + test_case + " " + (max-min));
		}
	}
	
	public static void calculator(int[] count,int index,int result) {
		if(index >= num.length-1) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		
		for (int i = 0; i < operator.length; i++) {
			if(count[i] != operator[i]) {
				count[i]++;
				if(i == 0) {
					calculator(count,index+1,result + num[index+1]);
				}else if(i == 1) {
					calculator(count,index+1,result - num[index+1]);
				}else if(i == 2) {
					calculator(count,index+1,result * num[index+1]);
				}else {
					calculator(count,index+1,result / num[index+1]);
				}
				count[i]--;
			}
		}
	}

}

