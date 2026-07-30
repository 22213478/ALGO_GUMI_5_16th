from collections import deque

def algorithm():
    V, E = map(int, input().split())
    edges = list(map(int, input().split()))
    indegree = [0] * (V + 1)
    graph = {}
    for idx in range(0, E * 2, 2):
        key, value = edges[idx:idx + 2]
        if key not in graph:
            graph[key] = [value]
        else:
            graph[key].append(value)
        indegree[value] += 1

    result = []
    queue = deque()
    start = [node for node in range(1, V + 1) if indegree[node] == 0][0]

    queue.append(start)
    result.append(start)
    while(queue):
        node = queue.popleft()

        for next_node in graph[node]:
            indegree[next_node] -= 1
            if indegree[next_node] == 0:
                queue.append(next_node)
                result.append(next_node)

    return " ".join(result)


# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, 11):
    print(f"{test_case} {algorithm()}")
