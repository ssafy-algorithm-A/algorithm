def rotate(x,d,k) :
    if d == 0:
        for i in range(x-1,N,x):
            for _ in range(k):
                onepan[i].insert(0,onepan[i].pop())
    else :
        for i in range(x - 1, N, x):
            for _ in range(k):
                onepan[i].append(onepan[i].pop(0))

def plus_minus():
    sum =0
    count =0
    for i in range(N):
        for j in range(M):
            if onepan[i][j]!=0:
                sum+=onepan[i][j]
                count+=1
    if (count==0) : return
    avg = sum/count

    for i in range(N):
        for j in range(M):
            if onepan[i][j]!=0 and onepan[i][j]>avg:
                onepan[i][j] -=1
            elif onepan[i][j]!=0 and onepan[i][j]<avg:
                onepan[i][j] +=1

def remove_same_num():

    remove_list=[]
    for i in range(N):
        for j in range(M):
            if onepan[i][j]==0: continue
            target=onepan[i][j]

            for k in range(4):
                nx = j + dx[k]
                ny = i+dy[k]
                if ny ==-1 or ny ==N : continue
                if nx == -1 : nx=M-1
                if nx == M : nx=0
                if target ==onepan[ny][nx]:
                    remove_list.append([i,j])
                    break;

    if remove_list:
        for list in remove_list:
            onepan[list[0]][list[1]]=0
    else :
        plus_minus()




dx =[0,0,-1,1]
dy =[-1,1,0,0]
N, M, T = map(int, input().split(' '))
onepan = [list(map(int, input().split(' '))) for _ in range(N)]


for _ in range(T) :
    x, d, k = map(int, input().split(' '))
    rotate(x,d,k)
    remove_same_num()

sum=0
for i in range(N):
    for j in range(M):
        sum+=onepan[i][j]
print(sum)
