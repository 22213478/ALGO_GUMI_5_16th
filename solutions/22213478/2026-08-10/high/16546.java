
/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.*;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution {
  public static void main(String args[]) throws Exception {
    /*
     * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
     * 여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
     * 이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
     * 따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
     * 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
     */
    // System.setIn(new FileInputStream("res/input.txt"));

    /*
     * 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
     */
    Scanner sc = new Scanner(System.in);
    int T;
    T = sc.nextInt();
    /*
     * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
     */

    for (int test_case = 1; test_case <= T; test_case++) {
      String line = sc.next();
      int[] num = new int[6];
      for (int i = 0; i < 6; i++) {
        num[i] = line.charAt(i) - '0';
      }

      int run_count1 = 0;
      int triplet_count1 = 0;
      int run_num = -1;
      int triplet_num = -1;
      int double_run_count1 = 0;
      int double_run_num = -1;
      Arrays.sort(num);
      int count = 0;
      int double_triplet_count1 = 0;
      int double_triplet_num = 0;
      boolean output = false;

      for (int i = 1; i < 3; i++) {
        if (num[i - 1] == num[i]) {
          if (triplet_num == -1) {
            triplet_num = num[i];
            triplet_count1++;
          }
          triplet_count1++;
        } else if (num[i - 1] + 1 == num[i]) {
          if (run_num == -1) {
            run_num = num[i];
            run_count1++;
          }
          run_count1++;
        }
        // } else if (i < 5 && (num[i - 1] + 1 == num[i + 1])) {
        // if (double_run_num == -1) {
        // double_run_num = num[i];
        // double_run_count1++;

        // }
        // double_run_count1++;
        // }
      }

      int cnt = 0;
      if (run_count1 >= 3)
        cnt++;

      if (triplet_count1 >= 3)
        cnt++;

      if (double_run_count1 >= 3)
        cnt++;

      // System.out.println("1트");
      // System.out.println(triplet_count1);

      // System.out.println(run_count1);
      // System.out.println(double_run_count1);

      run_count1 = 0;
      triplet_count1 = 0;
      run_num = -1;
      triplet_num = -1;
      double_run_count1 = 0;
      double_run_num = -1;

      for (int i = 4; i < 6; i++) {
        if (num[i - 1] == num[i]) {
          if (triplet_num == -1) {
            triplet_num = num[i];
            triplet_count1++;
          }
          triplet_count1++;
        } else if (num[i - 1] + 1 == num[i]) {
          if (run_num == -1) {
            run_num = num[i];
            run_count1++;
          }
          run_count1++;
          // } else if (i < 5 && (num[i - 1] + 1 == num[i + 1])) {
          // if (double_run_num == -1) {
          // double_run_num = num[i];
          // double_run_count1++;

          // }
          // double_run_count1++;
          // }
        }
      }
      if (run_count1 >= 3)
        cnt++;

      if (triplet_count1 >= 3)
        cnt++;

      if (double_run_count1 >= 3)
        cnt++;

      // System.out.println("2트");
      // System.out.println(triplet_count1);

      // System.out.println(run_count1);
      // System.out.println(double_run_count1);

      run_count1 = 0;
      triplet_count1 = 0;
      run_num = -1;
      triplet_num = -1;
      double_run_count1 = 0;
      double_run_num = -1;
      if (cnt == 0) {
        for (int i = 1; i < 6; i++) {
          if (i < 5 && (num[i - 1] == num[i + 1])) {
            // System.out.println("double: "+double_run_count1);
            if (double_triplet_num == -1) {
              double_triplet_num = num[i];
              // double_triplet_count1++;

            }
            double_triplet_count1++;
          } else if (i < 5 && (num[i - 1] + 1 == num[i + 1])) {
            if (double_run_num == -1) {
              double_run_num = num[i];
              // double_run_count1++;
              // System.out.println(double_run_count1 + ": "+num[i-1]+" " + num[i+1]);

            }
            double_run_count1++;
            // System.out.println(double_run_count1 + ": "+num[i-1]+" " + num[i+1]);
          }
        }

      }

      if (double_run_count1 >= 3)
        cnt += 2;
      if (double_triplet_count1 >= 3)
        cnt += 2;
      if (cnt >= 2)
        output = true;
      // System.out.println("double");

      // System.out.println(double_run_count1);
      // System.out.println(double_triplet_count1);

      System.out.println("#" + test_case + " " + output);

      /////////////////////////////////////////////////////////////////////////////////////////////
      /*
       * 이 부분에 여러분의 알고리즘 구현이 들어갑니다.
       */
      /////////////////////////////////////////////////////////////////////////////////////////////

    }
  }
}