package Programmers;
// �׽�Ʈ 21 ��	��� (4.26ms, 52.6MB)
public class N_���ڿ����� {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abcabcabcabcdededededede";
		
		System.out.println(solution(s));
	}
	
	public static int solution(String s) {
        int answer = s.length();
        
        for (int i = 1; i <= (s.length()/2); i++) {
			int index = 0; // ���� ���ڿ� index
        	String str = s.substring(index, i); // Ȯ���� ���ڿ�
        	int result = 0; // ���� ���ڿ� ����
        	int count = 0;
			while(index+i <= s.length()) { // ���ڿ� ���̸�ŭ �ݺ�
				if(str.equals(s.substring(index, index+i))){ // ���ڿ� ���ؼ� ������ ���ڼ���
					count++;
				}else { // �ٸ��� count�ʱ�ȭ�� �ٸ� ���ڿ��� �ٲ�� ��
					if(count == 1) { // count 1�̸� �����ؾ� �ϹǷ� ���̸�ŭ result�� ���ϱ�
						result += str.length();
					}else { // 1�ƴϸ� ������ �Բ� �ݺ����ڿ����̸� result�� ���ϱ�
						result += ((int)(Math.log10(count)+1) + str.length());						
					}
					count = 1; // count �ʱ�ȭ
					str = s.substring(index, index+i); // ���ο� ���ڿ��� ��ü
				}
				index += i; // �ε����� �ڸ� ���̸�ŭ �ٲ��ֱ�
			}
			// ������ ���ڿ��� count�� ���� while�� ���������Ƿ� �� ������ �ʿ���
			if(count == 1) { // count 1�̸� �����ؾ� �ϹǷ� ���̸�ŭ result�� ���ϱ�
				result += str.length();
			}else { // 1�ƴϸ� ������ �Բ� �ݺ����ڿ����̸� result�� ���ϱ�
				result += ((int)(Math.log10(count)+1) + str.length());						
			}
			result += (s.length() % i); // ���ڿ��� �� �̻� �ڸ� �� �����Ƿ� ���� ���ڿ� ���̸�ŭ result�� ���ϱ�
			answer = Math.min(answer, result); // min�� ���ϱ�
		}
        return answer;
    }
}
