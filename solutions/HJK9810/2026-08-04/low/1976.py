T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    h1, m1, h2, m2 = map(int, input().split())
    minute_sum = m1 + m2
    hour_sum = h1 + h2

    if minute_sum >= 60:
        minute_sum -= 60
        hour_sum += 1

    if hour_sum > 12:
        hour_sum -= 12
    
    print(f"#{test_case} {hour_sum} {minute_sum}")
