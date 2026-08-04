#include <iostream>
#include <algorithm>
using namespace std;


int main() {
	int T;
	//ют╥б
	cin >> T;
	for (int i = 1; i <= T; i++) {
		int  P, Q, R, S, W;
		cin >> P >> Q >> R >> S >> W;
		int A, B;
		A = P * W;
		if (W <= R) B = Q;
		else B = S * (W - R) + Q;
		cout << '#' << test_case << ' ' << min(A, B) << '\n';
	}

	return 0;
}