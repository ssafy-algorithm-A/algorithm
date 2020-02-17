package Baekjoon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
public class N_17143 {
	public static int[][] dot = {{-1,0},{1,0},{0,1},{0,-1}};
	public static Queue<Shark> queue;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		int M = sc.nextInt();
		queue = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			int x= sc.nextInt();
			int y = sc.nextInt();
			int speed = sc.nextInt();
			int dir = sc.nextInt(); // 1»ó 2ÇÏ 3¿À 4¿Þ
			if(dir == 1 || dir == 2) {
				speed %= (2*R-2);
			}
			else {
				speed %= (2*C-2);
			}
			int size = sc.nextInt();
			queue.add(new Shark(x,y,speed,dir,size));
		}
		int result = 0;
		int index = 0;
		while(index <= C) {
			index++; // ³¬½Ã¿Õ ÇÑÄ­ ÀÌµ¿
			Shark remove_shark = null;
			for (Shark s : queue) { // Á×ÀÏ »ó¾î °í¸£±â
				if(s.y == index) {
					if(remove_shark == null) {
						remove_shark = s;
					}else {
						if(remove_shark.x > s.x) {
							remove_shark = s;
						}
					}
				}
			}
			if(remove_shark != null) { // »ó¾î Á×ÀÌ±â
				result+= remove_shark.size;
				queue.remove(remove_shark);
			}
			PriorityQueue<Shark> pq = new PriorityQueue<>();
			while(!queue.isEmpty()) { // »ó¾î ÀÌµ¿
				Shark s = queue.poll();
				int dx = s.x + (dot[s.dir-1][0] * s.speed);
				int dy = s.y + (dot[s.dir-1][1] * s.speed);
				int go = s.speed;
				if(dx < 1 || dx > R) {
					dx = s.x;
					while(true) {		
						int temp = go;
						if(s.dir == 1) { // »ó
							go -= (dx - 1);
							if(go < 0) {
								dx -= temp;
								break;
							}
							dx = 1;
							s.dir +=1;
						}else if(s.dir == 2) { // ÇÏ
							go -= (R-dx);
							if(go < 0) {
								dx += temp;
								break;
							}
							dx = R;
							s.dir -=1;
						}
					}
				}else if(dy < 1 || dy > C) {
					dy = s.y;
					while(true) {
						int temp = go;
						if(s.dir == 3) { // ¿ì
							go -= (C - dy);
							if(go < 0) {
								dy += temp;
								break;
							}
							dy = C;
							s.dir +=1;
						}else if(s.dir == 4) { // ÁÂ
							go -= (dy-1);
							if(go < 0) {
								dy -= temp;
								break;
							}
							dy = 1;
							s.dir -=1;
						}
					}
				}
				s.x = dx;
				s.y = dy;
				pq.add(s);
			}
			HashMap<String,Shark> hm = new HashMap<>(); // °°Àº °÷ °í±â Á×ÀÌ±â
			while(!pq.isEmpty()) {
				Shark s= pq.poll();
				String str = s.x+","+s.y;
				if(!hm.containsKey(str)) {
					hm.put(str, s);
				}
			}
			queue = new LinkedList<>(hm.values());
		}
		System.out.println(result);
	}
	public static class Shark implements Comparable<Shark>{
		int x,y,speed,dir,size;
		public Shark(int x,int y,int speed, int dir, int size) {
			this.x= x;
			this.y =y;
			this.speed = speed;
			this.dir= dir;
			this.size = size;
		}
		@Override
		public int compareTo(Shark o) {
			// TODO Auto-generated method stub
			return o.size - this.size;
		}
	}
}