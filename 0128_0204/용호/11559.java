import java.util.Arrays;
import java.util.Scanner;

public class acmicpc_11559 {
	static char [][] puyo = new char[12][6];
	static boolean [][] check;
	static int [] count = {0,0,0,0,0,0};//R,G,B,P,Y, .
	static int [] dx = {-1,1,0,0};
	static int [] dy = {0,0,-1,1};
	static int number =0;
	static int ans = 0;
	static int comp = 0;
	static boolean checker = true;
	public static void main(String[] args) {
		int comp=0;
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i <puyo.length; i++) {
			String temp = sc.next();
			for (int j = 0; j < puyo[i].length; j++) {
				puyo[i][j] = temp.charAt(j);
			}
		}
		// 1.전체 탐색해서 연쇄되는 것들 찾음
		// 2.전체 탐색 후에, 연쇄되는 것들을 .으로 바꿔줌
		// 3.중력 작용
		// 4. 1~3을 반복. -> 더이상 붙어있는 것들이 없을때까지  --> 지금까지 찾은 연쇄의 횟수 출력 
		
		
		
//		for (int i = 0; i < puyo.length; i++) {
//			System.out.println(Arrays.toString(puyo[i]));
//		}
		//전체 배열 탐색	
		while(checker) {
			comp=ans;
			check = new boolean[12][6];
			for (int i = 11; i >=0; i--) {
				for (int j = 0; j < puyo[i].length; j++) {
					switch (puyo[i][j]) {
						case 'R':
							dfs(i,j,'R');
							break;
						case 'G':
							System.out.println("GGGGGGGGGGGGGGGGGGGGG");
							dfs(i,j,'G');
							break;
						case 'Y':
							dfs(i,j,'Y');
							break;
						case 'B':
							dfs(i,j,'B');
							break;
						case 'P':
							dfs(i,j,'P');
							break;
					}//end switch
				}
			}//end for
			down();
			for (int y = 0; y < puyo.length; y++) {
				System.out.println(Arrays.toString(puyo[y]));	
			}
			if (ans == comp) break;
		}//endwhile
		System.out.println(ans);
	}//end of main
		
	public static void dfs(int x,int y,char c) {
		int idx = 5;
		if (check[x][y] == false) {
			switch (c) {
			case 'R':
				idx = 0;
				count[0]++;
				break;
			case 'G':
				idx = 1;
				count[1]++;
				break;
			case 'B':
				idx = 2;
				count[2]++;
				break;
			case 'P':
				idx = 3;
				count[3]++;
				break;
			case 'Y':
				idx = 4;
				count[4]++;
				break;
			}//END SWITCH
		}
		check[x][y] = true;
//		System.out.println("DFS check배열 출력 : ");
//		for (int l = 0; l < check.length; l++) {
//			System.out.println(Arrays.toString(check[l]));
//		}
//		System.out.println(Arrays.toString(count));
		for (int i = 0; i < 4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if (nx >= 0 && nx < puyo.length && ny >= 0 && ny < puyo[i].length) {
				 if (!check[nx][ny] && puyo[nx][ny] == c) {
					 dfs(nx, ny, c);
				 }
			}
		}
		if (count[idx] < 4) {
				count[idx] = 0;
		} else {
			change();
			check = new boolean[12][6];
			ans+=1;
			count[idx]=0;
		}
//		System.out.println(Arrays.toString(count));
	}//end of dfs
	
	public static void change() {
		for (int a = 0; a < check.length; a++) {
			for (int b = 0; b < check[a].length; b++) {
				if (check[a][b] == true) {
					puyo[a][b] = '.';
				}
			}
		}
	}//end of change
	static void down() {
        for (int i = 11; i >=0 ; i--) {
            for (int j = 0; j < 6; j++) {
                if(puyo[i][j] == '.') 
                    continue;
                
                int nx = i;
                char mark = puyo[i][j];
                puyo[i][j] = '.';
                while(true) {
                    if(!isRange(nx+1, j) || puyo[nx+1][j] != '.') 
                        break;
                    nx++;
                }
                puyo[nx][j] = mark;
            }
        }
    }
	static boolean isRange(int x,int y) {
        if( x < 0 || x >= 12 || y < 0 || y >= 6) return false;
        return true;
    }
}//end of class
