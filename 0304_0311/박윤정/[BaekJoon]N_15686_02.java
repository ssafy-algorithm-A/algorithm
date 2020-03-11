package BaekJoon;
// 184ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N_15686_02 {
	public static List<DOT> chicken;
	public static List<DOT> home;
	public static int[] select;
	public static int answer;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		select = new int[M];
		for (int i = 0; i < N; i++) { // 0ºóÄ­ 1Áý 2Ä¡Å²Áý
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n == 2) chicken.add(new DOT(i,j));
				else if(n == 1) home.add(new DOT(i,j));
			}
		}
		answer = Integer.MAX_VALUE;
		go(0,0);
		System.out.println(answer);
	}
	
	public static void go(int index,int count) {
		if(count >= select.length) {
			int result = 0;
			for (int i = 0; i < home.size(); i++) {
				DOT dh = home.get(i);
				int min = Integer.MAX_VALUE;
				for (int j = 0; j < select.length; j++) {
					DOT dc = chicken.get(select[j]);
					int dt = Math.abs(dh.x-dc.x) + Math.abs(dh.y-dc.y);
					min = Math.min(min, dt);
				}				
				result += min;
			}
			answer = Math.min(answer, result);
			return;
		}
		
		for (int i = index; i < chicken.size(); i++) {
			select[count] = i;
			go(i+1,count+1);
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

