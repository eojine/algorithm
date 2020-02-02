import java.util.Arrays;
import java.util.Scanner;

public class BOJ_14502 {

    static int[][] arr;
    static int N;
    static int M;
    static int virusTop = -1;
    static Info[] virusSt = new Info[70];
    static int[] dn = {-1, 0, 1, 0};
    static int[] dm = {0, -1, 0, 1};
    static int max = 0;

    public static class Info {
        int n, m;

        public Info(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        wall(0);

        System.out.println(max);
    }

    public static void spreadVirus() {

        int[][] tmp = new int[N][M];
        int zeroCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = arr[i][j];

                if (tmp[i][j] == 2)
                    virusSt[++virusTop] = new Info(i, j);
            }
        }

        while (virusTop > -1) {
            int firstN = virusSt[virusTop].n;
            int firstM = virusSt[virusTop--].m;

            for (int i = 0; i < 4; i++) {
                int nextN = firstN + dn[i];
                int nextM = firstM + dm[i];

                if (nextN < 0 || nextM < 0 || nextN >= N || nextM >= M) {
                    continue;
                }

                if (tmp[nextN][nextM] == 0) {
                    tmp[nextN][nextM] = 2;
                    virusSt[++virusTop] = new Info(nextN, nextM);
                }

            } // end of for (spread virus)
        } // end of while (spread virus)

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmp[i][j] == 0) {
                    zeroCnt++;
                }
            }
        }

        if (max < zeroCnt) {
            max = zeroCnt;
        }
    }

    public static void wall(int cnt) {
        if (cnt == 3) {
            spreadVirus();
            return;
        }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                    wall(cnt + 1);
                    arr[i][j] = 0;
                }
    }
}
