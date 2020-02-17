import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
    static int N;
    static int calc[] ;
    static int num[] ;
    static int max_res;
    static int min_res;
    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        for(int t=0; t<T; t++) {
            max_res = Integer.MIN_VALUE;
            min_res  = Integer.MAX_VALUE;
            N = Integer.parseInt(bf.readLine());
            calc = new int[4];
            num= new int[N];
            String s = bf.readLine();
            StringTokenizer st = new StringTokenizer(s);
            for(int i=0; i<4 ;i++) {
                calc[i]=Integer.parseInt(st.nextToken());
            }
            s= bf.readLine();
            st = new StringTokenizer(s);
            for(int i=0; i<N; i++) {
                num[i]= Integer.parseInt(st.nextToken());
            }
             
            //dfs 로  연산자 줄 세우기.
            dfs(num[0],1);
            System.out.println("#"+(t+1)+" "+(max_res-min_res));
        }
    }
    public static void dfs(int res, int depth) {
        if(depth==N) {
            max_res = Math.max(max_res, res);
            min_res = Math.min(min_res, res);
            return;
        }
        for(int i=0; i<4; i++) {
            if(calc[i]>0) {
                if(i==0) {
                    calc[i]--;
                    dfs(res+num[depth], depth+1);
                    calc[i]++;
                }else if(i==1) {
                    calc[i]--;
                    dfs(res-num[depth], depth+1);
                    calc[i]++;
                }else if(i==2) {
                    calc[i]--;
                    dfs(res*num[depth], depth+1);
                    calc[i]++;
                }else {
                    calc[i]--;
                    dfs(res/num[depth], depth+1);
                    calc[i]++;
                }
                 
            }
             
        }
    }
 
}