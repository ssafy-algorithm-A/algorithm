import java.util.Scanner;

public class BaekJoon_17070_파이프옮기기1 {
	
	static int N, answer;
	static int[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		recur(0,1,0);
		// d -> 0 : 가로, 1 : 세로, 2 : 대각선
		
		System.out.println(answer);
	}
	
	public static void recur(int x, int y, int d) {
		
		if(x == N-1 && y == N-1 && map[x][y] != 1) {
			answer++;
			return;
		}
		
		// 현재 놓여있는 막대가 가로일 때
		if(d == 0) {
			// 오른쪽으로 한칸 움직이기
			if(isInside(x, y+1) && map[x][y+1] == 0) {
				recur(x, y+1, 0);
			}
			
			// 오른쪽 아래 대각선으로 움직이기
			if(isInside(x+1, y+1) && map[x+1][y+1] == 0 && map[x+1][y] == 0 && map[x][y+1] == 0) {
				recur(x+1, y+1, 2); 
			}
			
		} else if(d == 1) { // 막대가 세로 일 때
			// 밑으로 움직이기
			if(isInside(x+1, y) && map[x+1][y] == 0) {
				recur(x+1, y, 1);
			}
			
			// 오른쪽 아래로 움직이기
			if(isInside(x+1, y+1) && map[x+1][y+1] == 0 && map[x][y+1] == 0 && map[x+1][y] == 0) {
				recur(x+1, y+1, 2);
			}
			
		} else if(d == 2) {	// 막대가 대각선일 때
			// 가로로 움직이기
			if(isInside(x, y+1) && map[x][y+1] == 0) {
				recur(x, y+1, 0);
			}
			
			// 세로로 움직이기
			if(isInside(x+1, y) && map[x+1][y] == 0) {
				recur(x+1,y, 1);
			}
			
			// 대각선으로 움직이기
			if(isInside(x+1, y+1) && map[x+1][y+1] == 0 && map[x][y+1] == 0 && map[x+1][y] == 0) {
				recur(x+1,y+1,2);
			}	
		}
	}
	
	public static boolean isInside(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	
}
