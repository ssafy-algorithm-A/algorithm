### 미세먼지

1. 퍼지기

2. 공기청정하기

   1. try catch이용

      ```java
      while (true) {
      			try {
      				int c = 0;
      				x = x + dir_x[dircount];
      				y = y + dir_y[dircount];
      
      				if (arr[x][y] == -1)
      					break;
      
      				c = arr[x][y];
      				arr[x][y] = tmp;
      				tmp = c;
      
      			} catch (ArrayIndexOutOfBoundsException e) {
      				x = x - dir_x[dircount];
      				y = y - dir_y[dircount];
      				dircount++;
      			}
      
      		}
      ```

      