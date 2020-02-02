package codingtest;

import java.util.ArrayList;
import java.util.Scanner;

public class jongi {
	public static int box[][] = new int[10][10];
	public static int checklist[]= {0,5,5,5,5,5};
	public static int res = 1000000;

	
	public static void sol(int y, int x, int count) {
		if (x>=10) {
			sol(y+1, 0, count);
			return;
		}
		if(y>=10) {
			res = Math.min(count, res);
			return;
		}
		if(box[y][x]==0) {
			sol(y,x+1,count);
			return;
		}
		
		for(int size=5; size>0; size--) {
			if(y+size>10 || x+size>10 || checklist[size]<=0) {
				continue;
			}
			int flag =0;;
			for (int i = y; i < y+size ; i++) {
				for(int j =x; j<x+size; j++) {
					if (box[i][j]==0) {
						flag =1;
						break;
					}
				}
				if(flag ==1) break;
			}
			if (flag==0) {
				checklist[size]--;
				for(int i=0; i<size; i++) {
					for (int j = 0; j < size; j++) {
						box[y+i][x+j]=0;
					}
				}
				sol(y,x+size,count+1);
				for(int i=0; i<size; i++) {
					for (int j = 0; j < size; j++) {
						box[y+i][x+j]=1;
					}
				}
				checklist[size]++;
			}
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				box[i][j] = sc.nextInt();
			}
		}
		
		sol(0,0,0);
		if (res ==1000000) System.out.println(-1);
		else System.out.println(res);
		
	}

}
