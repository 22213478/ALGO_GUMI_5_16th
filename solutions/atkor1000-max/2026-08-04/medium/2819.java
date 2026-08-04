import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.lang.Math;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    public static int[][] dirs = {
    	{0, -1},
    	{1, 0},
    	{0, 1},
    	{-1, 0}
    };
           
            
    public static int[][] board = new int[4][4];
    public static ArrayList<Integer> numbers = new ArrayList<>();
    public static void scour(int moves, int r, int c, int sofar) {
    	moves--;
        for (int i = 0; i < 4; i++) {
        	int new_r = r + dirs[i][0];
            int new_c = c + dirs[i][1];
            int tmp = sofar;
            if (0 <= new_r && new_r < 4 && 0 <= new_c && new_c < 4) {
                tmp += board[new_r][new_c] * (Math.pow(10, moves));
                if (moves == 0) {
                    if (numbers.contains(tmp)) {
                        continue;
                    }
                    
                    else numbers.add(tmp);
                    //System.out.println(tmp);
                }
                else {
                    scour(moves, new_r, new_c, tmp);
                }
            } else continue;
        }
    }
    
    
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        



		for(int test_case = 1; test_case <= T; test_case++)
		{
		
            numbers.clear();
            
            for (int i = 0; i < 4; i++) {
            	for (int j = 0; j < 4; j++) {
                	board[i][j] = sc.nextInt();
                }
            }
            
            for (int i = 0; i < 4; i++) {
            	for (int j = 0; j < 4; j++) {
                    int sofar = 0;
                    scour(7, i, j, sofar);
                }
            }
            
            System.out.println("#" + Integer.toString(test_case) + " " + Integer.toString(numbers.size()));
		}
	}
}