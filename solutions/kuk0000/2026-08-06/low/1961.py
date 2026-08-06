# import sys
# sys.stdin = open("input.txt", "r")

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.

for test_case in range(1, T+1):
    N = int(input())
    arr = [list(map(int, input().split())) 
                for _ in range(N)]
    rot90 = [[0] * N for _ in range(N)]
    rot180 = [[0] * N for _ in range(N)]
    rot270 = [[0] * N for _ in range(N)]

    for i in range(N):
        for j in range(N):
            rot90[i][j] = arr[N-1-j][i]
            rot180[i][j] = arr[N-1-i][N-1-j]
            rot270[i][j] = arr[j][N-1-i]
    
    print(f'#{test_case}')
    for i in range(N):
        print(''.join(map(str, rot90[i])), end=' ')
        print(''.join(map(str, rot180[i])), end=' ')
        print(''.join(map(str, rot270[i])))