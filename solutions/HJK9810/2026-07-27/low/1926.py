N = int(input())
result = []

for num in range(1, N + 1):
    str_num = str(num)
    count_slash = len([num for num in str_num if num == '3' or num == '6' or num == '9'])

    if count_slash == 0:
        print(num, end=" ")
    else:
        print("-" * count_slash, end=" ")
