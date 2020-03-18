package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N_9935 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		char[] c = str.toCharArray();
		String boom = br.readLine();
		char[] result = new char[1000000];
		int index = 0; // 결과 배열에서의 index

		for (int i=0; i < c.length; i++) {
			result[index++] = c[i];
			if(index < boom.length()) {
				continue;
			}
			if (result[index-1] == boom.charAt(boom.length()-1)) {
				boolean check = true;
				for (int j = 0; j < boom.length(); j++) {
					if (result[index - j -1] != boom.charAt(boom.length()-j-1)) {
						check = false;
						break;
					}
				}
				if (check) { // true면 같다는 것이므로 인덱스 만큼 빼주기
					index -= boom.length();
				} 
			}
		}

		if (index == 0) {
			System.out.println("FRULA");
		} else {
			for (int j = 0; j < index; j++) {
				System.out.print(result[j]);
			}
		}
	}

}
