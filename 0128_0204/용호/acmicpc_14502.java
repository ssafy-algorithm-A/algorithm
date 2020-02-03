import java.util.Scanner;

public class acmicpc_14502 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int N,M;
	static int max = 0;
	static int [][] lab;			// 입력받은 원본
	static int [][] labcopy;		//바이러스 퍼뜨릴 공간
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		lab = new int [N][M];
		labcopy = new int [N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				lab[i][j] = sc.nextInt();
			}
		}
		wall(0,0);
		System.out.println(max);
	}
	//1. 벽 3개 세우기
	static void wall(int start,int count) {
		if (count == 3) {
			copy();
			for (int i = 0; i < labcopy.length; i++) {
				for (int j = 0; j < labcopy[i].length; j++) {
					if (labcopy[i][j] == 2) {
						dfs(i,j);
					}
				}
			}
			max = Math.max(max, safe());
			return;
		}//count = 3
		for (int i = start; i < N*M; i++) {
			int x = i/M;
			int y = i%M;
			if (lab[x][y] == 0) {
				lab[x][y] = 1;
				wall(i+1,count+1);
				lab[x][y] = 0;
			}
		}
		
	}
	
	
	//2. 바이러스 퍼뜨리기
	static void dfs(int x, int y) {
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
 
            if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                if(labcopy[nx][ny] == 0) {
                    labcopy[nx][ny] = 2;
                    dfs(nx, ny);
                }
            }
        }
    }
	
	//3. 안전구역 탐색
	static int safe() {
		int safe = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (labcopy[i][j] == 0) {
					safe++;
				}
			}
		}
		return safe;
    }
	//lab -> labcopy 복사
	static void copy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				labcopy[i][j] = lab[i][j];
			}
		}
	}
	
}
