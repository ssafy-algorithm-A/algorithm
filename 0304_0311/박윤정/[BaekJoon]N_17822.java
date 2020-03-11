package BaekJoon;
// 164ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class N_17822 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken()); // X�� ���
			int D = Integer.parseInt(st.nextToken()); // ���� 0,1
			int K = Integer.parseInt(st.nextToken()); // K�� �̵�

			int[] map = new int[M+1];
			// ȸ���ϱ�
			for (int j = 1; j <= N; j++) { 
				if(j % X == 0) { // ��ȣ�� X�� ����� ����
					if(D == 0) { // �ð����
						int k;
						for (k = (1+K); k <= M; k++) {
							map[k] = arr[j][k-K];
						}
						int index = 1;
						while(true) {
							map[index++] = arr[j][k-K];
							if((k-K) == M) break;
							k++;
						}
					}else { // �ݽð����
						int k;
						for (k = (1+K); k <= M; k++) {
							map[k-K] = arr[j][k];
						}
						int index = 1;
						while(true) {
							map[k-K] = arr[j][index++];
							if((k-K) == M) break;
							k++;
						}
					}
					
					for (int j2 = 1; j2 <= M; j2++) {
						arr[j][j2] = map[j2];
					}
				}
			}
			
			// ������ ���̸鼭 ���� �� ã��
 			HashSet<String> hs = new HashSet<>();
			for (int j = 1; j <= N; j++) { // ������ j�� ��
				for (int j2 = 1; j2 <= M; j2++) { // ��° ��
					if(arr[j][j2] == -1) continue; // ������� �ѱ��
					boolean check = false; // �������� üũ
					// ���ǳ��� �˻�
					if(j == 1) {
						if(arr[j][j2] == arr[j+1][j2]) {
							hs.add((j+1)+","+j2);
							check = true;
						}	
					}else if(j == N) {
						if(arr[j][j2] == arr[j-1][j2]) {
							hs.add((j-1)+","+j2);
							check = true;
						}
					}else {
						if(arr[j][j2] == arr[j+1][j2]) {
							hs.add((j+1)+","+j2);
							check = true;
						}
						if(arr[j][j2] == arr[j-1][j2]) {
							hs.add((j-1)+","+j2);
							check = true;
						}				
					}
					
					// �� ���� ������ �� ã��
					if(j2 == 1) {
						if(arr[j][j2] == arr[j][j2+1]) {
							hs.add(j+","+(j2+1));
							check = true;
						}
						if(arr[j][j2] == arr[j][M]) {
							hs.add(j+","+M);
							check = true;
						}	
					}else if(j2 == M) {
						// M �˻�
						if(arr[j][j2] == arr[j][j2-1]) {
							hs.add(j+","+(j2-1));
							check = true;
						}
						if(arr[j][j2] == arr[j][1]) {
							hs.add(j+","+1);
							check = true;
						}
					}else {
						if(arr[j][j2] ==  arr[j][j2+1]) {
							hs.add(j+","+(j2+1));
							check = true;
						}
						if(arr[j][j2] == arr[j][j2-1]) {
							hs.add(j+","+(j2-1));
							check = true;
						}	
					}
					
					if(check) { // ������ ���̸� ���� �� ������
						hs.add(j+","+j2);
					}
				}
			}
			
			if(hs.size() > 0) { // ������ ���̸� ���� �� ������
				for (String str : hs) {
					String[] temp = str.split(",");
					int x = Integer.parseInt(temp[0]);
					int y = Integer.parseInt(temp[1]);
					arr[x][y] = -1;
				}
			}else {
				int sum = 0;
				int num = 0;
				for (int a = 1; a <= N; a++) { // ����� ���� sum, num ���ϱ�
					for (int b = 1; b <= M; b++) {
						if(arr[a][b] == -1) continue; // ����� �Ÿ���
						sum += arr[a][b];
						num++;
					}
				}
				
				double avg;
				if(num == 0) { // 0/0
					avg = 0;
				}else { // ��� ���ϱ�
					avg = (double)sum / num;
				}
				
				for (int a = 1; a <= N; a++) {
					for (int b = 1; b <= M; b++) {
						if(arr[a][b] == -1) continue; // ����� �Ÿ���
						if(arr[a][b] > avg) { // ��պ��� ũ�� -1
							arr[a][b] -= 1;
						}else if(arr[a][b] < avg) { // ��պ��� ũ�� +1
							arr[a][b] += 1;
						}
					}
				}
			}
			
		}
		
		int sum = 0;
		for (int a = 1; a <= N; a++) { // ���� ���ϱ�
			for (int b = 1; b <= M; b++) {
				if(arr[a][b] == -1) continue; // ����� �Ÿ���
				sum += arr[a][b];
			}
		}
		System.out.println(sum);
	}

}
