import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class BOJ_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader("1\n" +
                "10\n" +
                "20\n" +
                "15\n" +
                "25\n" +
                "10\n" +
                "20"));
        int N = Integer.parseInt(br.readLine());
        int[] floor = new int[301];
        int[] memo = new int[301];

        for (int i = 1; i <= N; i++) {
            floor[i] = Integer.parseInt(br.readLine());
        }

        memo[1] = floor[1];
        memo[2] = Math.max(memo[1] + floor[2], floor[2]);

        for (int i = 3; i <= N; i++)
                memo[i] = Math.max(floor[i] + memo[i - 2], floor[i] + floor[i - 1] + memo[i - 3]);

        System.out.println(memo[N]);
    }
}
