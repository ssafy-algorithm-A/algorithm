package practice;

import java.util.Arrays;
import java.util.Scanner;


/**
 * 4방향 탐색으로 만들어지는 사각형 모양 탐색을 모두 진행하면서
 * 가지치기를 통해 케이스를 줄임
 * 재귀 함수를 이용한다.
 *  : 1. 좌표(y,x) 를 들고 다니며 재귀
 *    2. 가지치기
 *     1) 맵 범위를 넘을 때 2) 방문했던 곳일때 3) 같은 종류의  dessert 판매  ->return
 *     2)3) 을 위해 visitedmap, dessert 배열 생성
 * 	  3. 가던방향과 다음방향 두 방향으로 탐색
 * 	  4. 시작점을 만나면 result 계산
 * @author multicampus
 *
 */
public class 디저트카페{
    public static int[][] map;
    public static int[] dessert= new int[101];
    public static int[][] visitedmap;
    public static int[] dx = {1,-1,-1,1};
    public static int[] dy = {1,1,-1,-1};
    public static int sx;
    public static int sy;
    public static int N;
    public static int max_res;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
         
        for (int t = 0; t < T; t++) {
            max_res =-1;
            N = sc.nextInt();
            map = new int[N][N];
            visitedmap = new int[N][N];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
             
            for (int i = 0; i < N-2; i++) {
                for (int j = 1; j < N-1; j++) {
                    sx=j;
                    sy=i;
                    dessert[map[i][j]]=1;
                    visitedmap[i][j]=1;
                    findway(i, j, 1, 0);
                    visitedmap[i][j]=0;
                    dessert[map[i][j]]=0;
                }
            }
            System.out.println("#"+(t+1)+" "+max_res);
             
        }
    }
    public static void findway(int y,int x, int count, int dir) {
 
        if(dir>3) {//return
            return;
        }
        int ny = y + dy[dir];
        int nx = x +dx[dir];
         
        if (sy==ny && sx == nx) {
            max_res=Math.max(max_res,count);
        }
        if(ny<0 || nx <0 || ny>=N || nx>= N) {
            return;
        }
        if (visitedmap[ny][nx]==1) {//return
            return;
        }
        if(dessert[map[ny][nx]]==1) {
            return;
        }
        visitedmap[ny][nx]=1;
        dessert[map[ny][nx]]=1;
        findway(ny, nx, count+1, dir);
        findway(ny, nx, count+1, dir+1);
        visitedmap[ny][nx]=0;
        dessert[map[ny][nx]]=0;
         
         
    }
}