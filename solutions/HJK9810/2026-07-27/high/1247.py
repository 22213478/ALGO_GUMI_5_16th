def calc_dist(first, second):
    x1, y1 = first
    x2, y2 = second
    return abs(x1 - x2) + abs(y1 - y2)

def find_dist(pos, before_idx, count, visited, distance, min_dist):
    if count == len(pos):
        return before_idx, distance if min_dist > distance else min_dist

    for idx in range(len(pos)):
        if visited[idx]: continue

        visited[idx] = True
        find_dist(pos, idx, count + 1, visited, distance + calc_dist(pos[before_idx], pos[idx]), min_dist)
        visited[idx] = False

    return -1, min_dist

def algorithm():
    SIZE = int(input())
    input_post = list(map(int, input().split()))

    cus_pos = []
    office_pos = input_post[:2]
    home_pos = input_post[2:4]
    for idx in range(2, SIZE + 2):
        cus_pos.append(input_post[idx * 2:idx * 2 + 2])

    dist = float('inf')

    for idx in range(SIZE):
        s_x, s_y = office_pos
        f_x, f_y = cus_pos[idx]
        visited = [False] * SIZE
        visited[idx] = True

        last_idx, distance = find_dist(cus_pos, idx, 1, visited, abs(f_x - s_x) + abs(f_y - s_y), dist)

        if last_idx == -1: continue
        e_x, e_y = cus_pos[last_idx]
        h_x, h_y = home_pos

        dist = min(dist, abs(e_x - h_x) + abs(e_y - h_y) + distance)

    return dist

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    print(f"#{test_case} {algorithm()}")
