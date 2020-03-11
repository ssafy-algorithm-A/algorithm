package 백준;

/**
 * 시간초과
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class problem15686치킨배달 {
	static int size;
	static int[][] arr;
	static ArrayList<Chicken> ar;
	static int min = Integer.MAX_VALUE;
	static int dir[][] = {{-1,0},{0,-1},{1,0},{0,1}};

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		size = sc.nextInt();
		int num = sc.nextInt();

		arr = new int[size][size];
		ar = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				int k = sc.nextInt();
				if (k == 2) {
					ar.add(new Chicken(i, j));
				}
				else {
					arr[i][j] = k;
				}
			}
		}
		
        int[] comArr = new int[num];
        combination(comArr, ar.size(), num, 0, 0);//치킨집중에 num개 뽑아내기
		
        System.out.println(min);
        
        sc.close();
	}

	private static void combination(int[] comArr, int n, int r, int index, int target) {
		if (r == 0) {
			for (int i : comArr) {
				arr[ar.get(i).x][ar.get(i).y] = 2;
			}
			
			min = Math.min(min, searchChicken());
			for (int i : comArr) {
				arr[ar.get(i).x][ar.get(i).y] = 0;
			}
			
			return;
		}
		if (target == n)
			return;

		comArr[index] = target;
		combination(comArr, n, r - 1, index + 1, target + 1);// 뽑는경우
		combination(comArr, n, r, index, target + 1);// 안뽑는경우

	}

	private static int searchChicken() {//bfs로 가장 가까운 치킨집 찾기
		
		int sum = 0;
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if(arr[i][j] == 1) {
					
					int depth = 0;
					
					Queue<Chicken> q = new LinkedList<Chicken>();
					q.add(new Chicken(i, j));
					
					outloop : while(!q.isEmpty()) {
						
						int s = q.size();
						depth++;
						
						
						for (int p = 0; p < s; p++) {
							
							Chicken c = q.poll();
							
							for (int k = 0; k < dir.length; k++) {
								int dx = c.x + dir[k][0];
								int dy = c.y + dir[k][1];
								
								if(dx >= 0 && dy >= 0 && dx < size && dy < size) {
									
									if(arr[dx][dy] == 2) {
										break outloop;
									}
									q.add(new Chicken(dx, dy));
								}
							}
							
						}
					}
					sum += depth;
				}
			}
		}
		return sum;
	}
}

class Chicken {
	int x;
	int y;

	public Chicken(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Chicken [x=" + x + ", y=" + y + "]";
	}

}
