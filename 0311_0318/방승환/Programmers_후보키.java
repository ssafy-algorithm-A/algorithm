import java.util.ArrayList;

class Programmers_후보키 {

	static int N, M, L;
	static int[] temp;
	static boolean[] mask;
	static String[][] rel;
	static ArrayList<String> list, answer;
	static StringBuilder sb;
	
	public static int solution(String[][] relation) {
        answer = new ArrayList();
        
        N = relation[0].length;
        M = relation.length;
        
        rel = new String[M][N];
        
        for(int i=0; i<M; i++) 
        	for(int j=0; j<N; j++) 
        		rel[i][j] = relation[i][j];
        	
        
        temp = new int[N+1];
        mask = new boolean[N+1];
        temp[0] = 1;
        
        for(int i=1; i<=N; i++) {
        	L = i;
        	list = new ArrayList();
        	recur(1, 0);
        	filtering();
        }
        return answer.size();
    }
	
	public static void filtering() {
		
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<answer.size(); j++) {
				int a=0;
				for(int k=0; k<answer.get(j).length(); k++) {
					if(list.get(i).contains("" + answer.get(j).charAt(k))) {
						a++;
					}
				}
				if(answer.get(j).length() == a) {
					list.remove(i);
					i--;
					break;
				}
			}
		}
		for(int i=0; i<list.size(); i++) {
			answer.add(list.get(i));
		}
	}

	public static void recur(int start, int depth) {
		if (depth == L) {
			
			// temp 에 확인할 열의 인덱스가 들어있다.
			checking();

			return;
		}
		for (int i = temp[start - 1]; i <= N; i++) {
			if (mask[i])
				continue;
			temp[start] = i;
			mask[i] = true;
			recur(start + 1, depth + 1);
			mask[i] = false;
		}
	}

	public static void checking() {

		for (int i = 0; i < M; i++) {
			String st1 = "";
			for (int select = 1; select <= L; select++)
				st1 += rel[i][temp[select] - 1];

			for (int j = i + 1; j < M; j++) {
				String st2 = "";
				for (int select = 1; select <= L; select++)
					st2 += rel[j][temp[select] - 1];

				if (st1.equals(st2)) {
					return;
				}
			}
		}

		sb = new StringBuilder();
		
		for(int i=1; i<=L; i++) {
			sb.append(temp[i]-1);
		}
		
		list.add(sb.toString());
		
	}
}
