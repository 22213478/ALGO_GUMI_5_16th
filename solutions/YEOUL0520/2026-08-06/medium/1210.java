import java.io.*;
import java.util.*;

public class Solution {

    static final int SIZE = 100;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        for (int tc = 0; tc < 10; tc++) {

            int testCase = Integer.parseInt(br.readLine());

            int[][] ladder = new int[SIZE][SIZE];

            int row = SIZE - 1;
            int col = 0;

            // 사다리 입력
            for (int i = 0; i < SIZE; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < SIZE; j++) {

                    ladder[i][j] = Integer.parseInt(st.nextToken());

                    // 마지막 행에서 도착점 2의 위치 찾기
                    if (i == SIZE - 1 && ladder[i][j] == 2) {
                        col = j;
                    }
                }
            }

            boolean[][] visited = new boolean[SIZE][SIZE];

            visited[row][col] = true;

            // 맨 위쪽 행에 도착할 때까지 이동
            while (row > 0) {
                if (col > 0
                        && ladder[row][col - 1] == 1
                        && !visited[row][col - 1]) {

                    col--;
                } else if (col < SIZE - 1
                        && ladder[row][col + 1] == 1
                        && !visited[row][col + 1]) {

                    col++;
                } else {
                    row--;
                }

                visited[row][col] = true;
            }
            System.out.println("#"+testCase+" "+col);
        }
    }
}