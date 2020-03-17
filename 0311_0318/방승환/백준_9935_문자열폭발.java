import java.util.Scanner;

// 9935 문자열 폭발
public class 백준_9935_문자열폭발 {
	static char[] stack;
	static String first, second;
	static int top=0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		first = sc.next();
		second = sc.next();
		char[] f = first.toCharArray();
		char[] s = second.toCharArray();

		char last = s[s.length - 1];
		stack = new char[f.length];
		
		Outter: for (int i = 0; i < f.length; i++) {
			
			stack[top++] = f[i];
			
			if (f[i] == last) {
				int len = s.length;
				int x = top;
				for (int j = len - 1; j >= 0; j--) {
					if(x-1<0) continue Outter;
					if (stack[x - 1] != s[j]) continue Outter;
					else x--;
				}
				top -= len;
			}
		}

		if (top==0)
			System.out.println("FRULA");
		else {
			for (int i = 0; i < top; i++) {
				System.out.print(stack[i]);
			}
		}

	}
}
