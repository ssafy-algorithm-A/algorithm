package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N_2579 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] score = new int[n];
		for (int i = 0; i < n; i++) {
			score[i] = Integer.parseInt(br.readLine());
		}
		
		
		int[] stair = new int[n];
		stair[0] = score[0];
		if(n > 1) { // n이 1보다 작은 경우 처리
			stair[1] = Math.max(stair[0]+score[1], score[1]);
		}
		if(n > 2) { // n이 2보다 작은 경우 처리
			stair[2] = Math.max(score[0]+score[2], score[1]+score[2]);
			for (int i = 3; i < n; i++) {
				stair[i] = Math.max(stair[i-2]+score[i], stair[i-3]+ score[i-1] + score[i]);				
			}
		}
		
		System.out.println(stair[n-1]);
	}

}
