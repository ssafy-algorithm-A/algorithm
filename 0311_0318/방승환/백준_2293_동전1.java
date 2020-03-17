import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

// 2293 동전 1
public class 백준_2293_동전1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int [] DP = new int[k+1];
		int [] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		DP[0] = 1;
		
		for(int i=0; i<n; i++) {
			for(int j=1; j<=k; j++) {
				if(j>=arr[i]) DP[j] += DP[j-arr[i]];
			}
		}
		System.out.println(DP[k]);
	}
}
