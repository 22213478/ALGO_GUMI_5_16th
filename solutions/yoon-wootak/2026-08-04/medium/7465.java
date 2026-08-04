import java.io.*;
import java.util.*;

class Solution {
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case=1; test_case<=T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			parent = new int[N + 1];
			
			for (int i=1; i<=N; i++) {
				parent[i] = i;
			}
			
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
			Set<Integer> hs = new HashSet<>();
			for (int i=1; i<=N; i++) {
				hs.add(find(i));
			}	
		
			sb.append(hs.size()).append("\n");
		}
		
		System.out.println(sb);
	}
	
	//부모를 찾는 함수
	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	//부모-자식 관계 추가 함수
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA != rootB) {
			parent[rootB] = rootA;
		}
	}
}