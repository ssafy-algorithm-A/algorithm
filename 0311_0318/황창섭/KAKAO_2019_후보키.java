import java.util.ArrayList;

// �׽�Ʈ 18 ��	��� (2.61ms, 52.1MB)
// �׽�Ʈ 19 ��	��� (2.67ms, 50.8MB)
// �׽�Ʈ 20 ��	��� (4.52ms, 52.6MB)
// �׽�Ʈ 21 ��	��� (15.53ms, 53MB)
// �׽�Ʈ 22 ��	��� (5.64ms, 52.1MB)

public class KAKAO_2019_�ĺ�Ű {
	private static ArrayList<ArrayList<Integer>> arr; // �ĺ�Ű���� ���� arr
	private static int[] trr; // ���� ������ ��� ���� �迭
	private static int[] idx; // �����̼� ���� �ε����� ������ �迭{0,1,2,3,....}

	public static void main(String[] args) {

		String[][] relation = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
				{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
				{ "600", "apeach", "music", "2" } };
		int answer = 0;
		idx = new int[relation[0].length]; // �����̼� ���� �ε����� ������ �迭

		for (int i = 0; i < relation[0].length; i++) {
			idx[i] = i;
		}

		arr = new ArrayList<ArrayList<Integer>>();

		for (int i = 1; i <= relation[0].length; i++) {
			trr = new int[i];
			comb(relation, relation[0].length, i);
			// nCr ���� r�� ���� ������Ű�� 1~n������ �����̼� ���� ����
		}
		answer = arr.size();


		System.out.println(answer);

	}

	/**
	 * @param relation ����� �������� ���ٴ� ���ڿ�
	 * @param n ���� nCr
	 * @param r ���� nCr
	 */
	public static void comb(String[][] relation, int n, int r) {
		if (r == 0) {
			/* ���յ� �ĺ�Ű�� �ּҼ� Ȯ��
			 * 1,2 �� �ĺ�Ű�� �� �������
			 * 1,2,3 �� �ּҼ��� �������� ���Ѵ�
			 * 
			 * ex)
			 * arr -> {1} "{2,3}"
			 * trr -> {2,3,4}
			 * boolean check[] = {false,false};
			 * trr�� arr�� ��� ���Ҹ� ���ϸ�
			 * check �迭�� ��� true�� ���ϹǷ�
			 * �ּҼ��� �������� �����Ƿ� �ĺ�Ű ����
			 */

			for (ArrayList<Integer> arrayList : arr) {
				boolean[] check = new boolean[arrayList.size()]; // �� �ĺ�Ű ���̿� �´� �迭 ����
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
			
			
			checkUnique(relation); // �ּҼ��� ����ϸ� ���ϼ� Ȯ��
			
			return;
		} else if (n < r) {
			return;
		}

		// ��͸� ���� ��� ���� ����
		// ������ �����̼� ������ trr�� ���
		// ex) 0,1,2 -> �й�, �̸�, ����
		// 1,3 - > �̸�, �г�
		trr[r - 1] = idx[n - 1];
		comb(relation, n - 1, r - 1);
		comb(relation, n - 1, r);

	} // end of comb

//	���ϼ� Ȯ��
	public static void checkUnique(String[][] relation) {
		ArrayList<int[]> notUnique = new ArrayList<int[]>();
		// ������� ���ϼ��� Ȯ������ ���� ���� ����
		
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

		// ��� Ű���� ���ϼ��� Ȯ���ߴٸ�
		if (notUnique.size() == 0) {
			ArrayList<Integer> tArr = new ArrayList<Integer>();
			for (int i = 0; i < trr.length; i++) {
				tArr.add(trr[i]);
			}
			arr.add(tArr);
		}
	} // end of checkUnique
}
