package acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class acmicpc_17144 {
	static int [][] map;
	static int [][] tmpmap;
	static int R,C,T;
	static int [] dx = {-1,1,0,0};
	static int [] dy = {0,0,-1,1};
	static int upX;
	static int downX;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int [R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int idx = 0;
			while (st.hasMoreTokens()) {
				map[i][idx] = Integer.parseInt(st.nextToken());
				idx++;
			}	
		}//입력 끝
//		for (int j = 0; j < map.length; j++) {
//			System.out.println(Arrays.toString(map[j]));			
//		}//입력 확인
//		System.out.println();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == -1) downX = i;
			}
		}
		upX = downX - 1;
		
		//T초동안 진행
		while (T > 0) {
			tmpmap = new int [R][C];
			//1. 미세먼지 확산, 상하좌우 4방향, map[i][j]/5만큼 확산, map[i][j] - (map[i][j]/5)*확산된 방향의 갯수
			//미세먼지 확산 시작
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] !=0) {//미세먼지일 때
						//미세먼지를 퍼뜨림
						expand(i,j);
					}
				}//end inner for;
			}//end outer for;
			//미세먼지 확산 끝
			System.arraycopy(tmpmap, 0, map, 0, R);
			cleaner();
			T--;
		}//end while
//		for (int j = 0; j < map.length; j++) {
//			System.out.println(Arrays.toString(map[j]));			
//		}//입력 확인	
		System.out.println(checkSum());
	}//end main

	public static int checkSum() {
		int sum = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] > 0) sum+=map[i][j];
			}
		}
		return sum;
	}

	public static void expand(int i, int j) {//미세먼지 확산
		int count = 0;
		if (map[i][j] == -1) {
			tmpmap[i][j] = -1;
			return;
		}
		for (int k = 0; k < dx.length; k++) {
			int nx = i+dx[k];
			int ny = j+dy[k];
//			System.out.println("nx : "+nx+"ny : "+ny+"R : "+R+"C : "+C);
			if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
			if (map[nx][ny] == -1) continue;
			tmpmap[nx][ny] += map[i][j]/5;
			count+=1;
		}
		tmpmap[i][j] += map[i][j] - (map[i][j]/5)*count; 
	}
	
	  public static void cleaner() {
	        for (int i = upX ; i >0 ; i--) {
	            map[i][0] = map[i-1][0];
	        }
	        map[upX][0] = 0;
	        for (int i = 0; i < C - 1; i++) {
	            map[0][i] = map[0][i + 1];
	        }
	        for (int i = 0; i < upX; i++) {
	            map[i][C-1] = map[i + 1][C-1];
	        }
	        for (int i = C - 1; i >= 1; i--) {
	            map[upX][i] = map[upX][i - 1];
	        }
	        map[upX][0] = -1;
	        //아래 시계방향
	        for (int i = downX; i < R-1 ; i++) {
	            map[i][0] = map[i+1][0];
	        }
	        map[downX][0] =0;
	        for (int i = 0; i < C - 1; i++) {
	            map[R-1][i] = map[R-1][i + 1];
	        }
	        for (int i = R-1; i > downX; i--) {
	            map[i][C-1] = map[i-1][C-1];
	        }
	        for (int i = C - 1; i >= 1; i--) {
	            map[downX][i] = map[downX][i - 1];
	        }
	        map[downX][0] = -1;
	    }
}//end class

