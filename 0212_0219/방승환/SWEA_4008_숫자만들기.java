
import java.util.Scanner;

public class SWEA_4008_숫자만들기 {
	
	static int N, maxVal, minVal;
	static int[] operator, operand;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			operator = new int[4];
			operand = new int[N];
			
			for(int i=0; i<4; i++) operator[i] = sc.nextInt();
			for(int i=0; i<N; i++) operand[i] = sc.nextInt();
		
			maxVal = Integer.MIN_VALUE;
			minVal = Integer.MAX_VALUE;
			
			recur(1, operand[0]);
			
			System.out.println("#" + tc + " " + (maxVal-minVal));
		}
	}
	
	public static void recur(int depth, int result) {
		if(depth >= N) {
			maxVal = Math.max(result, maxVal);
			minVal = Math.min(result, minVal);
			return;
		}
		
		for(int i=0; i<4; i++) {
			
			if(operator[i] <= 0) continue;
			
			operator[i]--;
			
			switch(i) {
			case 0:
				recur(depth+1, result + operand[depth]);
				break;
			case 1:
				recur(depth+1, result - operand[depth]);
				break;
			case 2:
				recur(depth+1, result * operand[depth]);
				break;
			case 3:
				recur(depth+1, result / operand[depth]);
				break;
			}
			operator[i]++;
		}
	}
	
}
