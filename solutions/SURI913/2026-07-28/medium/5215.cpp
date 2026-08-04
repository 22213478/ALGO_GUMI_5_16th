#include <iostream>
#include <unordered_set>
#include <algorithm>
#define MAX 20
using namespace std;

int arr[MAX][MAX]; ///맛점수, 칼로리
int N, L, result;

//칼로리가 가장 낮으면서 맛이 좋은거
void dfs(int index, int sumN, int sumL) {
	if (sumL > L) {
		//지정한 칼로리보다 높으면 버림
		return;
	}
	if (index == N)
	{
		//조합 완료
		result = max(result, sumN);
		return;
	}
	//선택하는경우
	dfs(index + 1, sumN + arr[index][0], sumL + arr[index][1]);

	//선택하지 않는 경우
	dfs(index + 1, sumN, sumL);

}

int main()
{
	int T;
	cin >> T;
	for (int test_case = 1; test_case <= T; test_case++) {
		cin >> N >> L; // 재료의 수, 제한 칼로리

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2; j++) {
				cin >> arr[i][j];
			}
		}
		result = 0;
		dfs(0, 0, 0);


		cout << '#' << test_case << ' ' << result << endl;
	}
}