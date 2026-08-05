import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{

		//System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();


		for(int test_case = 1; test_case <= 1; test_case++)
		{
		
			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
				 이 부분에 여러분의 알고리즘 구현이 들어갑니다.
			 */
			/////////////////////////////////////////////////////////////////////////////////////////////
			int N = T;
            for (int i = 1; i <= N; i++) {
            	int count = 0;
                int j = i;
                while (j > 0) {
                	if (j % 10 == 3 || j % 10 == 6 || j % 10 == 9) count++;
                    j /= 10;
                }
                if (count > 0) {
                	for (int c = 0; c < count; c++) System.out.print("-");
                    System.out.print(" ");
                }
                else {
                	if (i == N) System.out.println(i);
                    else System.out.print(Integer.toString(i) + " ");
                }
            }
            
		}
	}
}