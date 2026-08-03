#include <iostream>
#include <vector>
using namespace std;

//그리디- 뒤에서 부터
int main()
{
	int T;
	cin >> T;
	for (int test_case = 1; test_case <= T; test_case++) {
		int N;
		long long maxValue = 0;
		long long result = 0;
		cin >> N;

		vector<int> array;
		array.resize(N);
		for (int i = 0; i < N; i++) cin >> array[i];

		for (int i= N-1; i >= 0; i--) {
			//가장 큰수 찾음
			if (array[i] > maxValue) maxValue = array[i];
			else
			{
				// 현재 가격에 구매해서 maxPrice에 판매
				result += maxValue - array[i];
			}
		}



		cout << '#' << test_case << ' ' << result <<endl;
	}
}