T = input(int())

for _ in range(1,T+1):
  test_case = int(input())
  math_grade = list(map(int, input().split()))

  count = [0] * (max(math_grade)+1)

  mode_grad=0
  freq=0
  for score in range(100, -1, -1):
    if count[score]>max_freq:
      max_freq = count[score]
      mode_grade=score
