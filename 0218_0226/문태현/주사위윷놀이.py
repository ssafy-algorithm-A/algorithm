dice = list(map(int, input().split(' ')))

#linked list 먼저 제작.
map_arr = [[0]*2 for _ in range(33)] # [][0] -> 그냥 직진 [][1]-> 꺽인점에서 시작하여 방향 바꿈.
score = [0]*33 # 지점 score
for i in range(19):
    map_arr[i][0]= i+1
map_arr[19][0]=31
for i in range(20, 22):
    map_arr[i][0]=i+1
map_arr[22][0] = 28
map_arr[23][0]=24
map_arr[24][0]=28
for i in range(25,32):
    map_arr[i][0]=i+1
map_arr[32][0]=32
# 정방향 끝
# 방향 전환
map_arr[5][1]=20
map_arr[10][1]=23
map_arr[15][1]=25
for i in range(20) :
    score[i]=2*i
score[20]=13
score[21]=16
score[22]=19
score[23]=22
score[24]=24
score[25]=28
score[26]=27
score[27]=26
score[28]=25
score[29]=30
score[30]=35
score[31]=40
score[32]=0
# map 제작 끝

horse = [0]*4  # 말 위치 배열
visited = [0]*33 # 맵에 말이 위치한 곳

#start algorithm
max_res =-1

#몇번말이 어느 거리 이동-> 그 위치 반환 함수.
def move(i, distance):
    curpos = horse[i]
    if curpos ==32 :
        return curpos
    # 방향 꺽는곳에서 시작
    if curpos ==5 or curpos==10 or curpos ==15:
        curpos=map_arr[curpos][1]
        for _ in range(distance-1):
            curpos = map_arr[curpos][0]
    # 일반적인 이동.
    else :
        for _ in range(distance):
            curpos=map_arr[curpos][0]
    return curpos

# 자료 map_arr, score, horse, visited, dice
# 재귀 완전탐색 함수 for 문을 통해 1~4번말을 이동시켜본다.
def dfs(count, total):
    global max_res
    if count>=10 :  # count가 10이면 total max 구함
        max_res = max(max_res, total)
        return
    # dice[count] == 이동해야할 거리
    distance = dice[count]
    for i in range(4): # 말 선택
        if horse[i] ==32 : continue # 끝이라 이동 불가능. -> 문제에 명시되어있음, 혹시 모두 도착해버린 후에도 count가 10이 안되면 어쩌나라는 생각했지만 그런case없었음
        cur = horse[i]
        movedpos= move(i, distance)
        if movedpos!=32 and visited[movedpos]==1:# 뭐가있어서이동불가능
            continue
        else : # 이동 가능 movedpos 로 이동.
            horse[i]=movedpos  #말실제이동
            visited[cur]=0   # 주의점. 이동했으므로 원래 있던자리 visited 빼야됨.
            visited[movedpos]=1 # 이동한 위치 visited
            dfs(count+1, total+score[movedpos])  #
            visited[movedpos]=0   # 여기서부터 depth 빠져나왔으므로 visited 와 horse 원상복구 해야됨.
            visited[cur]=1
            horse[i]=cur


dfs(0, 0)

print(max_res)
