#include<iostream>
#include<string>
#include <unordered_set>
#include <algorithm>
#define MAX 10
using namespace std;


string number;
int changeCount;
int answer;

//visited[count]에는 count번 교환했을 때 만들어진 숫자를 저장
unordered_set<string> visited[11];

void dfs(int count) {
	//정해진 횟수 다 사용
	if (count >= changeCount) {
		//선반 높이 이상이면 갱신(최소값)
		answer = max(answer, stoi(number));
		return;
	}

	int size = number.size();

	//모두 서로 다른 두 위치를 선택
	for (int i = 0; i < size - 1; i++) {
		for (int j = i + 1; j < size; j++) {
			//숫자판 교환
			swap(number[i], number[j]);

			//같은 교환횟수에서 이미 확인한 숫자인지 검사
			if (visited[count + 1].find(number) == visited[count + 1].end()) {
				
				visited[count + 1].insert(number);
				dfs(count + 1);
			}

			//다시 복구 => 백트랙킹
			swap(number[i], number[j]);
		}
	}

}

int main() {
	int T;
	cin >> T;
	for (int test_case = 1; test_case <= T; test_case++) {
		cin >> number >> changeCount;

		answer = 0;

		//이전테스트 방문기록 제거
		for (int i = 0; i < MAX; i++) {
			visited[i].clear();
		}

		visited[0].insert(number);
		dfs(0);

		cout << "#" << test_case << " " << answer  << "\n";
	}

	return 0;
}