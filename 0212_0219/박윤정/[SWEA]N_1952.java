package SWEA;

import java.util.Scanner;

public class N_1952 {
	static int[] cost;
	static int min;
	static int[] plan;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			cost = new int[4];
			for (int i = 0; i < cost.length; i++) {
				cost[i] = sc.nextInt();
			}
			
			plan = new int[12];
			for (int i = 0; i < plan.length; i++) {
				plan[i] = sc.nextInt();
			}
			
			min = cost[3]; // 1년치비용으로 초기화
			go(0, 0);
			System.out.println("#" + test_case + " " +min);
		}
	}
	
	public static void go(int month,int sum) {
		if(month >= 12) {
			min = Math.min(min, sum);
			return;
		}
		if(plan[month] == 0) {
			go(month+1, sum);
			return;
		}
		for (int i = 0; i < cost.length-1; i++) {
			if(i == 0) { // 하루
				go(month+1, sum + (plan[month] * cost[i]) );
			}else if(i == 1) {
				go(month+1, sum + (cost[i]));
			}else if(i == 2) {
				go(month+3, sum + cost[i]);
			}
		}
	}

}
