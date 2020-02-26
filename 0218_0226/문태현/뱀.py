def changedir(cdir, todo) :
    if todo =='L':
        if cdir == 0:
            return 3
        else :
            return cdir-1
    if todo == 'D':
        if cdir ==3:
            return 0
        else : return cdir+1

dx = [1,0,-1,0] # 우 하 좌 상
dy = [0, 1,0,-1]
N = int(input())
map_arr = [[[0, -1] for _ in range(N+1)] for _ in range(N+1)]
K = int(input())

for _ in range(K):
    y, x = list(map(int, input().split(' ')))
    map_arr[y][x][0]=2

L = int(input())
hx = hy = tx = ty = 1 # 꼬리 t , 머리h
count=0
dir =0
flag =0
map_arr[hy][hx][0]=1
map_arr[hy][hx][1]=0
list_x=[]
list_c=[]
for _ in range(L):
    X, C = list(input().split(' '))
    X= int(X)
    list_x.append(X)
    list_c.append(C)

index =0
while True:

    hx=hx+dx[dir]
    hy=hy+dy[dir]
    if hx<=0 or hy<=0 or hx>N or hy>N :
        break
    if map_arr[hy][hx][0]==1: # 자기에 부딪힘
        break
    if map_arr[hy][hx][0] == 0:  # 사과 없음 크기 줄인다.
        map_arr[ty][tx][0] = 0
        tdir = map_arr[ty][tx][1]
        ty = ty + dy[tdir]
        tx = tx + dx[tdir]
    map_arr[hy][hx][0]=1 # 몸 표시
    map_arr[hy][hx][1]=dir

    count+=1
    if index<L and count== list_x[index]:
        dir= changedir(dir, list_c[index])
        map_arr[hy][hx][1]=dir
        index+=1

print(count+1)
