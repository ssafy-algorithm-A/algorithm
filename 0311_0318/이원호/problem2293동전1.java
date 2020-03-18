package 백준;

/**
 * 메모리 : 19132
 *  시간 	: 284
 * 
 */
import java.util.Scanner;

public class problem2293동전1 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int row = sc.nextInt();
		int numArr[] = new int[row + 1];
		int col = sc.nextInt();

		for (int i = 1; i < numArr.length; i++) {
			numArr[i] = sc.nextInt();
		}//입력 끝

		int arr[][] = new int[numArr.length][col + 1];	//표만들기

		
		for (int i = 1; i < numArr.length; i++) {//row 1,2,5
			for (int j = 1; j <= col; j++) {//col 10만드는 경우의수
				int sum = 0;
				
				for (int k = 1; k <= i; k++) {//1로 만들수있는 1~10 부터 1,2,5로 만들수있는 1~10을만들수있는 경우의수 
					int tmp = j - numArr[k];
					
					if(tmp == 0) {//0이면 1로 치고 //음수면 안더함
						sum++;
					}
					else if(tmp > 0) {// 0 보다 크면 표로 찾아들어가 더함
						sum += arr[k][tmp];
					}
				}
				arr[i][j] = sum;
			}
		}
		
		System.out.println(arr[row][col]);

		sc.close();

	}

}
