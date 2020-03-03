package excercisesaffy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class state{
	int depth;
	int rx,ry,bx,by;
	public state(int ry, int rx, int by, int bx, int depth) {
		this.ry=ry;
		this.rx=rx;
		this.by=by;
		this.bx=bx;
		this.depth=depth;
	}
}

public class ±∏ΩΩ≈ª√‚2 {
	static int N;
	static int M;
	static int map[][];
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	
	static int res = -1;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		map= new int[N][M];

		int rx=0, ry=0, bx=0, by=0;
		for (int i = 0; i < map.length; i++) {
			String s = bf.readLine();
			for (int j = 0; j < map[0].length; j++) {
				//# -> 1, .-> 0, hole->2
				if(s.charAt(j)=='#') {
					map[i][j]=1;
				}
				else if(s.charAt(j)=='.') {
					map[i][j]=0;
				}
				else if(s.charAt(j)=='R') {
					map[i][j]=0;
					ry=i;
					rx=j;
				}
				else if(s.charAt(j)=='B'){
					map[i][j]=0;
					by=i;
					bx=j;
				}
				else 
					map[i][j]=2;
			}
		}
		
		res = bfs(ry, rx, by, bx, 0);
		
		System.out.println(res);
	}	
	public static int bfs(int ry, int rx, int by, int bx, int depth) {
		Queue<state> q = new LinkedList<state>();
		q.add(new state(ry, rx, by, bx, 0));
		int count=0;
		while(!q.isEmpty()){
			state temp = q.poll();
			if(temp.depth>9) return -1;
			for(int i=0; i<4; i++) {
				int flag=0;
				ry= temp.ry;
				rx = temp.rx;
				by=temp.by;
				bx=temp.bx;
				int checkred =0;
				int checkblue = 0;
				// red ∫Œ≈Õ øÚ¡˜ø© ∫Ω
				while(true) {
					ry=ry+dy[i];
					rx=rx+dx[i];
					if(map[ry][rx]==2) {
						flag++;
						break;
					}
					if(map[ry][rx]==1) {
						ry=ry-dy[i];
						rx=rx-dx[i];
						break;
					}
					checkred++;
				}
				//blue øÚ¡˜ø©∫Ω;
				while(true) {
					by=by+dy[i];
					bx=bx+dx[i];
					if(map[by][bx]==2) {
						flag--;
						break;
					}
					if(map[by][bx]==1) {
						by=by-dy[i];
						bx=bx-dx[i];
						break;
					}
					checkblue++;
				}
				if (flag==1) return temp.depth+1;		
				if (flag==-1) continue;
				if(ry==by && bx==rx) {//∞„ƒß
					if(map[ry][rx]==2) {
						continue;
					}
					if(checkred<checkblue) {
						by=by-dy[i];
						bx=bx-dx[i];
					}else {
						ry=ry-dy[i];
						rx=rx-dx[i];
					}
				}
				q.add(new state(ry, rx, by, bx, temp.depth+1));
			}
			
		}
		return -1;
	}
}
