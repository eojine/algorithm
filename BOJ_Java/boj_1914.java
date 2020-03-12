import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * 하노이탑 2^n - 1 만큼 움직인다.
 * 하노이탑 재귀를 다 돌려서 TotalCnt를 얻기에는 너무 큰수.. 시간초과남!
 * 그래서 BigInteger에 pow함수를 써서 구현했다.
 */
 
public class BOJ_1914_하노이탑 {
    static StringBuilder sb = new StringBuilder();
    static int N, totalCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        BigInteger bi = new BigInteger("2");
        BigInteger totalCnt = bi.pow(N).subtract(BigInteger.ONE);
        System.out.println(totalCnt);
        if (N <= 20) {
            hanoi(N, 1, 2, 3);
            System.out.print(sb);
        }
    }

    private static void hanoi(int cnt, int from, int temp, int to) {
        if (cnt == 0) return;
        totalCnt++;
        hanoi(cnt - 1, from, to, temp);
        if (N <= 20) sb.append(from).append(" ").append(to).append("\n");
        hanoi(cnt - 1, temp, from, to);
        return;
    }
}
