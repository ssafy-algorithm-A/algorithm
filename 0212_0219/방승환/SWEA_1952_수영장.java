import java.util.Scanner;

public class SWEA_1952_수영장 {
	
	static int[] cost, month;
	static int min;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			cost = new int[4];
			month = new int[12];
			min = Integer.MAX_VALUE;
			
			for(int i=0; i<4; i++) 
				cost[i] = sc.nextInt();
			
			for(int i=0; i<12; i++) 
				month[i] = sc.nextInt();
			
			
			recur(0, 0);
			
			System.out.println("#" + tc + " " + Math.min(min, cost[3]));
			
		}
	}
	
	public static void recur(int current, int total) {
		if(current >= 12) {
			min = Math.min(total, min);
			return;
		} else {
			// 1일 이용권을 사용했을 때
			recur(current+1, total + month[current] * cost[0]);
			
			// 1달 이용권을 사용했을 때
			recur(current+1, total + cost[1]);
			
			// 3달 이용권을 사용했을 때
			recur(current+3, total + cost[2]);
		}
	}
}
