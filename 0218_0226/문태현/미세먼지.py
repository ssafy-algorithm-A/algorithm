import time

def spread() :
    copy_map = [[0] * C for _ in range(R)]
    for i in range(R):
        for j in range(C):

            if map_arr[i][j]>=5 : # spread
                number=map_arr[i][j]//5
                for k in range(4):
                    ny= i+dy[k]
                    nx= j+dx[k]
                    if ny<0 or nx<0 or ny>=R or nx>=C : continue
                    if map_arr[ny][nx]==-1: continue
                    copy_map[ny][nx]+=number
                    map_arr[i][j]-=number


    for i in range(R):
        for j in range(C):
            map_arr[i][j]+=copy_map[i][j]

def gonggi():
    for i in range(R):
        if map_arr[i][0] == -1:
            sy = i
            break
    #위쪽
    y = sy-1
    x = 0
    while y>=1 :
        map_arr[y][x]=map_arr[y-1][x]
        y-=1
    y=0
    while x<=C-2:
        map_arr[y][x]=map_arr[y][x+1]
        x+=1
    x=C-1
    while y<=sy-1 :
        map_arr[y][x]=map_arr[y+1][x]
        y+=1
    y=sy
    while x>=2 :
        map_arr[y][x]=map_arr[y][x-1]
        x-=1
    map_arr[sy][1]=0
    # 아래쪽
    y = sy+2
    x = 0
    while y<=R-2 :
        map_arr[y][x]=map_arr[y+1][x]
        y+=1
    y=R-1
    while x<=C-2:
        map_arr[y][x]=map_arr[y][x+1]
        x+=1
    x=C-1
    while y>=sy+2 :
        map_arr[y][x]=map_arr[y-1][x]
        y-=1
    y=sy+1
    while x>=2 :
        map_arr[y][x]=map_arr[y][x-1]
        x-=1
    map_arr[sy+1][1]=0

dx = [0,0,-1,1]
dy= [-1,1,0,0]
R, C, T = list(map(int, input().split(' '))) # R=Y, C =X
map_arr = [list(map(int, input().split(' '))) for _ in range(R)]

#copy_map = [[0]*C for _ in range(R)]
ft=0
pt=0
for t in range(T) :
    spread()
    gonggi()


print(sum(map(sum, map_arr)) + 2)

