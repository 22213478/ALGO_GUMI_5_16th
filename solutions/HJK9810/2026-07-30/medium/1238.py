from collections import deque

DEFAULT_NODES = 100

def algorithm():
    SIZE, START = map(int, input().split())
    datas = list(map(int, input().split()))
    graph = [[] for _ in range(DEFAULT_NODES + 1)]
    for index in range(0, SIZE, 2):
        start, end = datas[index], datas[index + 1]
        graph[start].append(end)

    queue = deque()
    depthes = [-1] * (DEFAULT_NODES + 1)

    queue.append((START, 0))
    depthes[START] = 0
    max_depth = 0

    while queue:
        current, depth = queue.popleft()
        for next_node in graph[current]:
            if depthes[next_node] == -1:
                depthes[next_node] = depth + 1
                queue.append((next_node, depth + 1))
                if depthes[next_node] > max_depth:
                    max_depth = depthes[next_node]

    for idx in range(DEFAULT_NODES, 0, -1):
        if depthes[idx] == -1: continue
        if depthes[idx] == max_depth:
            return idx

    return START

# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, 11):
    print(f"#{test_case} {algorithm()}")
