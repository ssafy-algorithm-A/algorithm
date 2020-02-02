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
            dfs(ny, nx)
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
while True:

    visited = [[0 for _ in range(6)] for _ in range(12)]
    boom_list = []
    flag =0
    for i in range(12):
        for j in range(6):
            if puyo[i][j]!='.':
                index_list = []
                block_count=1
                dfs(i, j)
                if block_count>=4:
                    flag=1
                    boom_list.extend(index_list)
    if flag==1:
        count+=1
    if flag==0:
        break;
    for boom in boom_list:
        puyo[boom[0]][boom[1]]= '.'
    gravity()
   # for ci in range(12):
       # print(puyo[ci])

print(count)
