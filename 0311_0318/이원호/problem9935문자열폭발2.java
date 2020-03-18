package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem9935문자열폭발2 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuffer str = new StringBuffer(br.readLine());
		StringBuffer sub = new StringBuffer(br.readLine());

		int checkSub = sub.length() - 1;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == sub.charAt(checkSub)) {//맨마지막이랑 같으면

				boolean check = true;

				for (int j = checkSub; j >= 0; j--) {//뒤에서부터 비교
					if (i - (checkSub - j) < 0 || str.charAt(i - (checkSub - j)) != sub.charAt(j)) {
						check = false;
						break;
					}
				}

				if (check) {//다맞았다면
					str.delete(i - checkSub, i + 1);// 해당문자열 지우기

					i -= (checkSub + 1);
				}
			}
		}

		if (str.toString().equals("")) {
			System.out.println("FRULA");
		} else {
			System.out.println(str);
		}
	}
}
