def get_shark(c) :
    global sum_shark

    for j in range(1,R+1):
        if(len(map_arr[j][c])!=0):
            index = map_arr[j][c][0]
            sum_shark+= shark[index][4]
            shark[index][5]=0
            map_arr[j][c].remove(index)
            break

def move_shark():
    for i in range(len(shark)) :
        if shark[i][5]==1 : # 살아있으면
            move(i)


def move(i) :

    y= shark[i][0]
    x= shark[i][1]
    map_arr[y][x].remove(i) # map 에서 해당 상어 지움

    s= shark[i][2]
    d = shark[i][3] # 방향
    if d==1 or d==2 :
        real_s = s%((R-1)*2)
    elif d==3 or d==4 :
        real_s =s%((C-1)*2)

    while True :
        if d==1 :
            togo = min(y-1, real_s)
            y = y + dy[d]*togo
            x = x + dx[d]*togo
        elif d==2 :
            togo = min(R-y, real_s)
            y = y + dy[d] * togo
            x = x + dx[d] * togo
        elif d==3 :
            togo = min(C-x, real_s)
            y = y + dy[d] * togo
            x = x + dx[d] * togo
        elif d==4 :
            togo = min(x-1, real_s)
            y = y + dy[d] * togo
            x = x + dx[d] * togo
        real_s-=togo
        if real_s<=0 : break;
        d=change(d)


        if x<1 or y<1 or x>=C+1 or y>= R+1 : #다음 갈곳이 밖이라면
            d = change(d) # 디렉션 바꾸고 y, x  바꾼 방향으로 두번 감
            y= y+2*dy[d]
            x= x+2*dx[d]

    shark[i][0]=y            # shark 값 갱신.
    shark[i][1]=x
    shark[i][3]=d
    map_arr[y][x].append(i)# map 갱신

def change(d):
    if d==1 : return 2
    elif d==2 : return 1
    elif d==3 : return 4
    elif d==4 : return 3

def merge():
    for i in range(len(shark)) :
        if shark[i][5]==1 : # 살아있으면
            y = shark[i][0]
            x = shark[i][1]
            if len(map_arr[y][x])>1 :
                max_index=-1
                max_res=-1

                for shark_i in map_arr[y][x]:
                    shark[shark_i][5]=0
                    if(max_res<shark[shark_i][4]):
                        max_index = shark_i
                        max_res = shark[shark_i][4]
                map_arr[y][x].clear()
                shark[max_index][5]=1
                map_arr[shark[max_index][0]][shark[max_index][1]].append(max_index)




dx=[0,0,0,1,-1]
dy=[0,-1,1,0,0]
R, C, M = list(map(int, input().split(' ')))
map_arr = [[[] for _ in range (C+1)] for _ in range(R+1)]
shark = [[] for _ in range(M)]  # 0~M-1 의 상어, y,x,s,d,z,live
sum_shark=0

for i in range(M):
    shark[i] = list(map(int, input().split(' ')))
    shark[i].append(1)
    map_arr[shark[i][0]][shark[i][1]].append(i)

for c in range(1,C+2):
    get_shark(c) # 1부터 C 까지 get_shark
    if(c==C) :break
    move_shark()
    merge()

print(sum_shark)