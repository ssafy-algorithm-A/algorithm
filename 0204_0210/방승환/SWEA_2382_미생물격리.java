
import java.util.ArrayList;
import java.util.Scanner;

// 미생물 격리

public class SWEA_2382_미생물격리 {

	static int N, M, K, result;
	// dir[1]:상, [2]하, [3]좌, [4]우
	static int[][] map, dir = {{0,0},{-1,0},{1,0},{0,-1},{0,1}};
	static int[] change_dir = {0, 2, 1, 4, 3}; 
	static Pair[] micro;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			result = 0;
			N = sc.nextInt(); // 셀의 개수 
			M = sc.nextInt(); // 격리 시간
			K = sc.nextInt(); // 미생물 군집의 개수 
			micro = new Pair[K+1];
			
			// d -> 1 2 3 4  상 하 좌 우
			for(int i=1; i<=K; i++) {
				int r = sc.nextInt();
				int c = sc.nextInt();
				int n = sc.nextInt();
				int dir = sc.nextInt();
				micro[i] = new Pair(r, c, n, dir, true);
			}
			
			// M시간 돌리기
			for(int minute=0; minute<M; minute++) {
				
				// 일단 한칸씩 움직이기
				for(int i=1; i<=micro.length-1; i++) {
					Pair p = micro[i];
					if(!p.alive) continue;
					int nx = p.x + dir[p.d][0];
					int ny = p.y + dir[p.d][1];
					micro[i] = new Pair(nx, ny, p.n, p.d, p.alive);
				}
				
				// 움직여진 군집들
				for(int i=1; i<=micro.length-1; i++) {
					Pair p = micro[i];
					
					if(!p.alive) continue;
					
					// 약품이 칠해진 부분으로 움직인 상태일 때
					if(isOutside(p.x, p.y)) {
						micro[i].n /= 2;
						if(micro[i].n == 0) micro[i].alive = false;
						micro[i].d = change_dir[p.d];
						
					} else {	// 약품이 안칠해진 곳이면

						
						// 같은 좌표에 있을시 가장 큰 미생물의 수와 더해진 미생물의 수값을 구하기 위한 변수 
						int max = p.n;
						int sum = p.n;
						int idx = i;

						for(int j=i+1; j<=micro.length-1; j++) {
							if(!micro[j].alive) continue;
							// 현재 좌표와 같은 좌표를 찾아서
							if(micro[j].x == p.x && micro[j].y == p.y) {
								// 미생물수를 비교한다.
								if(max > micro[j].n) {	// 현재 좌표의 미생물 수가 많으면
									// 미생물을 더하고 그 좌표의 군집을 죽인다.
									sum += micro[j].n;
									micro[j].alive = false;	
								} else {	// 다른 좌표의 미생물 수가 많으면
									// sum에 미생물 수를 더하고 max값을 그 좌표의 미생물 수로 바꾼다.
									// 방향도 큰 쪽의 방향으로 저장한다
									// 현재 좌표의 군집은 죽인다.
									sum += micro[j].n;
									max = micro[j].n;
									micro[idx].alive = false;
									idx = j;
								}
							} 
						}
						// 반복문을 빠져나오면 sum 에는 같은좌표에 있는 군집 미생물들이 모두 더해서 저장되어 있고
						// idx 에는 군집중에 가장 미생물수가 많은 군집이 저장되어 있다.
						// 그러므로 idx 인덱스에 있는 군집에 sum을 저장해준다.
						micro[idx].n = sum;
					}
//					System.out.println(i + " 번째 군집의 현재 상태 : " + " -----------> p.x : " + p.x + " / p.y :" + p.y + " / p.n : " + p.n + " / p.d : " + p.d);
				}
//				System.out.println();
			}
			
			for(int i=1; i<=micro.length-1; i++) {
				if(micro[i].alive) result += micro[i].n;
			}
			
			System.out.println("#" + tc + " " + result);
		}
	}
	
	
	public static boolean isOutside(int x, int y) {
		return x==0||y==0||x==N-1||y==N-1;
	}
	
	public static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}

class Pair {
	int x;
	int y;
	int n;
	int d;
	boolean alive;
	
	Pair (int x, int y, int n, int d, boolean alive) {
		this.x=x;
		this.y=y;
		this.n=n;
		this.d=d;		
		this.alive = alive;
	}
}
