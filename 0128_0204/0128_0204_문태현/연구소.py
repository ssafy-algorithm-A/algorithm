import copy

N, M = map(int, input().split(' '))
box = [list(map(int, input().split(' '))) for _ in range(N)]
dx = [0,0,-1,1]
dy = [-1,1,0,0]
max_res = -1

def spread() : #queue bfs
    global max_res
    tempbox=copy.deepcopy(box)
    queue = [] #(y,x)
    for i in range(N):
        for j in range(M):
            if tempbox[i][j]==2: # queue 에 저장
                queue.insert(0, [i,j])

    while len(queue)!=0 :
        cy, cx = queue.pop()
        for i in range(4):
            nx = cx + dx[i]
            ny = cy + dy[i]
            if ny<0 or nx<0 or nx>=M or ny>=N : continue
            if tempbox[ny][nx]==0 :
                tempbox[ny][nx] = 2
                queue.insert(0, [ny, nx])
    count=0
    for i in range(N):
        for j in range(M):
            if tempbox[i][j]==0 :
                count+=1
    max_res= max(max_res, count)

def makewall(count, x, y) : #재귀 dfs
    if count ==3 :
        spread();
        return

    '''
    000
    010 -> y
    000 -> y+1
    '''
    for j in range(x, M):
        if box[y][j] ==0:
            box[y][j]=1
            makewall(count + 1, j , y)
            box[y][j]=0
    for i in range(y+1, N):
        for j in range(0, M) :
            if box[i][j]==0 :
                box[i][j]=1
                makewall(count+1, j, i)
                box[i][j]=0


makewall(0, 0, 0)

print(max_res)