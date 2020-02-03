def dfs(i,j) :
    global block_count
    visited[i][j]=1
    index_list.append([i,j])
    cx = j
    cy = i
    for n in range(4):
        nx = cx+dx[n]
        ny = cy + dy[n]
        if nx<0 or ny<0 or nx>=6 or ny>= 12 :
            continue
        #print(ny,nx)
        if puyo[cy][cx]==puyo[ny][nx] and visited[ny][nx]!=1:
            block_count+=1
            #block_count 를 여기서 계산을 하게 됨
            dfs(ny, nx)

# 뇌절 포인트 4  gravity 하는 방법-> 한 열을 리스트에 다 담아 버리고 아래서 부터 다시 갱신함
def gravity():
    for i in range(6):
        plus_list=[]
        for j in range(11,-1,-1):
            if puyo[j][i]!='.':
                plus_list.insert(0,puyo[j][i])
                puyo[j][i]='.'
        for k in range(11, 11-len(plus_list),-1):
            puyo[k][i]=plus_list.pop()


puyo = [['.' for _ in range(6)] for _ in range(12)]

block_count=0;
visited = [[0 for _ in range(6)] for _ in range(12)]
dx = [0,0,-1,1]
dy = [-1,1,0,0]
for i in range(12):
    data = input()
    for j in range(6):
        puyo[i][j]= data[j]
count =0
#알고리즘 시작
while True: # 연쇄 턴
    visited = [[0 for _ in range(6)] for _ in range(12)]
    boom_list = []
    #뇌절 포인트1 . visited 는 한턴에 맵 전체를 탐색할때 중복을 피하기위함으로 한턴에 한번 초기화
    #2 boom_list는 모든 뿌요 한번에 터뜨리기 위해 선언
    flag =0
    for i in range(12):
        for j in range(6):
            # 모든 점을 돌며 문자가 있으면 dfs로 들어간다.
            if puyo[i][j]!='.':
                index_list = []
                block_count=1
                #index_list는 boom_list에 누적할 리스트로 한 중첩포인트(같은색 4개)당 하나이다.
                #뇌절 포인트3 : 네개 이상이면 터뜨리는걸 어떻게 할 것이냐 dfs 내부로 들어가면 도중에 판단할 수 없음
                #dfs를 모두 돌고 나서 계산된 block count가 4가 넘는다면 그 중첩포인트는 터뜨릴 수 있음
                dfs(i, j)
                if block_count>=4:
                    flag=1
                    boom_list.extend(index_list)
                    #4가 넘으므로 while문을 한턴을 더 진행하기 위해 flag를 설정하고 boom_list에 포인트 누적
    if flag==1:
        count+=1
        #결과값(몇턴) 계산
    if flag==0:
        break;
        #boom_count가 4가 넘는 것이 하나도 없다-> 더이상 턴 진행 못함 while문 break;
    for boom in boom_list:
        puyo[boom[0]][boom[1]]= '.'
        #담아놓은 중첩 포인트들 터뜨림
    gravity()
        #아래로 다 내림
   # for ci in range(12):
       # print(puyo[ci])

print(count)
