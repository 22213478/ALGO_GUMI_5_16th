import java.util.Scanner;

class Solution {

    static Scanner sc = new Scanner(System.in);
    
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    
    static int[] visited = new int[10000000]; //0~9999999(1000000~9999999)
    static int result = 0;    
    static int[][] field = new int[4][4];

    public static void main(String[] args) {
        int testCase;
        testCase = sc.nextInt();
        
        for(int tc = 1; tc <= testCase; tc++){
            
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    field[i][j] = sc.nextInt();
                }
            }

            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    dfs(i, j,  field[i][j], 1);
                }
            }

            System.out.println("#" + tc + " " + result);
            result = 0; //초기화
            visited = new int[10000000]; //초기화
        }
    }

    public static void dfs(int x, int y, int num, int cnt){
        if(cnt == 7){
            if(visited[num] == 0){
                visited[num] = 1;
                result++;
            }
        }
        else{
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4){
                    dfs(nx, ny, num*10 + field[nx][ny], cnt + 1);
                }
            }
        }
    }
}