package excercisesaffy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class place{
	int y;
	int x;
	public place(int y, int x) {
		this.y=y;
		this.x=x;
	}
}
class vertex implements Comparable<vertex>{
	int one;
	int two;
	int val;
	public vertex(int one, int two, int val) {
		this.one = one;
		this.two = two;
		this.val = val;
	}
	@Override
	public int compareTo(vertex v) {
		// TODO Auto-generated method stub
		return this.val-v.val;
	}

}
public class 다리만들기2 {
	static int parent[];
	static int N;
	static int M;
	static int map[][];
	static ArrayList<ArrayList<place>> island= new ArrayList<ArrayList<place>>();
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1, 0,0};
	static ArrayList<vertex> varr = new ArrayList<vertex>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(bf.readLine());
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			
			st=new StringTokenizer(bf.readLine());
			for (int j = 0; j < map[0].length; j++) {
				int val= Integer.parseInt(st.nextToken());
				if(val==1) {
					map[i][j]=-1;
				}else {
					map[i][j]=val;
				}
			}
		}
		//섬 번호 매기기.
		island.add(null);

		int count=1;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j]==-1) {
					bfs(i, j, count);
					count++;
				}
			}
		}

		//섬간의 최소거리 구하기 모든 섬간의 가능한 vertex를 구한다. vC2
		//두 섬간 연결이 안되면 val 는 -1 로 준다. 
		// sort 하고 -1 은 제외한다.
		for (int i = 1; i < island.size(); i++) {
			for (int j = i+1; j < island.size(); j++) {
				calMinDis(i,j);
			}
		}
		parent = new int[island.size()];
		//kruskal algorithm
		Collections.sort(varr);
		
		for(int i=1; i< island.size(); i++) {

			makeSet(i);
		}
	
		int res =0;
	
		for (int i = 0; i < varr.size(); i++) {
		
			if(varr.get(i).val<0) continue;
			if(findSet(varr.get(i).one)!=findSet(varr.get(i).two)) {
				res+=varr.get(i).val;
				union(varr.get(i).one, varr.get(i).two);
			
				
			}
		}
		
		boolean check_connect=true;
		int pivot = findSet(1); 
		for(int i=2; i<island.size(); i++) {
			if(findSet(i)!=pivot) {
				check_connect=false;
			}
		}
		if (!check_connect) {
			System.out.println(-1);
		}else {
			System.out.println(res);
		}
	
	}
	
	public static void makeSet(int i) {
		parent[i]=i;
	}
	public static int findSet(int i) {
		if(parent[i]==i) {
			return i;
		}else {
			parent[i]=findSet(parent[i]);
			return parent[i];
		}
	}
	public static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		parent[py]=px;
	}
	public static void calMinDis(int first, int second) {
		//섬간의 최소거리 구하기 모든 섬간의 가능한 vertex를 구한다. vC2
				//두 섬간 연결이 안되면 val 는 -1 로 준다. 
				// sort 하고 -1 은 제외한다.
		int min_res = 100000;
	
		for(int i=0; i< island.get(first).size(); i++) {
			int cy=island.get(first).get(i).y;
			int cx = island.get(first).get(i).x;
			
			for(int j=0; j<4; j++) {
				int ny = cy;
				int nx = cx;
				int dis =0;
				while(true) {
					ny=ny+dy[j];
					nx=nx+dx[j];
					if(ny<0 || nx<0 ||ny>=N ||nx>=M) break;
					if(map[ny][nx]==map[cy][cx]) break;
					if(map[ny][nx]!=0 && map[ny][nx]!=second) break;
					if(map[ny][nx]==second) {
						if(dis>=2) {
							min_res = Math.min(dis, min_res);
						}
						break;
					}
					dis++;
				}
			}
		}
		if(min_res ==100000) min_res = -1;
		varr.add(new vertex(first, second, min_res));
		
		
	}
	public static void bfs(int y, int x, int idx) {
		int visited[][] = new int[N][M];
		Queue<place> q = new LinkedList<place>();
		ArrayList<place> temp = new ArrayList<place>();
		
		q.add(new place(y,x));
		temp.add(new place(y,x));
		map[y][x]=idx;
		visited[y][x]=1;
		
		while(!q.isEmpty()) {
			place p = q.poll();
			int cy=p.y;
			int cx=p.x;
			for(int i=0; i<4; i++) {
				int ny = cy+dy[i];
				int nx = cx+dx[i];
				if(ny<0 || nx<0 ||ny>=N || nx>=M) continue;
				if(visited[ny][nx]==1) continue;
				if(map[ny][nx]==0) continue;
				
				q.add(new place(ny,nx));
				temp.add(new place(ny,nx));
				map[ny][nx]=idx;
				visited[ny][nx]=1;
			}
		}
		island.add(temp);
		
	}
}
