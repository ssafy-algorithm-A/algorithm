import java.util.Scanner;
public class SWEA_미생물격리 {
	static class microbInfo{
		//1.상 2.하 3.좌 4.우
		//미생물의 수
		int row, col, dir, num; 
		boolean die;
		microbInfo(int row, int col, int num, int dir){
			this.row = row; this.col = col;
			this.num = num; this.dir = dir;
			this.die = true; //true면 살아있는 집단이다.
		}
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		microbInfo []list;
		int TC = scan.nextInt();
		for(int testcase = 1; testcase <= TC; testcase++) {
			int N = scan.nextInt(); //판의 크기
			int hour = scan.nextInt(); //총 움직일 시간
			int microbNum = scan.nextInt(); //미생물의 수
			list = new microbInfo[microbNum];
			//int [][]Map = new int[N][N];
			//list에 미생물들의 모든 정보를 넣어둔다.
			for(int i = 0; i < microbNum; i++) {
				int row = scan.nextInt(); int col = scan.nextInt();
				int num = scan.nextInt(); int dir = scan.nextInt();
				list[i] = new microbInfo(row, col, num, dir);
			}
			//Map에 모서리 벽 방향은 다 1로 표시한다.
			/*for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(i == 0 || j == 0 || i == N-1 || j == N-1) Map[i][j] = 1; //벽으로 둘러싼다.
					else Map[i][j] = 0; //나머지는 미생물이 갈 수 있는 0으로 표시
				}
			}*/
			while(hour > 0) { //주어진 시간만큼 돌아간다.
				for(int i = 0; i < list.length; i++) {
					//미생물 정보들을 돌면서 이동한다.
					if(list[i].dir == 1) list[i].row = list[i].row-1; //상 --> row-1	
					else if(list[i].dir == 2) list[i].row = list[i].row+1; //하 --> row+1
					else if(list[i].dir == 3) list[i].col = list[i].col-1; //좌 --> col-1
					else if(list[i].dir == 4) list[i].col = list[i].col+1; //우 --> col+1		
				}
				//미생물들에 대해서 겹쳤을 경우에 대한 처리
				for(int i = 0; i < list.length; i++) {
					int sum = list[i].num; //바뀌게 될 녀석
					int standard = list[i].num; //현재의 가장 큰 녀석의 미생물 개수
					if(list[i].die) {
						for(int j = 0; j < list.length; j++) {
							//자신이랑 같은게 있는지 본다.
							if(!list[j].die) continue;
							if(list[i].row == list[j].row && list[i].col == list[j].col && i != j) {
								sum += list[j].num; //비교한 녀석의 수 만큼 추가한다..
								if(standard < list[j].num) {
									standard = list[j].num; //기준 크기를 바꿔준다.
									list[j].die = false; //일단 죽은체로 만든다. //i는 무조건 살려서 갱신
									list[i].dir = list[j].dir; //그리고 더 큰 녀석의 dir을 넣어준다.
								}
								else { //기준이 내가 더 크다면 상대방을 죽인다.
									list[j].die = false; //비교당한 녀석이 죽는다.
									list[j].dir = list[i].dir;
								}
								
							}
						}
					}
					list[i].num = sum;
					
				}
				for(int i = 0; i < list.length; i++) {
					//미생물들이 이동한 뒤의 현재 상황에 대해서 변경해준다.
					//미생물이 벽에 있는 경우
					if(list[i].row == 0 || list[i].col == 0 || list[i].row == N-1 || list[i].col == N-1) {
						if(list[i].dir == 1) list[i].dir = 2; //반대방향으로 바꿔줌
						else if(list[i].dir == 2) list[i].dir = 1;
						else if(list[i].dir == 3) list[i].dir = 4;
						else if(list[i].dir == 4) list[i].dir = 3;
						list[i].num = list[i].num/2; //미생물 수를 반으로 줄여준다.
					}
				}
				hour--;
			}
			int sum = 0;
			for(int i = 0; i < list.length; i++) {
				if(list[i].die) sum += list[i].num; 
			}
			System.out.println("#"+testcase+ " "+sum);
			
		}
		
	}

}
