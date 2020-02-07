def move():
    # gun에 있는 것 모두 이동 -> gun에 y,x 변경 map 도 변경?????
    # map에 gun 넣다뺏다 하는게 맘에 들지 않음 . remove   는 너무 비효율?
    # map 의 용도 -> merge 하기 위해서 그러면 이동할때 맵에 채워 넣고 끝나면 clear하면 됨
    # 빨간 구역에 간 것 고려 ->

    #move할때 map 새로 만들거니까 초기화 (vs map에 remove , append 쓰기 뭐가 더 좋을진 ? 의문)
    for k in range(K):
        map_arr[gun[k][0]][gun[k][1]].clear()

    for k in range(K):
        if gun[k][4] ==0: continue # 살아있는 것만
        y =gun[k][0]
        x =gun[k][1]
        num= gun[k][2]
        dir=gun[k][3]

        ny = y + dy[dir]
        nx = x + dx[dir]
        if ny ==0 or ny == N-1 or nx ==0 or nx ==N-1 : # 빨간 영역으로 갔을 때
            num = int(num/2)
            dir = nextdir[dir]
        # num=0 이면 dead
        if num ==0 : gun[k][4]=0
        # 바뀐 내용 다시 저장
        gun[k][0]=ny
        gun[k][1]=nx
        gun[k][2]=num
        gun[k][3] =dir
        #map_arr 만들기
        map_arr[ny][nx].append(k)

def merge() :
    flag =0
    for k in range(K):
        if flag ==1 : break
        y = gun[k][0]
        x = gun[k][1]
        if len(map_arr[y][x])>1 :
            length = len(map_arr[y][x])
            max_index =-1;
            max_num = -1;
            sum_res=0
            for i in range(length):
                index = map_arr[y][x][i]
                gun[index][4]=0
                sum_res+=gun[index][2]
                if(max_num<gun[index][2]):
                    max_index = index
                    max_num = gun[index][2]
            for i in range(length):
                idx=map_arr[y][x][i]
                if idx==max_index :
                    gun[idx][4] = 1
                    gun[idx][2]= sum_res
                    map_arr[gun[idx][0]][gun[idx][1]].clear()
                    map_arr[gun[idx][0]][gun[idx][1]].append(idx)
                    break;
                    flag =1

# def merge()
dx = [0,0,0,-1,1]
dy = [0,-1,1,0,0]
nextdir = [0, 2, 1, 4, 3]

T = int(input())
for t in range(T) :
    N, M , K= list(map(int,input().split(' ')))
    gun = []  # y, x , num, dir
    map_arr = [[[] for _ in range(N)] for _ in range(N)] # 2차원 map

    for k in range(K) :
        temp =list(map(int, input().split(' ')))
        temp.append(1)# live
        gun.append(temp)


    for _ in range(M): # iteration
        move()
        merge()
    sum_f=0
    for k in range(K) :
        if gun[k][4]==1:
            sum_f+= gun[k][2]
    print('#'+str(t+1)+' '+str(sum_f))
