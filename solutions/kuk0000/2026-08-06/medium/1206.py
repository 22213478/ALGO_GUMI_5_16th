# import sys
# sys.stdin = open("input.txt", "r")
T=10

for test_case in range(1, T+1):
    N = int(input()) # 건물 개수

    heights = list(map(int, input().split())) # 건물 높이
    # 조망권 확보 세대 수
    count = 0
    for i in range(2, N-2):
        # 현재 건물의 높이가 두 건물보다 높은 경우
        if heights[i] > heights[i-2] and heights[i] > heights[i+2] and heights[i] > heights[i-1] and heights[i] > heights[i+1]:
            count += min(heights[i] - heights[i-2], heights[i] - heights[i+2], heights[i] - heights[i-1],heights[i] - heights[i+1])
    print(f'#{test_case} {count}')