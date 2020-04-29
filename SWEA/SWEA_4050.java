package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_4050_재관이의대량할인 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader("2\n" +
                "4\n" +
                "3 2 3 2\n" +
                "6\n" +
                "6 4 5 5 5 5\n"));

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            int sum = 0;

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum += arr[i];
            }

            Arrays.sort(arr);

            int tmpSum = 0;
            if (N%3 == 0) {
                for (int i = 0; i < N; i+=3) {
                    tmpSum += arr[i];
                }
            } else {
                for (int i = N%3; i < N; i+=3) {
                    tmpSum += arr[i];
                }
            }

//            System.out.println(Arrays.toString(arr));
            System.out.println("#" + tc + " " + (sum - tmpSum));
        }
    }
}
