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
            for (int i=0; i<100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0; j<100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int x = 99;
            int y = -1;
            for (int i=0; i<100; i++) {
                if (arr[99][i] == 2) {
                    y = i;
                    break;
                }
            }

            //수정전: char lr = 0;
            //기존에는 lr을 'c', 'r', 0으로해서 좌, 우, 위를 표현했음. 위의 방식 보다는 아래의 dir로 방향을 표시

            //-1:좌, 1:우, 0:위
            int dir = 0;

            while(x != 0) {

                //if 조건문에 y - 1 >= 0 대신 y > 0 사용
                if (dir != 1 && y > 0 && arr[x][y - 1] == 1) {
                    y--;
                    dir = -1;
                }

                else if (dir != -1 && y < 99 && arr[x][y + 1] == 1) {
                    y++;
                    dir = 1;
                }

                //수정전: else if (x - 1 >= 0 && arr[x - 1][y] == 1) {
                //좌, 우 이동이 안되면 무조건 위로 이동이므로 else if 구문은 의미 없음

                else {
                    x--;
                    dir = 0;
                }
            }

            sb.append(y).append("\n");
        }

        System.out.println(sb);
    }
}