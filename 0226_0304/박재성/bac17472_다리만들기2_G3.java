package main.java.Temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bac17472_다리만들기2_G3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int R;
    static int C;
    final static int M = 99;
    static int answer = 0;

    static int map[][];
    static boolean visited[][];
    static int[][] val; // 최소 거리를 구해서 MST해볼것임.
    static int p[];

    //상 하 좌 우
    static int dx[] = {0,0,-1,1};
    static int dy[] = {-1,1,0,0};

    public static void main(String args[]) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C];

        for(int i =0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        check_island();
        init_map_and_distance();
        kruskal();
       // mpprint();
       // System.out.println();
       // pprint();

        if(answer == 0)
            answer = -1;
        System.out.println(answer);

    }

    private static void kruskal() {
        //구한 최소거리를 다시 정렬할 수 있게 가져와서 초기화
        int idx = 0;
        int length=0;

        //연결된 엣지 갯수 구하기, p.length는 섬의 갯수 +1
        for(int i =1; i<p.length; i++){
            for(int j=i+1; j<p.length; j++){
                if(val[i][j] != M)
                   length++;
            }
        }

        //
        Edge G[] = new Edge[length];
        for(int i =1; i<p.length; i++){
            for(int j=i+1; j<p.length; j++){
                if(val[i][j] != M) {
                    G[idx++] = new Edge(i, j, val[i][j]);
                }
            }
        }
        Arrays.sort(G);
      /* for(Edge e : G)
          System.out.println(e);
        System.out.println();*/

        //부모를 자신으로  makeset
        //섬 1,2,3,4 (p.lenght은 5)
        for(int i =1; i<p.length; i++)
            p[i] = i;

        int cnt = 0;
        int MST = 0;

        //섬 숫자(N)보다 변수가 N-1 보다 적을 때.
        if(length < ((p.length-1)-1)){
            answer = -1;
            return;
        }

        for(int i =0; i<length; i++){
            Edge tmp = G[i];
            int px = findSet(tmp.x);
            int py = findSet(tmp.y);
            if(px != py){
                union(px, py);
               //System.out.println(tmp);
                cnt++;
                MST += tmp.val;
                if(cnt == length)
                    break;
            }

        }

       answer = MST;


    }

    private static void union(int px, int py) {
        p[py] = px;
    }

    private static int findSet(int x) {
        if(p[x] == x){
            return x;
        }
        else
            return p[x] = findSet(p[x]);
    }


    private static void init_map_and_distance() {
        //일단 무한으로
        for(int r[] : val){
            Arrays.fill(r, M);
        }

        for(int i =0; i<R; i++){
            for(int j=0; j<C; j++){
                int num = map[i][j];
               // System.out.println(num);

                for(int d=0; d<4; d++){
                    int count = 0;
                    int new_x = i + dy[d];
                    int new_y = j + dx[d];
                    //범위안이고
                        while(true){
                            //1)범위 벗어남? 종료.
                            if(!(new_x >=1 && new_y >=1 && new_x<R && new_y <C))
                                break;
                            //2)바다니? 그럼 그 방향으로 계쏙 가봐
                            if(map[new_x][new_y] == 0){
                                count++;
                                new_x += dy[d];
                                new_y += dx[d];
                            }//3) 같은 섬이면 종료.
                            else if(map[new_x][new_y] == num)
                                break;
                            else { // 다른 섬과 마주친것
                                if(count <2)//다리길이는 2이상입니다.
                                    break;
                                int min = val[num][map[new_x][new_y]];
                                min = Math.min(min, count);
                                val[num][map[new_x][new_y]] = min;
                                break;
                            }
                        }
                }// for d
            }// for j
        }// for i

    }

    private static void check_island() {
        int idx = 1;
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == 1 && !visited[i][j])
                    BFS(i,j,idx++);
            }
        }

        //섬의 갯수로 초기화
        val = new int[idx][idx];
        p = new int[idx];
    }


    //섬마다 번호를 줄것임.
    private static void BFS(int x, int y, int idx) {
        Queue<Point> Q = new LinkedList<>();
        Q.add(new Point(x,y));

        while(!Q.isEmpty()){
            Point tmp = Q.poll();
            map[tmp.x][tmp.y] = idx;
            visited[tmp.x][tmp.y] = true;

            for(int i =0; i<4; i++){
                int new_x = tmp.x + dy[i];
                int new_y = tmp.y + dx[i];
                if(new_x >=0 && new_y >=0 && new_x<R && new_y <C){
                    if(!visited[new_x][new_y] && map[new_x][new_y] == 1) {
                        Q.add(new Point(new_x, new_y));
                    }
                }

            }
        }

    }

    public static void mpprint(){
        for(int i =0; i<val.length; i++){
            for(int j=0; j<val.length; j++){
                System.out.printf("%2d ", val[i][j]);
            }
            System.out.println();
        }
    }

    public static void pprint(){

        for(int i =0; i<R; i++){
            for(int j=0; j<C; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Edge implements Comparable<Edge>{
        int x;
        int y;
        int val;

        public Edge(int x, int y, int val) {
            super();
            this.x = x;
            this.y = y;
            this.val = val;
        }

        // 디버깅이 편함
        @Override
        public String toString() {
            return "(" + x + "," + y + "=" + val;
        }

        @Override
        public int compareTo(Edge o) {
            return this.val - o.val;
        }


    }
}