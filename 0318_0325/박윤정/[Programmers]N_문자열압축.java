package Programmers;
// 테스트 21 〉	통과 (4.26ms, 52.6MB)
public class N_문자열압축 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abcabcabcabcdededededede";
		
		System.out.println(solution(s));
	}
	
	public static int solution(String s) {
        int answer = s.length();
        
        for (int i = 1; i <= (s.length()/2); i++) {
			int index = 0; // 현재 문자열 index
        	String str = s.substring(index, i); // 확인할 문자열
        	int result = 0; // 최종 문자열 길이
        	int count = 0;
			while(index+i <= s.length()) { // 문자열 길이만큼 반복
				if(str.equals(s.substring(index, index+i))){ // 문자열 비교해서 같으면 숫자세기
					count++;
				}else { // 다르면 count초기화와 다른 문자열로 바꿔야 함
					if(count == 1) { // count 1이면 생략해야 하므로 길이만큼 result에 더하기
						result += str.length();
					}else { // 1아니면 개수와 함께 반복문자열길이를 result에 더하기
						result += ((int)(Math.log10(count)+1) + str.length());						
					}
					count = 1; // count 초기화
					str = s.substring(index, index+i); // 새로운 문자열로 대체
				}
				index += i; // 인덱스를 자른 길이만큼 바꿔주기
			}
			// 마지막 문자열은 count만 세고 while문 빠져나오므로 이 과정이 필요함
			if(count == 1) { // count 1이면 생략해야 하므로 길이만큼 result에 더하기
				result += str.length();
			}else { // 1아니면 개수와 함께 반복문자열길이를 result에 더하기
				result += ((int)(Math.log10(count)+1) + str.length());						
			}
			result += (s.length() % i); // 문자열을 더 이상 자를 수 없으므로 남은 문자열 길이만큼 result에 더하기
			answer = Math.min(answer, result); // min값 구하기
		}
        return answer;
    }
}
