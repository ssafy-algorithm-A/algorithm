package main.java.Temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bac14890_경사로_G4 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int L;
    static int arr[][];
    static int answer = 0;

    public static void main(String args[]) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        //입력
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N+1][N+1];

        //배열에 입력
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =1; j<=N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        answer = N*2;
        solution();
        System.out.println(answer);

    }

    private static void solution() {
        //1) 좌 우
        for(int i =1; i<=N; i++) {
            int next = arr[i][1];;
            int flag = 0;
            int count = 0;

            for (int j = 2; j <= N; j++) {
                if(arr[i][j] == next) {
                    count++;
                    continue;
                }
                //1-1)만약에 다음칸이 한칸 내리막
                else if(arr[i][j] == next - 1){
                    for(int k = j; k<(j+L); k++){
                        if(j+L-1 > N){
                            flag =1;
                            break;
                        }
                        if(arr[i][j] != arr[i][k]) flag = 1;
                    }
                    count=-1;
                    next = next - 1;
                    j = j+(L-1);
                }
                //1-2)만약에 다음칸이 한칸 오르막
                else if(arr[i][j] == next + 1){
                    if(count >= (L-1)) {
                        count = 0;
                        next = next + 1;
                    }
                    else flag = 1;
                }
                //1-3)두칸 이상이면
                else {
                    answer--;
                   break;
                }

                if(flag == 1) {
                    answer--;
                   break;
                }
            }
        }

        //2) 상하
        for(int i =1; i<=N; i++) {
            int next = arr[1][i];
            int count = 0;
            int flag = 0;

            for (int j = 2; j <= N; j++) {
                if(arr[j][i] == next) {
                    count++;
                    continue;
                }
                    //2-1)만약에 다음칸이 한칸 낮으면
                else if(arr[j][i] == next - 1){
                    for(int k = j; k<(j+L); k++){
                        if(j+L-1 > N){
                            flag =1;
                            break;
                        }
                        if(arr[j][i] != arr[k][i]) flag = 1;

                    }
                    count = -1;
                    next = next - 1;
                    j = j+(L-1);
                }
                //2-2)만약에 다음칸이 한칸 높으면
                else if(arr[j][i] == next + 1){
                    if(count >= (L-1)) count = 0;
                    else flag =1;
                    next = next +1;
                }
                //2-3)2칸이상이거나 높으면 낮으면
                else {
                    answer--;
                    break;
                }

                if(flag == 1) {
                    answer --;
                    break;
                }
            }
        }
    }
}