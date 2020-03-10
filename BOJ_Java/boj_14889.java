package BOJ;
/**
 * 메모리 : 36020	KB
 * 시간 : 256 ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 1 << N; i++) {
            if (Integer.bitCount(i) == N/2) {

                int idx1 = 0, idx2 = 0;
                int[] p1 = new int[N/2];
                int[] p2 = new int[N/2];
                int sum1 = 0, sum2 = 0;

                for (int j = 0; j < N; j++) {
                    if((i & (1 << j)) > 0)
                        p1[idx1++] = j;
                    else
                        p2[idx2++] = j;
                }

                for (int item1 : p1)
                    for(int item2 : p1)
                        sum1 += map[item1][item2];

                for (int item1 : p2)
                    for(int item2 : p2)
                        sum2 += map[item1][item2];

                int gap = sum1 - sum2;
                if (gap < 0) gap = -gap;
                if (gap < min) min = gap;
            }
        }

        System.out.println(min);
    }
}
