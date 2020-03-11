import java.util.Scanner;
import java.util.ArrayList;

class BaekJoon_15686_치킨배달 {
	
	static int N, M, L, len, answer;
	static int[][] arr;
	static ArrayList<House> house = new ArrayList<House>();
	static ArrayList<House> chicken = new ArrayList<House>();
	static ArrayList<House> select = new ArrayList<House>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][N];
		answer = 99999999;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == 1) house.add(new House(i, j)); 
				if(arr[i][j] == 2) chicken.add(new House(i, j));
				
			}
		}
		
		for(int i=0; i<chicken.size(); i++) select.add(new House(0, 0));
		
		for(int i=1; i<=M; i++) {
			L = i;
			combination(0, 0);			
		}
		
		System.out.println(answer);
	}
	
	public static void combination(int start, int depth) {
		if(depth == L) {
			
//			for(int i=0; i<L; i++) {
//				House hou = select.get(i);
//				System.out.println(hou.x + "   " + hou.y);
//			}
//			System.out.println();
			
			len = 0;
			calc();
			answer = Math.min(len, answer);
			return;
		}
		for(int i=start; i<chicken.size(); i++) {
			select.set(depth, chicken.get(i));
			combination(i+1, depth+1);
		}
	}
	
	public static void calc() {
		int min = 99999999;
		for(int i=0; i<house.size(); i++) {
			House h = house.get(i);
			min = 99999999;
			for(int j=0; j<L; j++) {
				House c = select.get(j);
				int length = Math.abs(h.x-c.x) + Math.abs(h.y-c.y);
				min = Math.min(min, length);
			}
			len += min;
		}
	}
}

class House {
	int x;
	int y;
	House(int x, int y){
		this.x=x;
		this.y=y;
	}
}
