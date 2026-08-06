#include<iostream>
#include<stack>
using namespace std;
#define MAX 100
int ladder[MAX][MAX];

int main() {
	int T = 10, testNum;
	for (int test_case = 1; test_case <= T; test_case++) {
		cin >> testNum;
		int x, y;
		//입력
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				cin >> ladder[i][j];

				if (ladder[i][j] == 2) {
					y = i;
					x = j;
				}

			}
		}

		//위로 올라가며 탐색
		while (y>0)
		{

			// 왼쪽으로 갈 수 있으면 끝까지 이동
			if (x > 0 && ladder[y][x - 1] == 1) {
				while (x > 0 && ladder[y][x - 1] == 1) {
					x--;
				}
			}
			// 오른쪽으로 갈 수 있으면 끝까지 이동
			else if (x < MAX-1 && ladder[y][x + 1] == 1) {
				while (x < MAX - 1 && ladder[y][x + 1] == 1) {
					x++;
				}
			}
			// 좌우 없으면 위로 이동
			y--;

		}

		cout << '#' << testNum << ' ' << x << '\n';
	}

	return 0;
}