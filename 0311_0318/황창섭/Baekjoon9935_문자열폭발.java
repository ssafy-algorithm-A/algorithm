import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실행시간 240ms

public class Baekjoon9935_문자열폭발 {
	private static String sStr;
	private static String sBomb;
	private static char[] str; // 폭발할 문자열을 담은 배열
	private static char[] bomb; // 폭발 문자열
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sStr = br.readLine();
		sBomb = br.readLine();
		int bL = sBomb.length();
		int sL = sStr.length();

		str = new char[sL];
		bomb = new char[bL];
		int top = -1;

		for (int i = 0; i < bL; i++) {
			bomb[i] = sBomb.charAt(i);
		}
		//////////////// 여기까지 입력부 ///////////////////

		loop: for (int i = 0; i < sL; i++) {
			str[++top] = sStr.charAt(i); // 문자열에 있는 문자를 str에 담음
			if (str[top] == bomb[bL - 1]) { // 지금 담은 문자가 폭발 문자열의 마지막 문자라면
				int tempTop = top; // 현재 인덱스의 위치를 보관하고 tempTop으로 확인
				if (tempTop >= bL-1) { // 스택처럼 하나씩 문자를 빼면서 폭발 문자열인지 확인
					for (int j = 1; j <= bL; j++) {
						if (str[tempTop--] != bomb[bL - j]) {
							continue loop; // 원호 따봉 ^^b
						}
					}
					top = tempTop; // 폭발 문자열의 경우 tempTop으로 인덱스 변경
				}
			}
		}

		if (top < 0) { // 문자가 남아있지 않다면
			System.out.print("FRULA");
		} else {
			for (int i = 0; i <= top; i++) {
				sb.append(str[i]);
			}
			System.out.print(sb);
		}

	}
}
