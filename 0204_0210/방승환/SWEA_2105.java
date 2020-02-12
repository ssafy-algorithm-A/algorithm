import java.util.Scanner;
import java.util.ArrayList;

public class SWEA_2105 {

	static int[][] map;
	static int N, len, answer;
	static int[] check;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			
			N = sc.nextInt();
			answer = Integer.MIN_VALUE;
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			for(int i=0; i<N-2; i++) {
				for(int j=1; j<N-1; j++) {
					for(int d1=1; d1<=j; d1++) {
						for(int d2=1; d2<N-j; d2++) {
							// i, j : 1번좌표
							// i+d1, j-d1 : 2번좌표
							// i+d2, j+d2 : 3번좌표
							// 2번의 x좌표 + d2, 3번의 y좌표 - d1 : 4번좌표 
							
							if(isInside(i+d1, j+d2) && isInside(i+d1+d2, j+d2-d1)) {
								check = new int[101];
								len = 0;
								
								if(touring(i, j, d1, d2)) answer = Math.max(answer, len);
//								if(len == 6) {
//									System.out.print(i + " " + j + " / ");
//									System.out.print((i+d1) + " " + (j-d1) + " / ");
//									System.out.print((i+d2) + " " + (j+d2)  + " / ");
//									System.out.print((i+d1+d2) + " " + (j+d2-d1) + " \n");
//								}
//								System.out.println(len);

							}
						}
					}
				}
			}
			if(answer == Integer.MIN_VALUE) answer = -1;
			System.out.println("#" + tc + " " + answer);
		}
	}
	
	public static boolean touring(int x, int y, int d1, int d2) {
		int x2 = x+d1, y2 = y-d1;
		int x3 = x+d2, y3 = y+d2;
		int x4 = x2+d2, y4 = y3-d1;
		
		// 1번에서 2번
		for(int i=0; i<d1; i++) {
			if(check[map[x+i][y-i]] == 0) {
				check[map[x+i][y-i]]++;
				len++;
			} else return false;
		}
		
		// 4번에서 3번
		for(int i=0; i<d1; i++) {
			if(check[map[x4-i][y4+i]] == 0 ) {
				check[map[x4-i][y4+i]]++;
				len++;
			} else return false;
		}
		
		// 2번에서 4번
		for(int i=0; i<d2; i++) {
			if(check[map[x2+i][y2+i]] == 0) {
				check[map[x2+i][y2+i]]++;
				len++;
			} else return false;
		}
		
		// 3번에서 1번
		for(int i=0; i<d2; i++) {
			if(check[map[x3-i][y3-i]] == 0) {
				check[map[x3-i][y3-i]]++;
				len++;
			} else return false;
		}
		
		return true;
	}
	
	public static boolean isInside(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}

}
