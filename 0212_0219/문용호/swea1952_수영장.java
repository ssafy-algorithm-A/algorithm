package algo;

import java.util.Scanner;

public class swea1952_수영장 {
	static int [] ticket;
	static int [] month;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			ticket = new int [4];
			month = new int [12];
			for (int i = 0; i < ticket.length; i++) {
				ticket[i] = sc.nextInt();
			}
			for (int i = 0; i < month.length; i++) {
				month[i] = sc.nextInt();
			}
			answer = Integer.MAX_VALUE;
			dfs(0,0);
			System.out.println("#"+t+" "+answer);
		}//end tc
	}//end main
	static int answer;
	public static void dfs(int index, int sum) {
		if (index >= 12) {
			answer = Math.min(answer, sum);
			return;
		}
		//1일짜리
		dfs(index+1,sum+(month[index]*ticket[0]));
		//1달짜리
		if (month[index] > 0) dfs(index+1,sum+ticket[1]);
		//3달짜리
		if (index <= 9) {
			if (month[index] + month[index+1]+month[index+2] > 0) dfs(index+3,sum+ticket[2]);
		}
		//1년짜리
		if (index == 0) dfs(index+12,sum+ticket[3]);
	}//end dfs
}//end class