DEFAULT = 4
DIR = ((0, -1), (0, 1), (1, 0), (-1, 0))

def algorithm():
    board = [list(input().split()) for _ in range(DEFAULT)]
    result = set()
    visited = [[False] * DEFAULT for _ in range(DEFAULT)]

    def search_board(row, col, depth, sentence):
        if depth == 6:
            result.add(sentence + board[row][col])
            return

        for (r, c) in DIR:
            n_row, n_col = row + r, col + c
            if 0 <= n_row < DEFAULT and 0 <= n_col < DEFAULT:
                visited[n_row][n_col] = True
                search_board(n_row, n_col, depth + 1, sentence + board[n_row][n_col])
                visited[n_row][n_col] = False

    for row in range(DEFAULT):
        for col in range(DEFAULT):
            visited = [[False] * DEFAULT for _ in range(DEFAULT)]
            search_board(row, col, 0, board[row][col])

    return len(result)

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    print(f"#{test_case} {algorithm()}")
