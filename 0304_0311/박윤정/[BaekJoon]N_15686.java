package BaekJoon;
// 156 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N_15686 {
	public static List<DOT> chicken;
	public static List<DOT> home;
	public static int[] dist;
	public static int answer;
	public static int M;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		for (int i = 0; i < N; i++) { // 0ºóÄ­ 1Áý 2Ä¡Å²Áý
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n == 2) chicken.add(new DOT(i,j));
				else if(n == 1) home.add(new DOT(i,j));
			}
		}
		dist = new int[home.size()];
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		answer = Integer.MAX_VALUE;
		go(0,0);
		System.out.println(answer);
	}
	
	public static void go(int index,int count) {
		if(count >= M) {
			int result = 0;
			for (int i = 0; i < dist.length; i++) {
				result += dist[i];
			}
			answer = Math.min(answer, result);
			return;
		}
		
		for (int i = index; i < chicken.size(); i++) {
			DOT dc = chicken.get(i);
			int[] temp = new int[dist.length];
			System.arraycopy(dist, 0, temp, 0, dist.length);
			for (int j = 0; j < home.size(); j++) {
				DOT dh = home.get(j);
				int dt = Math.abs(dh.x-dc.x) + Math.abs(dh.y-dc.y);
				dist[j] = Math.min(dist[j], dt);
			}
			go(i+1,count+1);
			System.arraycopy(temp, 0, dist, 0, dist.length);
		}
	}
	
	public static class DOT{
		int x,y;
		
		public DOT(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
