package bruteforce;

import java.util.Scanner;

//테트로미노
//https://www.acmicpc.net/problem/14500
//풀이법 : 주어진 모양 5개,회전된모양 배열로 만들어놓고 다 비교해주기
public class acmicpc_14500 {
	static int [][] map;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		map = new int [N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}//입력 끝
		
		//1자모양
		int [] I = {1,1,1,1};
		int [][] I_turn = {{1},{1},{1},{1}};
		
		//정사각형 모양
		int [][] square = {{1,1},{1,1}};
		
		//L자 모양
		int [][] L = {{1,0	},{1,0},{1,1}};
		int [][] L_turn1 = {{1,1,1},{1,0,0}};
		int [][] L_turn2 = {{1,1},{0,1},{0,1}};
		int [][] L_turn3 = {{0,0,1},{1,1,1}};
		
		int [][] L_reverse = {{0,1	},{0,1},{1,1}};
		int [][] L_reverse_turn1 = {{1,0,0},{1,1,1}};
		int [][] L_reverse_turn2 = {{1,1},{1,0},{1,0}};
		int [][] L_reverse_turn3 = {{1,1,1},{0,0,1}};
		
		//번개모양
		int [][] thunder = {{1,0},{1,1},{0,1}};
		int [][] thunder_turn = {{0,1,1},{1,1,0}};
		int [][] thunder_reverse = {{0,1},{1,1},{1,0}};
		int [][] thunder_reverse_turn = {{1,1,0},{0,1,1}};
		
		//T자모양
		int [][] T = {{1,1,1},{0,1,0}};
		int [][] T_turn1 = {{0,1},{1,1},{0,1}};
		int [][] T_turn2 = {{0,1,0},{1,1,1}};
		int [][] T_turn3 = {{1,0},{1,1},{1,0}};

		compare(I); compare(I_turn);
		compare(square);
		compare(L); compare(L_turn1); compare(L_turn2); compare(L_turn3);
		compare(L_reverse); compare(L_reverse_turn1); compare(L_reverse_turn2); compare(L_reverse_turn3);
		compare(thunder); compare(thunder_turn); compare(thunder_reverse); compare(thunder_reverse_turn);
		compare(T); compare(T_turn1); compare(T_turn2); compare(T_turn3);
		System.out.println(max);
	}//end main
	public static void compare(int[][] arr) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (j+arr[0].length > map[i].length || i+arr.length > map.length) continue;
				int sum = 0;
				int idx_i = i;
				for (int k = 0; k < arr.length; k++) {
					int idx_j = j;
					for (int l = 0; l < arr[k].length; l++) {
//						System.out.println(k+" "+l+" "+idx_i+" "+idx_j);
						sum+= map[idx_i][idx_j++]*arr[k][l];
					}
					idx_i++;
				}
//				System.out.println(sum);
				if (max < sum) max = sum;
			}
		}
	}
	public static void compare(int[] a) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (j+a.length > map[i].length) continue;
				int sum = 0;
				int idx = j;
				for (int k = 0; k < a.length; k++) {
					sum+= map[i][idx++]*a[k];
				}
//				System.out.println(sum);
				if (max < sum) max = sum;
			}
		}
	}//end compare with 1 array
	
}//end class
