import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class swea4008_숫자만들기 {
	static int [] operators = new int [4];//+ - * / 순서
	static int [] numbers;
	static String [] list;
	static HashSet<String> set;
	static int answer=Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(),"");
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			set = new HashSet<String>();
			st = new StringTokenizer(br.readLine(),"");
			int N = Integer.parseInt(st.nextToken());
			numbers = new int [N];			
			st = new StringTokenizer(br.readLine()," ");
			int idx = 0,sum=0;
			while(st.hasMoreTokens()) {//연산자의 개수 입력
				operators[idx]=Integer.parseInt(st.nextToken());
				sum+=operators[idx++];
			}
			idx = 0;
			st = new StringTokenizer(br.readLine()," ");
			while(st.hasMoreTokens()) {//수식에 사용되는 숫자 입력
				numbers[idx++]=Integer.parseInt(st.nextToken());
			}//입력 종료
			list = new String[sum];
			idx = 0;
			int lidx = 0;
			while (true) {
				if (idx == 0 && operators[idx] > 0) {
					list[lidx++] = "+";
					operators[idx]--;
				}
				else if (idx == 1 && operators[idx] > 0) {
					list[lidx++] = "-";
					operators[idx]--;
				}
				else if (idx == 2 && operators[idx] > 0) {
					list[lidx++] = "*";
					operators[idx]--;
				}
				else if (idx == 3 && operators[idx] > 0){
					list[lidx++] = "/";
					operators[idx]--;
				}
//				System.out.println(idx+" "+(operators.length-1)+ Arrays.toString(operators));
				if (idx == operators.length-1 && operators[idx] == 0) break;
				if (operators[idx] == 0) idx++;
			}//end while - 배열에 기호들 전부 넣어줌
			int [] a = new int [list.length];
			backtrack(a,0,a.length);
			System.out.println("#"+t+" "+calculate(set));
		}//end tc
	}//end main
	public static int calculate(HashSet<String> set) {
		Iterator<String> it = set.iterator();
		StringTokenizer st;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		while(it.hasNext()) {//set내에 저장된 + + - / 문자열
			String s = it.next();
			st = new StringTokenizer(s," ");
			int index = 0;
			int ans=numbers[index];
			while(st.hasMoreTokens()) {
				String tmp = st.nextToken();
				if (tmp.equals("+")) {
					ans+=numbers[++index];
				} else if (tmp.equals("-")) {
					ans-=numbers[++index];
				} else if (tmp.equals("*")) {
					ans*=numbers[++index];
				} else if (tmp.equals("/")){// 나누기일때
					ans/=numbers[++index];
				}
			}//end : st.hasMoreTokens
			if (ans <= min) min = ans;
			if (ans >= max) max = ans;
		}//end : it.hasNext
		return max-min;
	}
	/** []a : 배열원소의 사용 유무를 저장할 배열, k : 현재 단계, input : 단계의 끝 k==input이면 종료*/
	public static void backtrack(int [] a, int k, int input) {
		if (k==input) {//종료파트
			process_solution(a,k); //현재단계에서 만들어낸 순열을 출력
		} else {//재귀파트
			int [] c = new int [a.length];// 후보군을 담을 배열 : 사용하지 않은 숫자
			int ncands = make_candidates(a,k,input,c);// 후보군의 개수
			for (int i = 0; i < ncands; i++) {
				a[k] = c[i];
				backtrack(a, k+1, input);
			}
		}
	}//end backtrack
	public static int make_candidates(int[] a, int k, int input, int[] c) {
		boolean [] in_perm = new boolean [a.length];
		for (int i = 0; i < k; i++) { 
			in_perm[a[i]] = true;
		}
		int ncands = 0;
		for (int i = 0; i < in_perm.length; i++) {
			if (!in_perm[i]) {
				c[ncands] = i;
				ncands++;
			}
		}
		return ncands;	//후보군의 개수 리턴
	}//end make candidates
	/** a배열의 원소를 보고 순열출력*/
	public static void process_solution(int [] a, int k) {
		String s = "";
		//앞으로 해줘야할 것.
		//10번 tc에서 타임아웃
		for (int i = 0; i < k; i++) {
				s += list[a[i]] + " ";
		}
		set.add(s);
	}//end process_solution
}//end class

/* 타임아웃남
 *	12
 * 2 1 6 2
 * 2 3 7 9 4 5 1 9 2 5 6 4
 * 1. +, -, *, / 일부러 보기 쉽게 만들어서 넣어줬는데 굳이 그럴필요 없을듯
 * 2. 접근방식이 잘못됐나 
 */
