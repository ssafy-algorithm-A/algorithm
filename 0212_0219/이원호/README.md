### 낚시왕(해결)

* 이동횟수를 % (col -1) 나눠서 문제 해결

### 수영장(해결)

* 재귀로 풀었음 
* 처음엔 0이 아닌 집합의 연속만 재귀를 돌리려다가 그냥 완탐

### 숫자 만들기(해결)

* 중복 순열을 만들어서 풀었는데 스택 오버플로우로 넘기면서 짜는걸로 다시 작성했지만

* 다시 같은 오류

  * 지금 생각해보면 

  ```
  Sacnner sc = new Scanner(new File("input.txt"))
  를 안바꾸고 제출해서 일수도 있음
  
  수영장에서 위와같이 제출하니 같은 오류였는데
  Sacnner sc = new Scanner(System.in)
  으로 변경하니 맞음
  ```

### 파이프 옮기기(해결)

* ![image-20200218194753180](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200218194753180.png)

이부분때문에 단방향 서칭을 함 

이상함