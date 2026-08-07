# max(각 행의 합, 각 열의 합, 각 대각선의 합)
T = 10

for test_case in range(T):
    test_num = int(input())
    arr = [list(map(int, input().split())) for _ in range(100)]
    row_sum = [0 for _ in range(100)]
    col_sum = [0 for _ in range(100)]
    x_sum_LR = 0
    x_sum_RL = 0

    for i in range(100):
        for j in range(100):
            row_sum[i] += arr[i][j]
            col_sum[j] += arr[i][j]
    for x in range(100):
        x_sum_LR += arr[x][x]
        x_sum_RL += arr[x][99-x]

    f_result = max(max(row_sum), max(col_sum),x_sum_LR, x_sum_RL)
    print(f'#{test_num} {f_result}')