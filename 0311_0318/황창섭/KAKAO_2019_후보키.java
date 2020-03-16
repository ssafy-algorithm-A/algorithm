import java.util.ArrayList;

// 테스트 18 〉	통과 (2.61ms, 52.1MB)
// 테스트 19 〉	통과 (2.67ms, 50.8MB)
// 테스트 20 〉	통과 (4.52ms, 52.6MB)
// 테스트 21 〉	통과 (15.53ms, 53MB)
// 테스트 22 〉	통과 (5.64ms, 52.1MB)

public class KAKAO_2019_후보키 {
	private static ArrayList<ArrayList<Integer>> arr; // 후보키들을 담은 arr
	private static int[] trr; // 뽑은 조합을 담기 위한 배열
	private static int[] idx; // 릴레이션 조합 인덱스를 저장할 배열{0,1,2,3,....}

	public static void main(String[] args) {

		String[][] relation = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
				{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
				{ "600", "apeach", "music", "2" } };
		int answer = 0;
		idx = new int[relation[0].length]; // 릴레이션 조합 인덱스를 저장할 배열

		for (int i = 0; i < relation[0].length; i++) {
			idx[i] = i;
		}

		arr = new ArrayList<ArrayList<Integer>>();

		for (int i = 1; i <= relation[0].length; i++) {
			trr = new int[i];
			comb(relation, relation[0].length, i);
			// nCr 에서 r의 수를 증가시키며 1~n까지의 릴레이션 조합 생성
		}
		answer = arr.size();


		System.out.println(answer);

	}

	/**
	 * @param relation 사용은 안하지만 들고다닐 문자열
	 * @param n 조합 nCr
	 * @param r 조합 nCr
	 */
	public static void comb(String[][] relation, int n, int r) {
		if (r == 0) {
			/* 조합된 후보키의 최소성 확인
			 * 1,2 가 후보키로 들어가 있을경우
			 * 1,2,3 은 최소성을 만족하지 못한다
			 * 
			 * ex)
			 * arr -> {1} "{2,3}"
			 * trr -> {2,3,4}
			 * boolean check[] = {false,false};
			 * trr과 arr의 모든 원소를 비교하면
			 * check 배열은 모두 true로 변하므로
			 * 최소성이 만족되지 않으므로 후보키 제외
			 */

			for (ArrayList<Integer> arrayList : arr) {
				boolean[] check = new boolean[arrayList.size()]; // 각 후보키 길이에 맞는 배열 생성
				boolean check2 = false;
				int top=-1;
				for (Integer k : arrayList) {
					for (int i = 0; i < trr.length; i++) {
						if (k == trr[i]) {
							check[++top] = true;
						}
					}
				}
				
				for (int i = 0; i < check.length; i++) {
					if(!check[i]) {
						check2 = true;
					}
				}
				
				if(!check2) {
					return;
				}
			}
			
			
			checkUnique(relation); // 최소성에 통과하면 유일성 확인
			
			return;
		} else if (n < r) {
			return;
		}

		// 재귀를 통해 모든 조합 생성
		// 생성한 릴레이션 조합은 trr에 담김
		// ex) 0,1,2 -> 학번, 이름, 전공
		// 1,3 - > 이름, 학년
		trr[r - 1] = idx[n - 1];
		comb(relation, n - 1, r - 1);
		comb(relation, n - 1, r);

	} // end of comb

//	유일성 확인
	public static void checkUnique(String[][] relation) {
		ArrayList<int[]> notUnique = new ArrayList<int[]>();
		// 현재까지 유일성을 확보하지 못한 조합 저장
		
		for (int j = 0; j < relation.length; j++) {
			for (int k = j + 1; k < relation.length; k++) {
				if (relation[j][trr[0]].equals(relation[k][trr[0]])) {
					notUnique.add(new int[] { j, k });
				}
			}
		}

		for (int i = 1; i < trr.length; i++) {
			for (int j = 0; j < notUnique.size(); j++) {
				if (!relation[notUnique.get(j)[0]][trr[i]].equals(relation[notUnique.get(j)[1]][trr[i]])) {
					notUnique.remove(j);
					j--;
				}
			}
		}

		// 모든 키들이 유일성을 확보했다면
		if (notUnique.size() == 0) {
			ArrayList<Integer> tArr = new ArrayList<Integer>();
			for (int i = 0; i < trr.length; i++) {
				tArr.add(trr[i]);
			}
			arr.add(tArr);
		}
	} // end of checkUnique
}
