import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int [] nums = new int [n];
		for (int t = 0; t < n; t++) {
			st = new StringTokenizer(br.readLine(),"");
			nums[t] = Integer.parseInt(st.nextToken());
		}//end tc
		int [] d = new int [k+1];
		d[0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <=k; j++) {
				if (j-nums[i] >= 0) {
					d[j]+=d[j-nums[i]];
				}
			}
		}
		System.out.println(d[k]);
	}//end main
}//end class
