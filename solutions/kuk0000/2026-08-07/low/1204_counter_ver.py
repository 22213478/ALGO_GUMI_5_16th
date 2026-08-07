from collections import Counter
T = int(input())

for test_input in range(1,T+1):
  test_case = int(input())

  math_grade = list(map(int,input().split()))
  counter = Counter(math_grade)
  max_freq = max(counter.values())
  
  mode_grade = max(v for v, cnt in counter.items() if cnt == max_freq)

  
  print(f'#{test_case} {mode_grade}')