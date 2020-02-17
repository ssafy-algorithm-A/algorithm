import java.util.ArrayList;
import java.util.Scanner;

public class BaekJoon_17143_낚시왕 {
	
	static int R, C, M, result;
	static int[][] map, dir = {{0,0},{-1,0},{1,0},{0,1},{0,-1}};	// 북 남 동 서
	static Shark[] sharks;
	static int[] change_dir = {0,2,1,4,3};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();
		map = new int[R][C];
		sharks = new Shark[M+1];
	
		for(int i=1; i<=M; i++) {
			int r = sc.nextInt()-1; // 상어 위치 (r, c)
			int c = sc.nextInt()-1;
			int s = sc.nextInt(); // 속력
			int d = sc.nextInt() ; // 이동 방향
			int z = sc.nextInt(); // 크기
			map[r][c] = i;
			sharks[i] = new Shark(r, c, s, d, z);
		}

		// 1. 낚시왕이 한칸씩 움직인다.		
		for(int i=0; i<C; i++) {
			
//			System.out.println("현재 낚시왕이 있는 열의 번호 : " + i);
			// 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
			catching(i);
			
			// 맵을 초기화하고 상어 한마리씩 움직이면서 지도에 표시한다.
			map = new int[R][C];
			
			// 3. 상어가 이동한다.
			moving();
			
			
		}
		
		System.out.println(result);
	}	

	public static void moving() {
		// 1 : 북, 2 : 남, 3 : 동, 4 : 서
		
		for(int i=1; i<=M; i++) {
			if(sharks[i] == null) continue;
			else {
				Shark s = sharks[i];
				int nx = s.r;
				int ny = s.c;
				int direction = s.d;
				int speed = s.s;
				
				if(direction == 1 || direction == 2) speed %= (R*2-2); 
				else if(direction == 3 || direction == 4) speed %= (C*2-2);
					
				for(int j=0; j<speed; j++) {
					nx += dir[direction][0];
					ny += dir[direction][1];
					
					if(nx < 0 || ny < 0 || nx > R-1 || ny > C-1) {
						nx -= dir[direction][0];
						ny -= dir[direction][1];	
						
						direction = change_dir[direction];
						
						nx += dir[direction][0];
						ny += dir[direction][1];
						
						continue;
					}
					
					if( ((direction == 1 || direction == 2) && (nx == 0 || nx == R-1))
							|| ((direction == 3 || direction == 4) && (ny == 0 || ny == C-1))){
						direction = change_dir[direction];
					}
				}
				
				if(map[nx][ny] != 0) {
					if(sharks[map[nx][ny]].z > s.z) { // 겹친 자리에 있는 상어의 크기가 현재 상어의 크기보다 크면
						sharks[i] = null;	// 현재 상어가 잡아먹힘
						continue;
					} else { // 겹친 자리에 있는 상어의 크기가 현재 상어의 크기보다 작으면
						sharks[map[nx][ny]] = null;
					}
				}				
				
				map[nx][ny] = i;
				sharks[i] = new Shark(nx, ny, s.s, direction, s.z);
				
			}
		}
		
		
	}
	
	// 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
	public static void catching(int y) {
		
		// 낚시왕이 있는 열에서 가장 가까운 상어를 잡는다.
		for(int i=0; i<R; i++) {
			if(map[i][y] != 0) {
//				System.out.println("현재 잡은 상어의 크기 : " + sharks[map[i][y]].z);
				result += sharks[map[i][y]].z;
				sharks[map[i][y]] = null;
				return;
			}
		}
	}
	
	public static boolean isInside(int x, int y) {
		return x>=0 && x<R && y>=0 && y<C;
	}
}

class Shark {
	int r; // 상어 위치 (r, c)
	int c;
	int s; // 속력
	int d; // 이동 방향
	int z; // 크기
	
	Shark(int r, int c, int s, int d, int z) {
		this.r=r;
		this.c=c;
		this.s=s;
		this.d=d;
		this.z=z;
	}
}
