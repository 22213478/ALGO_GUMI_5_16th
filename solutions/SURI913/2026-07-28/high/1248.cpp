#include<iostream>
#include <vector>

using namespace std;
vector<vector<int>> children;

int dfs(int commonParent) {

	int count = 1;
	for (int child : children[commonParent]) {
		count += dfs(child);
	}

	return count;
}


int main() {
	int T;
	cin >> T;
	for (int test_case = 1; test_case <= T; test_case++) {
		int V, E, nodeValue_1, nodeValue_2;

		cin >> V >> E >> nodeValue_1 >> nodeValue_2; //정점, 간선, 공통 부모 찾을 노드값 두개
		
		vector<int> parent(V + 1, 0); //자식 이 부모 찾는 용
		children = vector<vector<int>>(V + 1); //부모가 자식 찾는 용

		for (int i = 0; i < E; i++) {
			int parentValue, childValue;
			cin >> parentValue >> childValue;

			parent[childValue] = parentValue;
			children[parentValue].push_back(childValue);
		}

		// nodeValue_1의 부모를 타고 올라가서 체크

		vector<bool> visited(V + 1, false);
		int current = nodeValue_1;

		while (current != 0) {
			visited[current] = true;
			current = parent[current];
		}

		//nodeValue_1의 부모체크를 참고해서
		//nodeValue_2의 부모를 타고 올라감
		current = nodeValue_2;

		while (!visited[current]) {
			current = parent[current];
			//가장 가까운 부모 만나면 끝남
		}

		int commonParent = current; //공통부모 찾기 완

		//공통부모를 기준으로 서브트리 크기 계산 => 자신을 포함한 자식 노드 수
		int answer = dfs(commonParent);


		cout << "#" << test_case << " " << answer << "\n";
	}

	return 0;
}