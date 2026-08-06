memo = []
for i in range(100):
    t = []
    for j in range(100):
        t.append(0)
    memo.append(t)
memo[0][0] = 1

def solver(N, P):
    if P == 1:
        return N
	
    if memo[N - 1][P - 1] != 0:
        return memo[N - 1][P - 1]
    
    m = 0
    for n in range(1, N // 2 + 1):
        for d in range(1, P):
            if (d > n or P - d > N - n):
                continue
            
            r = solver(n, d) * solver(N - n,  P - d)
            if r > m:
                m = r
    memo[N - 1][P - 1] = m
    return m


T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # ///////////////////////////////////////////////////////////////////////////////////
    '''

        이 부분에 여러분의 알고리즘 구현이 들어갑니다.

    '''
    # ///////////////////////////////////////////////////////////////////////////////////
    N, P = map(int, input().split())
    ret = solver(N, P)
    print('#' + str(test_case) + ' ' + str(ret))