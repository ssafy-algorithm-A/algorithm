package main.java.Temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bac17472_다리만들기2_G3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int R;
    static int C;
    final static int M = 9999999;
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
        answer = getMinimumDistance();

        System.out.println(answer);

    }

    //Kruskal
    private static int getMinimumDistance() {
        ArrayList<Edge> G = new ArrayList<>();
        int distance = 0;
        //makeset
        for (int i = 1; i < p.length; i++) p[i] = i;

        for (int i = 1; i < p.length; i++) {
            for (int j = i; j < p.length; j++) {
                if (val[i][j] == M) continue;
                G.add(new Edge(i, j, val[i][j]));
            }
        }
        Collections.sort(G);
        int cnt = 1;
        for (int i = 0; i < G.size(); i++) {
            int px = findSet(G.get(i).x);
            int py = findSet(G.get(i).y);
            if (px != py) {
                distance += G.get(i).val;
                union(px, py);
                cnt++;
                if(cnt == p.length -1) break;
            }
        }
        return cnt == p.length - 1 ? distance : -1;
    }

    private static void union(int px, int py) {
        p[py] = px;
    }

    private static int findSet(int x) {
        if(p[x] == x)return x;
        return p[x] = findSet(p[x]);
    }


    private static void init_map_and_distance() {
        //일단 무한으로
        for(int r[] : val){
            Arrays.fill(r, M);
        }

        for(int i =0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == 0) continue;
                int num = map[i][j];

                for(int d=0; d<4; d++){
                    int count = 0;
                    int new_x = i + dy[d];
                    int new_y = j + dx[d];
                    //범위안이고
                    while(true){
                        //1)범위 벗어남? 종료.
                        if(!(new_x >=0 && new_y >=0 && new_x<R && new_y <C))
                            break;
                            //2)바다니? 그럼 그 방향으로 계쏙 가봐
                        else if(map[new_x][new_y] == 0){
                            count++;
                            new_x += dy[d];
                            new_y += dx[d];
                        }
                        else { //3) 같은섬? 4) 다른 섬과 마주친것
                            if(count <2 || map[new_x][new_y] == num)//다리길이는 2이상입니다.
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
        Queue<Edge> Q = new LinkedList<>();
        Q.add(new Edge(x,y));

        while(!Q.isEmpty()){
            Edge tmp = Q.poll();
            map[tmp.x][tmp.y] = idx;
            visited[tmp.x][tmp.y] = true;

            for(int i =0; i<4; i++){
                int new_x = tmp.x + dy[i];
                int new_y = tmp.y + dx[i];
                if(new_x >=0 && new_y >=0 && new_x<R && new_y <C){
                    if(!visited[new_x][new_y] && map[new_x][new_y] == 1) {
                        Q.add(new Edge(new_x, new_y));
                    }
                }

            }
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

        public Edge(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Edge o) {
            return this.val - o.val;
        }
    }
}