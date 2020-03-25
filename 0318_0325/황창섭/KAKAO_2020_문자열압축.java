/*
 * �׽�Ʈ 20 ��	��� (4.78ms, 54.4MB)
 * �׽�Ʈ 21 ��	��� (4.81ms, 54.2MB)
 * �׽�Ʈ 22 ��	��� (4.54ms, 54.6MB)
 * �׽�Ʈ 23 ��	��� (4.47ms, 53MB)
 * �׽�Ʈ 24 ��	��� (4.25ms, 54.7MB)
 * �׽�Ʈ 25 ��	��� (4.72ms, 54.7MB)
 */

public class KAKAO_2020_���ڿ����� {

	public static void main(String[] args) {
		String str = "ababcdcdababcdcd";
		int ans = solution(str);
		System.out.println(ans);
	}

	public static int solution(String s) {
		int answer = s.length(); // �ʱⰪ = ������ ���ڿ�����

		for (int sLen = 1; sLen <= s.length() / 2; sLen++) { // 1���� ����, 2���� ����, 3����...
			StringBuilder sb = new StringBuilder(); // ������ ���ڿ��� ���� ����

			for (int i = 0; i < s.length(); i++) {
				int n = 1; // �ݺ��Ǵ� ��
				
				if (i + sLen * 2 > s.length()) { // ������ ���� ���̺��� ���� ���ڰ� ������
					sb.append(s.substring(i)); // ���� ���� ���ϱ�
					break;
				}
				String str = s.substring(i, i + sLen); // ���� Ÿ�� ���� ����
				
				/*
				 * ���ڿ��� ababcdcd �̰� ���� ���̰� 2�� ��
				 * str = 0<= index < 2 = ab
				 * str.equals() 0<= index <2 ��  2<= index <4 ��
				 * ������ �� �ε���(i)�� sLen ��ŭ ����
				 * 0<= index <2 �� 4<= index <6 ��
				 * 
				 * �ݺ�
				 */
				while (str.equals(s.substring(i + sLen, i + sLen * 2))) {
					n++;
					i = i + sLen;
					if (i + sLen * 2 > s.length()) { // ������ ���� ���̺��� ���� ���ڰ� ������
						break;
					}
				}

				if (n > 1) {
					sb.append(n).append(str);
					i = i + sLen - 1;
				} else {
					sb.append(str);
					i = i + sLen - 1;
				}
			}
			
			
			if(answer > sb.length()) {
				answer = sb.length();
			}
		}

		return answer;
	}
}
