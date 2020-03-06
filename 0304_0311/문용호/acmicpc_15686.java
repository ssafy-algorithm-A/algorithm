import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//치킨 배달

class pointer{
	int x;
	int y;
	public pointer(int x,int y) {
		this.x = x;
		this.y = y;
	}
}

public class acmicpc_15686 {
	static int [][] map;
	static pointer [] trr;
	static ArrayList<pointer> chicken = new ArrayList<pointer>();
	static ArrayList<pointer> houses = new ArrayList<pointer>();
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int [N][N];
		trr = new pointer[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 1) {
					houses.add(new pointer(i, j));
				}
				else if (tmp == 2) {
					chicken.add(new pointer(i, j));
				}
				map[i][j] = tmp;
			}
		}
		comb(chicken.size(),M);
		System.out.println(ans);
	}//end main
	private static void comb(int n, int r) {
		if (r==0) {//종료
			checkDistance(trr);
		} else if(n < r) {
			return; // 조합 규칙에 벗어남
		} else {//재귀
			trr[r-1] = chicken.get(n-1);
			comb(n-1,r-1);
			comb(n-1,r);
		}
	}
	private static void checkDistance(pointer[] trr) {
		int d = 0;
		for (pointer house : houses) {//각각의 집이
			int dist = Integer.MAX_VALUE;
			int h_x = house.x;
			int h_y = house.y;
			for (int i = 0; i < trr.length; i++) {//각각의 치킨집에 대해
				int c_x = trr[i].x;
				int c_y = trr[i].y;
//				System.out.println("집 : "+h_x+" "+h_y+"\t 치킨 : "+c_x+" "+c_y);
				int p_dist = Math.abs(h_x-c_x)+Math.abs(h_y-c_y);
				if (p_dist == 1) {
					dist = 1;
					break;
				}
				if (dist > p_dist) dist = p_dist;
			}
			d+=dist;
		}
		if (ans > d) ans = d;
	}
}//end class
