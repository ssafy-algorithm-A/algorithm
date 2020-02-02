def sol(y, x, count):

    global res
    if x >=10 :
        sol(y+1,0,count)
        return
    if y>=10 :
        res=min(count, res)
        return

    if map_arr[y][x]==0 :
        sol(y,x+1,count)
        return

    for size in range(5,0,-1):
    #    print(y+size, x+size)
        if checklist[size] <= 0 or y+size>10 or x+size>10 :
            continue

        flag =0
        for i in range(size):
            for j in range(size):
                if map_arr[y+i][x+j] ==0 :
                    flag =1
                    break
            if flag==1: break
        if flag==1 : continue

        for i in range(size):
            for j in range(size):
                map_arr[y+i][x+j]=0
        checklist[size]=checklist[size]-1
        sol(y, x+size, count+1)
        for i in range(size):
            for j in range(size):
                map_arr[y + i][x + j] = 1
        checklist[size] = checklist[size]+1
        '''flag = 0
        erase_list = []

        for i in range(y, y+size):
            for j in range(x, x+size):
                erase_list.append([i,j])
                if map_arr[i][j] == 0 :
                    flag = 1
                    break;
            if flag ==1 : break;
        if flag==0 :
            print(erase_list)
            checklist[size]-=1
            for erase in erase_list:
                print(erase[0],erase[1])
                map_arr[erase[0]][erase[1]] =0
            sol(y, x+1, count+1)
            for erase in erase_list:
                map_arr[erase[0]][erase[1]] =1
            checklist[size]+=1
            '''

map_arr= [list(map(int, input().split(' '))) for _ in range(10)]
checklist = [0,5,5,5,5,5]

res = 10000000

sol(0,0,0)
if res == 10000000:
    print(-1)
else :print(res)