def algorithm():
    N, K = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(N)]

    count = 0
    stack = 0

    for row in range(N):
        if board[row][0] == 1: stack += 1
        for col in range(1, N):
            if board[row][col] == 0:
                if stack == 0: continue
                elif stack == K:
                    count += 1
                    stack = 0
                else: stack = 0
            else:
                stack += 1
        if stack == K: count += 1
        stack = 0

    stack = 0
    for col in range(N):
        if board[0][col] == 1: stack += 1
        for row in range(1, N):
            if board[row][col] == 0:
                if stack == 0: continue
                elif stack == K: 
                    count += 1
                    stack = 0
                else: stack = 0
            else:
                stack += 1
        if stack == K: count += 1
        stack = 0

    return count

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    print(f"#{test_case} {algorithm()}")
