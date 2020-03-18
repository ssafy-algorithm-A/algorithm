package Programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class N_�ĺ�Ű {
	public static int[] number;
	public static List<Integer> indexList;
	public static int result;
	public static HashSet<String> remove;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] relation = {
			{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}
		};

		System.out.println(solution(relation));
	}
	
	public static int solution(String[][] relation) {
        int answer = 0;
        remove = new HashSet<>();
        indexList = new ArrayList<>();
        result = 0;
        for (int j = 0; j < relation[0].length; j++) {
        	HashSet<String> hs = new HashSet<>();
			for (int i = 0; i < relation.length; i++) {
				hs.add(relation[i][j]);
			}
			if(hs.size() != relation.length) { // ���ϼ� �������ϸ�
				indexList.add(j); // �� ��ȣ �ֱ�
			}else {
				result++;					
			}
		}
        
        for (int i = 2; i <= indexList.size(); i++) {
        	number = new int[i];
			comb(indexList.size(), i, relation);
		}
        
        answer = result;
        return answer;
    }
	
	public static void comb(int n, int r, String[][] relation) {
		if(n<r) { // ���� �̴°� �Ұ����� ���
			return;
		}
		if(r==0) { // ���̻� ���� ���� ���� ���
			HashSet<String> hs = new HashSet<>();
			String key = "";
			for (int i = 0; i < number.length; i++) {
				key += number[i] +",";
			}
			for (int i = 0; i < relation.length; i++) {
				String str = "";
				for (int j = 0; j < number.length; j++) {
					str += relation[i][number[j]] +",";
				}
				hs.add(str);
			}
			if(hs.size() == relation.length) {
				boolean flag = false;
				for(String s : remove) {
					int check = 0;
					String[] temp = s.split(",");
					for (int i = 0; i < temp.length; i++) {
						if(key.contains(temp[i])) {
							check++;
						}
					}
					if(check == temp.length) {
						flag = true;
						break;
					}
				}
				if(!flag) {
					remove.add(key);
					result++;		
				}
			}
			return;
		}
		
			
		// ����
		number[r-1] = indexList.get(n-1);
		comb(n-1, r-1, relation);
		// ����
		comb(n-1,r, relation);
	}
}
