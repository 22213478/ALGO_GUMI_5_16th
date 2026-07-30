def algorithm():
    V, E = map(int, input().split())
    edges = list(map(int, input().split()))
    start = [True] + [True] * V
    graph = {}
    for idx in range(0, E * 2, 2):
        graph[edges[idx]] = edges[idx + 1]
        start[edges[idx + 1]] = True


# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, 11):
    print(f"{test_case} {algorithm()}")
