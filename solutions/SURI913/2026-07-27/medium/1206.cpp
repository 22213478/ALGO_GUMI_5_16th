#include<iostream>
#include<vector>
#include <algorithm>
using namespace std;

int main() {
	int T = 10;
	int num;
	for (int test_case = 1; test_case <= T; test_case++) {
		int N, input, sum = 0;
		cin >> N;
		vector<int> buliding;
		for (int i = 0; i < N; i++) {
			cin >> input;
			buliding.push_back(input);
		}
		
		for (int i = 2; i < N-2; i++) { //앞뒤로 2채씩 무조건 건물이 없어서
			int maxBuliding = max(max(buliding[i - 2], buliding[i - 1]), max(buliding[i + 2], buliding[i + 1]));
			if (buliding[i] > maxBuliding) sum += buliding[i] - maxBuliding;
		}
		cout << '#' << test_case << ' ' << sum << '\n';
	}
	
	return 0;
}