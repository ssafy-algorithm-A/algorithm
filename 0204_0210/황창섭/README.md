# 2020/02/04~2020/02/10



## 게리맨더링(성공)

- 모든 set 조합을 이용한 완전탐색 풀이
- 중간에 연결이 안되거나 answer보다 커지면 break
- 메모리 17848KB 시간 128ms



## 디저트 카페(성공)

- 일반적인 좌우 탐색이 아닌 대각선 탐색이므로 dr, dc 다르게 설정
- ↘ ↙ ↘ 나 ↖↙↖  등의 탐색이 나오지 않도록 idx 설정



## 미생물 격리(성공)

- 맵을 요구하지 않은 문제이지만 따로 맵을 생성하여 
  현재 미생물의 위치를 인덱스를 통해 탐색하기 쉽도록 설계
- 둘 이상의 미생물이 합칠경우 예외 케이스가 발생할 수 있으므로
  tempMax 변수를 생성하여 방지

