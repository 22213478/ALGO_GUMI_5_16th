#include<iostream>
#include<algorithm>
#include <utility> // pair 사용을 위한 헤더
#include <cmath>

#define MAX 101
using namespace std;
int N, answer;

pair<int, int> home, company;
pair<int, int>arr[MAX];
bool visited[MAX];

int distance(pair<int, int> p1, pair<int, int>p2) {
	return abs(p1.first - p2.first) + abs(p1.second - p2.second);
}


void dfs(pair<int, int>pos, int cnt, int sum) {

	if (sum >= answer) { //크면 끝내
		return;
	}

	pair<int, int> current = pos;

	if (cnt == N) {
		sum += distance(pos, home);
		answer = min(answer, sum);
		return;
	}

	for (int i = 0; i < N; i++) {
		if (visited[i]) continue; //return 아니다 

		visited[i] = true; //백트래킹 +  dfs
		dfs(arr[i], cnt+1, sum+ distance(current, arr[i])); //sum 냅다 값 변경하지마라
		visited[i] = false;
	}

}

int main() {
	int T;
	cin >> T;
	for (int test_case = 1; test_case <= T; test_case++) {

		cin >> N; //고객의 수

		cin >> company.first >> company.second;
		cin >> home.first >> home.second;
		
		for (int i = 0; i < N; i++) {
			cin >> arr[i].first >> arr[i].second;
			visited[i] = false;
		}
		
		answer = 1e9;
		dfs(company, 0,0);

		cout << "#" << test_case << " " << answer<< "\n";
	}

	return 0;
}