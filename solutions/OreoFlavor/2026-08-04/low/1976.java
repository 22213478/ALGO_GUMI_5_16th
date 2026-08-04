import java.util.Scanner;

public class Solution {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int testCase = sc.nextInt();
        int hour1, hour2;
        int min1, min2;
        for(int tc = 0; tc < testCase; tc++) {
            int rhour = 0;
            int rmin = 0;
            hour1 = sc.nextInt();
            min1 = sc.nextInt();
            hour2 = sc.nextInt();
            min2 = sc.nextInt();
            if((min1 + min2) >= 60){
                rhour++;
            }
            rmin = (min1 + min2) % 60;
            rhour += hour1 + hour2;
            rhour %= 12;
            if(rhour == 0)
                rhour = 12;

            System.out.println("#" + (tc+1) + " " + rhour + " " + rmin);
        }

    }
}