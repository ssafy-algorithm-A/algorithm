package codingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int x;
	int y;
	public Node(int y, int x) {
		this.y=y;
		this.x=x;
	}
}

public class 치킨배달_조합시뮬 {
	static int dx[] = {0,0,-1,1};
	static int dy[]= {-1,1,0,0};
	static int N;
	static int M;
	static int map[][];
	static ArrayList<Node> chicken = new ArrayList<Node>();
	static ArrayList<Node> home = new ArrayList<Node>();

	static int arr[];
	static int min_res=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map= new int[N][N];
		
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < map.length; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					chicken.add(new Node(i,j));
				}
				else if(map[i][j]==1) {
					home.add(new Node(i,j));
				}
			}
		}
		arr = new int[chicken.size()];


		comb(0, 0, M);
		
		System.out.println(min_res);
		
	}
	

	
	public static int calculateSum() {
		int res=0;
		//arr 이 1 인 chicken집은 살아있음
		for (int i = 0; i < home.size(); i++) {
			int temp = Integer.MAX_VALUE;
			for (int j = 0; j < chicken.size(); j++) {
				if(arr[j]==1) {
					temp = Math.min(temp, Math.abs(chicken.get(j).y-home.get(i).y)+Math.abs(chicken.get(j).x-home.get(i).x));
				}
			}
			res+=temp;
		}
		return res;
	}
	
	public static void comb(int idx, int cnt, int mcnt) {
		if(cnt==mcnt) {
			min_res = Math.min(min_res, calculateSum()); 
			return;
		}//종료
		
		for (int i = idx; i < arr.length; i++) {
			arr[i]=1;
			comb(i+1,cnt+1, mcnt);
			arr[i]=0;
		}
		//실행
		//호출
		
	}

}
