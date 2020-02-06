import java.util.Scanner;

public class BOJ_9095_123더하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int TC = sc.nextInt();


        for (int tc = 1; tc <= TC; tc++) {
            int num = sc.nextInt();
            int[] dp = new int[11];

            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;

            for (int i = 4; i <= num; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }

            System.out.println(dp[num]);
        }

    }
}
