import java.io.*;
import java.util.*;

class Solution {
    //시간복잡도 : O(N)
    //실제 연산 : 최악의 경우 510N

//	public static void main(String[] args) throws Exception {
//		//System.setIn(new FileInputStream("input.txt"));
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//
//		for (int test_case=1; test_case<=10; test_case++) {
//			sb.append("#").append(test_case).append(" ");
//
//			int N = Integer.parseInt(br.readLine());
//			StringTokenizer st = new StringTokenizer(br.readLine());
//
//			int[][] arr = new int[255][N];
//			for (int c=0; c<N; c++) {
//				int h = Integer.parseInt(st.nextToken());
//				if (h == 0) {
//					for (int r=254; r>=0; r--) {
//						arr[r][c] = 0;
//					}
//				}
//				else {
//					for (int r=254; r>=0; r--) {
//						if (h != 0) {
//							arr[r][c] = 1;
//							h--;
//						}
//						else {
//							arr[r][c] = 0;
//						}
//					}
//				}
//			}
//
//			int count = 0;
//			for (int c=2; c<N-2; c++) {
//				for (int r=254; r>=0; r--) {
//					if (arr[r][c] == 0) {
//						break;
//					}
//
//					if (arr[r][c-2] == 0 && arr[r][c-1] == 0 && arr[r][c+1] == 0 && arr[r][c+2] == 0) {
//						count++;
//					}
//				}
//			}
//
//			sb.append(count).append("\n");
//		}
//
//		System.out.println(sb);
//	}

    //시간 복잡도 : O(N)
    //실제 연산 : 2N

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int test_case=1; test_case<=10; test_case++) {
            sb.append("#").append(test_case).append(" ");

            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[N];
            for (int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int count = 0;
            for (int i=2; i<N-2; i++) {
                int max = Math.max(Math.max(arr[i-2], arr[i-1]), Math.max(arr[i+1], arr[i+2]));

                if (max < arr[i]) {
                    count += arr[i] - max;
                }
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}