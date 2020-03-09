import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class pointer{
	int x;
	int y;
	public pointer(int x,int y) {
		this.x = x;
		this.y = y;
	}
}

public class acmicpc_17822 {
	static int [][] map;
	static int [] dx = {-1,1,0,0};
	static int [] dy = {0,0,-1,1};
	static int N,M,T;
	static int x,d,k;
	static double avg;
	static int mapsum;
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int [N+1][M+1];
		avg = 0;//평균값 저장할 변수
		mapsum=0;//map전체의 합
		count = N*M;//map에 숫자 갯수 (0제외)
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 1; j <= M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				mapsum += tmp;
			}
		}
		avg= (double)mapsum/count;
		for (int t = 0; t < T ; t++) {
			st = new StringTokenizer(br.readLine()," ");
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			move(x,d,k);
			find();
		}
		System.out.println(getSum());
	}
	private static void find() {//여기 다시 확인
		boolean flag = true;
		//인접한 같은 수가 있는 경우,
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] > 0) {
					Stack<pointer> s = new Stack<pointer>();
					s.add(new pointer(i,j));
					int cur = map[i][j];
					while (!s.isEmpty()) {
						boolean inflag = true;
						pointer p = s.pop();
						int x = p.x;
						int y = p.y;
						for (int k = 0; k < dx.length; k++) {
							int nx = x+dx[k];
							int ny = y+dy[k];
							if (nx == 0 || nx > N) continue;
							if (ny == 0) ny = M;
							if (ny > M) ny = 1;
							if (map[nx][ny] == cur) {
								map[nx][ny] = 0;
								s.add(new pointer(nx,ny));
								inflag = false; //인접하면서, 수가 같은것을 찾은 경우
								flag = false;
							}
						}//end dx,dy
						if (!inflag) map[x][y] = 0;
					}//end while
				}
			}//end inner for
		}//end outer for
		if (flag) {
			int s = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (map[i][j] == 0) count--;
					else s+=map[i][j]; 
				}
			}
			mapsum = s;
			avg = (double)mapsum/count;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (map[i][j] > 0) {
						if (map[i][j] > avg) map[i][j]-=1;
						else if (map[i][j] < avg) map[i][j]+=1;
					}
				}
			}
		}
	}//end find
	private static void DFS() {
				
	}
	private static int getSum() {
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sum+=map[i][j];
			}
		}
		return sum;
	}
	private static void move(int x, int d, int k) {
		for (int i = 1; i <= N; i++) {
			if (i%x == 0) {//x의 배수인 원판만
				int times = k%M;
				int dtmp;
				if (times > M/2) {//시계방향, 반시계방향중 적은횟수 찾기
					times = M-times;
					if (d == 1) dtmp = 0;
					else dtmp = 1;
					while (times > 0) {
						turn(i,dtmp);
						times--;
					}
				} else {
					while (times > 0) {
						turn(i,d);
						times--;
					}
				}
			}
		}//원판 찾기
	}//end move
	private static void turn(int i, int d) {
		if (d==0) {//시계방향, k만큼
			int tmp = map[i][M];
			for (int j = M; j >=2; j--) {
				map[i][j]=map[i][j-1];
			}
			map[i][1] = tmp;
		} else {//반시계방향,k만큼
			int tmp = map[i][1];
			for (int j = 1; j <=M-1; j++) {
				map[i][j]=map[i][j+1];
			}
			map[i][M] = tmp;
		}
	}
}//end class
