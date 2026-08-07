# max(각 행의 합, 각 열의 합, 각 대각선의 합)
T = 10

for test_case in range(T):
    test_num = int(input())
    arr = [list(map(int, input().split())) for _ in range(100)]
    row_sum = [0 for _ in range(100)]
    col_sum = [0 for _ in range(100)]
    x_sum_LR = 0
    x_sum_RL = 0

    row_sum = [sum(row) for row in arr]
    col_sum = [sum(col) for col in zip(*arr)] #zip()으로 transpose해서 풀기
    diag_LR = sum(row[i] for i, row in enumerate(arr))
    diag_RL = sum(row[99-i] for i, row in enumerate(arr))

    f_result = max(max(row_sum), max(col_sum),diag_LR, diag_RL)
    print(f'#{test_num} {f_result}')