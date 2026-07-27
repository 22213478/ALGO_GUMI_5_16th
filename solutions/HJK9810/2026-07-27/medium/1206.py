def algorithm():
    N = int(input())
    buildings = list(map(int, input().split()))
    count = 0

    for index in range(2, N - 2):
        max_height = max(buildings[index -2], buildings[index - 1], buildings[index + 1], buildings[index + 2])
        if buildings[index] > max_height:
            count += (buildings[index] - max_height)

    return count


# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, 11):
    print(f"#{test_case} ${algorithm()}")