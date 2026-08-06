import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    public static int board[][] = new int[100][100];
    
    public static Boolean rung_on_left(int r, int c) {
    	if (c == 0) return false;
        if (board[r][c - 1] == 1) return true;
        else return false;
    }
    
    public static Boolean rung_on_right(int r, int c) {
    	if (c == 99) return false;
        if (board[r][c + 1] == 1) return true;
        else return false;
    }
    
    public static Boolean rod_found(int r, int c, int d) {
    	if (board[r - 1][c + d] == 1) return true;
        else return false;
    }
    
	public static void main(String args[]) throws Exception
	{
		//System.setIn(new FileInputStream(&quot;res/input.txt&quot;));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		//int T = sc.nextInt();
		//T=sc.nextInt();
	
		while(true)
		{
		
			int q = sc.nextInt();
            for (int i = 0; i < 100; i++) {
            	for (int j = 0; j < 100; j++) {
                	board[i][j] = sc.nextInt();
                }
            }
            
            int r = 99;
            int c = -1;
            for (int k = 0; k < 100; k++) {
            	if (board[99][k] == 2) c = k; 
            }
            assert(c >= 0);
            
            Boolean from_rung = false;
            int rung_dir = 0;
            while (r > 0) {
                assert(board[r][c] != 0);
                if (rung_dir == 0) {
                    if (rung_on_left(r, c) && !from_rung) {
                        rung_dir = -1;
                        c += rung_dir;
                        continue;
                    }
                    else if (rung_on_right(r, c) && !from_rung) {
                        rung_dir = 1;
                        c += rung_dir;
                        continue;
                    }
                    else {
                        r -= 1;
                        from_rung = false;
                    }
                }
                else {
                	if (rod_found(r, c, rung_dir)) {
                    	c += rung_dir;
                        rung_dir = 0;
                        from_rung = true;
                    }
                    else c += rung_dir;
                }
                
            }
            
            System.out.println("#" + Integer.toString(q) + " " + Integer.toString(c));
            if (q == 10) break; 
		}
	}
}