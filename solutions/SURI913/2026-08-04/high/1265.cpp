#include <iostream>
using namespace std;

int N, P;
long long result;

int main()
{
	int T;
	cin >> T;
	for (int test_case = 1; test_case <= T; test_case++) {
		cin >> N >> P; // 달란트, 묶음

		//균등하게 분배해야지 값이 큼
		//O(P)
		//거듭제곱 방식이면 O(log P)인데 이문제에는 필요없을 듯?
		//P가 크면 거듭제곱 써야함
		int cnt = N / P;
		int r = N % P;
		result = 1;
		for (int i = 0; i < P; i++) {
			int num = cnt;
			if (r > 0) {
				num++;
				r--;
			}
			result *= num;
		}

		cout << '#' << test_case << ' ' << result << endl;
	}
}