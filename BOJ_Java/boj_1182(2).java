package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class BOJ_1182_부분수열의합 {

    static int result, S, N, arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader("5 0\n" +
                "-7 -3 -2 5 8"));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        backTrack(0, 0);
        if (S == 0) result--;
        System.out.println(result);
    }

    private static void backTrack(int res, int idx) {
        if (idx == N) {
            if (res == S) result++;
            return;
        }

        backTrack(res, idx + 1);
        backTrack(res + arr[idx], idx + 1);
    }
}
