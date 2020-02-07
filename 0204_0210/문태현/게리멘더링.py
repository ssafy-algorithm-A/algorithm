import itertools
import copy
# 인접 리스트 이용

def make_list(pivot, other_list):
    li= []
    q =[]
    visited= [0]*(N+1)
    li.append(pivot)
    q.insert(0,pivot)
    visited[pivot]=1
    while(len(q)!=0):
        num = q.pop()

        for c in connect[num]:
            if c in other_list :continue;  # 단, 연결되는 부분이 다른쪽 선거구면 제외
            if visited[c]==1: continue;
            li.append(c)
            q.insert(0,c)
            visited[c] =1
    return li


def sol(c_list, otherc_list) :
    global min_res
    # 나눠진 것들이 모두 연결되어 있는 가?
    # 나눠진 구역이 연결되잇는지 판단하기 위해 make_list에서 bfs 탐색
    # 하여 1과 연결된 모든 부분(다른쪽 선거구 제외) 리스트 만듬
    check_list1 =make_list(c_list[0],otherc_list)
    for c in c_list :   # 연결된 부분에 포함 안된 구역이 있는가
        if c not in check_list1:
            return
    check_list2 = make_list(otherc_list[0],c_list) # 반대쪽 선거구도 마찬가지
    for c in otherc_list:
        if c not in check_list2:
            return
    sum1 =0
    sum2 =0
    for c in c_list:
        sum1+=arr[c]
    for c in otherc_list:
        sum2+=arr[c]
    res=abs(sum1-sum2)
    min_res = min(min_res, res)

N = int(input())
arr= [0]
arr.extend(list(map(int, input().split(' '))))
min_res = 100000
connect = [[]] #인접 리스트
for i in range(N):
    temp=list(map(int, input().split(' ')))
    del temp[0]
    connect.append(temp)
comb = []
for i in range(1,N+1):
    comb.append(i)


for i in range(1,int((N/2))+1) :
    c_list = list(itertools.combinations(comb, i))
    for c in c_list:
        otherc=copy.deepcopy(comb)
        for test in c:
            otherc.remove(test)
        # 가능한 구역의 구분 조합으로 생성.
        sol(list(c), otherc)

if min_res==100000 :
    print(-1)
else :
    print(min_res)
#c= itertools.combinations(comb, 2)
#print(list(c)[0][0])