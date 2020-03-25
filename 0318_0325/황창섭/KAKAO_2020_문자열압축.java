/*
 * 테스트 20 〉	통과 (4.78ms, 54.4MB)
 * 테스트 21 〉	통과 (4.81ms, 54.2MB)
 * 테스트 22 〉	통과 (4.54ms, 54.6MB)
 * 테스트 23 〉	통과 (4.47ms, 53MB)
 * 테스트 24 〉	통과 (4.25ms, 54.7MB)
 * 테스트 25 〉	통과 (4.72ms, 54.7MB)
 */

public class KAKAO_2020_문자열압축 {

	public static void main(String[] args) {
		String str = "ababcdcdababcdcd";
		int ans = solution(str);
		System.out.println(ans);
	}

	public static int solution(String s) {
		int answer = s.length(); // 초기값 = 압축전 문자열길이

		for (int sLen = 1; sLen <= s.length() / 2; sLen++) { // 1길이 압축, 2길이 압축, 3길이...
			StringBuilder sb = new StringBuilder(); // 압축한 문자열을 담을 변수

			for (int i = 0; i < s.length(); i++) {
				int n = 1; // 반복되는 값
				
				if (i + sLen * 2 > s.length()) { // 압축할 문자 길이보다 남은 문자가 적으면
					sb.append(s.substring(i)); // 남은 문자 더하기
					break;
				}
				String str = s.substring(i, i + sLen); // 압축 타겟 문자 선정
				
				/*
				 * 문자열이 ababcdcd 이고 압출 길이가 2일 때
				 * str = 0<= index < 2 = ab
				 * str.equals() 0<= index <2 와  2<= index <4 비교
				 * 같으면 비교 인덱스(i)에 sLen 만큼 더해
				 * 0<= index <2 와 4<= index <6 비교
				 * 
				 * 반복
				 */
				while (str.equals(s.substring(i + sLen, i + sLen * 2))) {
					n++;
					i = i + sLen;
					if (i + sLen * 2 > s.length()) { // 압축할 문자 길이보다 남은 문자가 적으면
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
