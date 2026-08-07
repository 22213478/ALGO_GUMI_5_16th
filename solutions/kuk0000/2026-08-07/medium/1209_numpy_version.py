# max(각 행의 합, 각 열의 합, 각 대각선의 합)
import numpy as np
T = 10

for test_case in range(T):
    test_num = int(input())
    
    arr = np.array([list(map(int,input().split())) for _ in range(100)])

    row_sum = arr.sum(axis=1) # 각 행의 합 : axis=1
    col_sum = arr.sum(axis=0) # 각 열의 합 : axis=0

    #대각선
    diag_LR = np.trace(arr) # 왼쪽위->오른쪽 아래 원소들의 합 : 주대각선 합
    diag_RL = np.trace(np.fliplr(arr)) # 배열을 좌우로 뒤집은 후 대각선 합

    f_result = max(row_sum.max(), col_sum.max(), diag_LR, diag_RL)
    print(f'#{test_num} {f_result}')