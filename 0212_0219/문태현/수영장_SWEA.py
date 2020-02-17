def dfs(month, credit):
    global min_credit
    # Month에서 1일권 사용. 1달권 사용. 3달권 사용 3개의 재귀함수. month 가 12 월달까지 끝이면 credit min 계싼
    # 이번달 1일권
    if month >= 12:
        min_credit = min(min_credit, credit)
        return
    day = schedule[month]
    dfs(month + 1, credit + day * ticket[0])
    # 이번달 1달권
    dfs(month + 1, credit + ticket[1])

    # 이번달부터 3달
    dfs(month + 3, credit + ticket[2])


T = int(input())
min_credit = 1000000
for t in range(T):
    ticket = list(map(int, input().split(' ')))  # 1일, 1달, 3달, 1년
    schedule = list(map(int, input().split(' ')))
    min_credit = 1000000
    dfs(0, 0)
    min_credit = min(min_credit, ticket[3])
    print('#' + str(t + 1) + ' ' + str(min_credit))