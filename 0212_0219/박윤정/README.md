# [BaekJoon] 파이프 옮기기1 17070  
dfs 활용  
파이프의 첫 위치 (1,1),(1,2) 를 1로 변경하고 진행  
재귀 활용시 종료 조건은 ex, ey라는 파이프의 끝 위치를 나타내는 변수를 활용하여 이 좌표가 마지막 곳에 도달하거나 범위밖으로 벗어나는 것  
전자의 경우에만 count를 세고 종료  
for문 활용하여 주어진 세가지 방향의 경우를 순차적으로 탐색, 이때 i==2 (대각선방향)인 경우는 위, 왼 방향 칸도 검사해주어야 함  
방문 표시를 할 때에도 i==2 인 경우는 위, 왼을 따로 방문 후 해제  


# [BaekJoon] 낚시왕 17143  
낚시왕이 상어 죽이기 -> 상어 이동 -> 같은 곳 상어 죽이기  
상어 정보를 입력받아 Queue로 만들어서 관리  
index변수로 낚시왕 위치 관리, index 위치에 있는 상어 찾는데 이때 가장 x좌표가 작은 상어 저장해두고, 상어 객체 죽이기  
상어 speed만큼 이동하는데 범위 벗어나면 (벗어나기 전까지 가다가 방향바꾸고) 이를 반복 후 우선순위 큐에 모두 넣음  
우선순위 큐를 사용하여 큰 값이 먼저 나오게 하여 크기가 가장 큰 상어만 남기고 다 죽인다, HashMap을 활용하여 우선순위 큐에서 꺼낸 순서대로 넣고 이미 존재한다면 버린다. 이 값들을 다시 큐에 넣고 반복  
!!! 상어가 한 칸씩 이동하게 할 경우 시간초과가 난다는 점을 유의  



# [SWEA] 수영장 1952  
재귀 활용  
1년치 비용은 테스트케이스마다 한번만 가능하므로 시간을 조금이나마 줄이기 위해 최솟값을 1년치 비용으로 초기화하고 시작  
재귀를 활용하여 비용의 경우의 수를 따진다.  
이때 종료조건은 파라미터로 현재 달을 저장하는 month를 활용하여 12에 도달하면 최소값 저장 후 종료  
파라미터에 sum을 활용하여 비용 저장, 하루, 한달인 경우 +1, 세달인 경우 +3  



# [SWEA] 숫자 만들기 4008  
재귀 활용  
연산자를 받아서 배열로 관리  
숫자는 순서가 바뀌지 않으므로 재귀를 활용하여 부호의 경우의 수를 따져준다.  
이때 파라미터에 숫자의 index를 가지고 호출하며 종료 조건은 숫자의 크기에 도달했을 때이다.   
