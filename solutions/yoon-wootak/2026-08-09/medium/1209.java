import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int test_case=1; test_case<=10; test_case++) {
            br.readLine();
            sb.append("#").append(test_case).append(" ");

            int[][] arr = new int[100][100];
            for (int r=0; r<100; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c=0; c<100; c++) {
                    arr[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            int sumR = 0;
            int sumC = 0;
            int sumD = 0; //왼쪽 위에서 시작하는 대각선
            int sumI = 0; //왼쪽 아래에서 시작하는 대각선
            int max = -1;

            for (int r=0; r<100; r++) {
                sumR = 0;
                sumC = 0;
                for (int c=0; c<100; c++) {
                    sumR += arr[r][c];
                    sumC += arr[c][r];

                    //방법1. if 없이 대각선 계산
                    //sumD += arr[r][r];
                    //sumI += arr[r][99 - r];

                    //방법2. if 사용 대각선 계산
                    if (r == c) {
                        sumD += arr[r][c];
                    }

                    if (r + c == 99) {
                        sumI += arr[r][c];
                    }
                    //
                }
                max = Math.max(max, sumR);
                max = Math.max(max, sumC);
            }

            max = Math.max(max, sumD);
            max = Math.max(max, sumI);

            sb.append(max).append("\n");
        }

        System.out.println(sb);
    }
}