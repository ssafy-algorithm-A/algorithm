package main.java.Temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bac13460_구슬탈출2_G3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static String map[][];
    static int answer = Integer.MAX_VALUE;

    static int Hole_r=0;
    static int Hole_b=0;

    //상 하 좌 우
    static int dx[] = {0,0,-1,1};
    static int dy[] = {-1,1,0,0};

    public static void main(String args[]) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new String[N][M];

        for(int i =0; i<N; i++) map[i] = br.readLine().split("");

        solution();
        if(answer == Integer.MAX_VALUE)
            answer = -1;
        System.out.println(answer);
    }

    private static void solution() {
        Point R_w = null;
        Point B_w = null;

        //빨강 파랑 구슬 위치
        for(int i =0; i<N; i++){
            for(int j =0; j<M; j++){
                if(map[i][j].equals("R"))
                    R_w = new Point(i,j);
                else if(map[i][j].equals("B"))
                    B_w = new Point(i,j);
            }
        }

        for(int i =0; i<4; i++) {
            dfs(R_w.x,R_w.y,B_w.x, B_w.y, i, 0);
        }
    }

    public static void dfs(int r_x, int r_y, int b_x, int b_y, int dir, int count){

        //파랑이 빠져버린다면 실패.
        //횟수가 넘어가면 리턴.
        if(count > 10){
            return;
        }
        if(Hole_b == 1){
            return;
        }
        //올바른 결과..
        if(Hole_r ==1 && Hole_b == 0){
            //System.out.println(count + " ---- ");
            answer = Math.min(answer, count);
            return;
        }

        Point next_r = null;
        Point next_b = null;

        //상 하 좌 우(0,1,2,3)
                //빨강 선, 파랑 후
                if(dir == 0 || dir == 1) {
                    if (((r_x < b_x) && dir == 0)
                            || ((r_x > b_x) && dir == 1)) {
                        map[r_x][r_y] = ".";
                        next_r = moveR(new Point(r_x,r_y), dir);
                        if (Hole_r == 0) map[next_r.x][next_r.y] = "R";

                        map[b_x][b_y] = ".";
                        next_b = moveB(new Point(b_x,b_y), dir);
                        if (Hole_b == 0) map[next_b.x][next_b.y] = "B";
                    }
                    //파랑 선, 빨강 후
                    else {
                        map[b_x][b_y] = ".";
                        next_b = moveB(new Point(b_x,b_y), dir);
                        if (Hole_b == 0) map[next_b.x][next_b.y] = "B";

                        map[r_x][r_y] = ".";
                        next_r = moveR(new Point(r_x,r_y), dir);
                        if (Hole_r == 0) map[next_r.x][next_r.y] = "R";
                    }
                }

                if(dir == 2 || dir == 3) {
                    //빨강 선, 파랑 후
                    if (((r_y < b_y) && dir == 2)
                            || ((r_y > b_y) && dir == 3)) {
                        map[r_x][r_y] = ".";
                        next_r = moveR(new Point(r_x,r_y), dir);
                        if (Hole_r == 0) map[next_r.x][next_r.y] = "R";

                        map[b_x][b_y] = ".";
                        next_b = moveB(new Point(b_x,b_y), dir);
                        if (Hole_b == 0) map[next_b.x][next_b.y] = "B";
                        //pprint();
                    }
                    //파랑 선, 빨강 후
                    else {
                        map[b_x][b_y] = ".";
                        next_b = moveB(new Point(b_x,b_y), dir);
                        if (Hole_b == 0) map[next_b.x][next_b.y] = "B";

                        map[r_x][r_y] = ".";
                        next_r = moveR(new Point(r_x,r_y), dir);
                        if (Hole_r == 0) map[next_r.x][next_r.y] = "R";
                    }
                }

        //재귀
        for(int i =0; i<4; i++) {
            dfs(next_r.x, next_r.y, next_b.x, next_b.y, i, count + 1);
        }

        //종료시 리셋
        map[next_r.x][next_r.y] = ".";
        map[next_b.x][next_b.y] = ".";
        map[r_x][r_y] = "R";
        map[b_x][b_y] = "B";
        Hole_r =0;
        Hole_b =0;
    }

    private static Point moveR(Point r, int dir) {
        Point result_r = new Point(r.x, r.y);
        int new_x =r.x+dy[dir];
        int new_y =r.y+dx[dir];
        while(true){
            if(map[new_x][new_y].equals(".")) {
                new_x += dy[dir];
                new_y += dx[dir];
            }
            else if(map[new_x][new_y].equals("O")){
               Hole_r = 1;
                break;
            }
            else {
                break;
            }
        }
        new_x -= dy[dir];
        new_y -= dx[dir];
        result_r = new Point(new_x, new_y);
        return result_r;
    }

    private static Point moveB(Point b, int dir) {
        Point result = new Point(b.x, b.y);
        int new_x =b.x+dy[dir];
        int new_y =b.y+dx[dir];
        while(true){
           if(map[new_x][new_y].equals(".")) {
                new_x += dy[dir];
                new_y += dx[dir];
            }
           else if(map[new_x][new_y].equals("O")){
               Hole_b = 1;
               break;
           }
            else{
                break;
            }
        }
        new_x -= dy[dir];
        new_y -= dx[dir];
        result = new Point(new_x, new_y);
        return result;
    }

    public static class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

