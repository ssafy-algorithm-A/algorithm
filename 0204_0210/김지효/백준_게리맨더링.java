import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class 백준_게리맨더링 {
	static int N;
	static int []capacity; //각 구역별 인구수를 기록한다.
	static int [][]Map; //각각이 누구와 연결되 있는지 기록해 놓는다.
	static int []Aarea; //A구역에 대한 정보
	static int []Barea; //B구역에 대한 정보
	static boolean []visit;
	static int min = 9999;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		capacity = new int[N+1]; // 인덱스 1부터 시작이니 +1 해준다.
		Map = new int[N+1][];
		for(int i = 1; i <= N; i++) {
			capacity[i] = scan.nextInt();
		}
		//각각 N개의 구역들이 연결된 곳들을 입력받는다.

		int index = 1; //N번 입력받는다.
		while(index <= N) {
			int size = scan.nextInt();	
			Map[index] = new int[size];
			for(int j = 0; j < size; j++) {
				Map[index][j] = scan.nextInt();
			}			
			index++;
		}
		
		//재귀함수를 할 때 기준이되는 부분집합의 개수를 N-1개 까지 돌려준다.
		for(int i = 1; i <= N-1; i++) {
			Aarea = new int[i];
			Barea = new int[N-i];
			
			Recurse(i, 0, 1);
		}
		if(min == 9999) System.out.println(-1);
		else System.out.println(min);
		
	}
	static void Recurse(int end, int start, int x) {
		if(start == end) {
			int sum = 0; // 인구 수의 차이를 저장한다.
			boolean drop = true;
			visit = new boolean[N+1];
			dfs(Aarea[0], Aarea); //자신의 첫번 쨰 구역과 자기 자신을 보낸다.
			
			for (int i = 0; i < Aarea.length; i++) {
				//Aarea중에서 visit하지 못한곳이 하나라도 있다면 실패이다.
				if(!visit[Aarea[i]]) {
					drop = false;
					break;
				}
				sum += capacity[Aarea[i]]; //그 구역의 인구수를 더한다.
			}
			if(drop) {
				//A구역이 기준을 통과 했다면 B구역에 대한 DFS 실행
				int j = 0;
				for(int i = 1; i < visit.length; i++) {
					//visit에서 Aarea에 속하지 않은 구역들을 Barea에 넣어준다.
					if(visit[i] == false) {
						Barea[j] = i;
						j++;
					}
				}
				dfs(Barea[0], Barea);
				//System.out.println("==============");
				int temp = 0;
				for (int i = 0; i < Barea.length; i++) {
					if(!visit[Barea[i]]) {
						drop = false;
						break;
					}
					temp += capacity[Barea[i]]; //그 구역의 인구수를 더한다.
				}
				sum -= temp;
				//drop이 없어야 min값을 변화시킨다.
				if(drop) {
					if(sum < 0) sum = -sum;
					if(min > sum) min = sum;
				}
			}
		}
		else {
			//N개의 구역 중에서 재귀 하기 전의 start값 보다 큰 값만 쓴다.
			for(int i = x; i <= N; i++) {
				Aarea[start] = i;
				if(start == 0 && i > N-end+1) break; 
				Recurse(end, start+1, i+1);
			}
		}
	}
	static void dfs(int i, int[] area){
        visit[i] = true;   // 함수 호출 시, visit 했음을 표시
        for(int j = 1; j < area.length; j++){
        	int n = area[j];
        	//아래는 Map[i]에 연결된 곳 중에 area에 포함되는곳이 있는지 또 방문은 했는지 확인한다.
        	if(visit[n] == false && IntStream.of(Map[i]).anyMatch(x -> x == n)) {
                dfs(n, area); //방문을 하지 않고 Map에 연결된 곳이라면 이동한다.
        	}
        	
        }
    }
}
