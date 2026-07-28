#include <iostream>
using namespace std;

int main()
{
	int T = 1;
	for (int test_case = 1; test_case <= T; test_case++) {
		int N;
		cin >> N;

		for (int i = 1; i <= N; i++) {
			int tmp = i;
			int clapCount = 0;

			// i의 각 자릿수를 확인
			while (tmp > 0) {
				int digit = tmp % 10;

				if (digit == 3 || digit == 6 || digit == 9) {
					clapCount++;
				}

				tmp /= 10;
			}

			if (clapCount) {
				for (int j = 0; j < clapCount; j++) cout << '-';
			}
			else cout << i;
			cout << ' ';

		}

	}
}