def calc_dist(first, second):
    x1, y1 = first
    x2, y2 = second
    return abs(x1 - x2) + abs(y1 - y2)

def algorithm():
    SIZE = int(input())
    input_post = list(map(int, input().split()))

    cus_pos = []
    office_pos = input_post[:2]
    home_pos = input_post[2:4]
    for idx in range(2, SIZE + 2):
        cus_pos.append(input_post[idx * 2:idx * 2 + 2])

    dist = float('inf')
    visited = [False] * SIZE

    def find_dist(current_idx, count, now_dist):
        nonlocal dist

        if now_dist >= dist: return
        if count == SIZE:
            dist = min(dist, now_dist + calc_dist(cus_pos[current_idx], home_pos))
            return

        for idx in range(SIZE):
            if visited[idx]: continue

            visited[idx] = True
            find_dist(idx, count + 1, now_dist + calc_dist(cus_pos[current_idx], cus_pos[idx]))
            visited[idx] = False

    for idx in range(SIZE):
        visited[idx] = True
        find_dist(idx, 1, calc_dist(cus_pos[idx], office_pos))
        visited[idx] = False

    return dist

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    print(f"#{test_case} {algorithm()}")
