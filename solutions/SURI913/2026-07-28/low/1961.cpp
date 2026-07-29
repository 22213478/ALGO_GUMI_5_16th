#include <iostream>
#include <stdio.h>

#define MAX 8
using namespace std;

int arr[MAX][MAX];
int N;
int main()
{
	int T;
	cin >> T;
	for (int test_case = 1; test_case <= T; test_case++) {
		cin >> N;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cin >> arr[i][j];
			}
		}

		cout << '#' << test_case << endl;

		for (int i = 0; i < N; i++) {
			//90도 회전
			for (int j = 0; j < N; j++) {
				cout << arr[(N -1)- j][i];
			}
			cout << ' ';
			//180도
			for (int j = 0; j < N; j++) {
				cout << arr[(N - 1) - i][(N - 1) -j];
			}
			cout << ' ';
			//270도
			for (int j = 0; j < N; j++) {
				cout << arr[j][(N - 1) - i];
			}
			cout << endl;
		}

		
	}
}
