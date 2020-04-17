import java.io.StringReader;
import java.util.Scanner;

public class boj_11562 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc = new Scanner(new StringReader("4 3\n" +
                "1 2 0\n" +
                "2 3 1\n" +
                "3 4 0\n" +
                "7\n" +
                "1 1\n" +
                "1 2\n" +
                "2 1\n" +
                "1 4\n" +
                "4 1\n" +
                "2 3\n" +
                "4 3"));

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] D = new int[N][N];

        // 스스로가 아닌 이상 Inf로 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) D[i][j] = 500;
            }
        }

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            int b = sc.nextInt();

            // b == 1 : 양방향, b == 0 : 단방향
            D[u][v] = 0;
            D[v][u] = b == 0? 1 : 0; // 단방향이면 뒤집어야함.
        }

        // 플로이드 워샬
        for (int k = 0; k < N; k++) { // 경유
            for (int i = 0; i < N; i++) { // 시작
                for (int j = 0; j < N; j++) { // 도착
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }

        int K = sc.nextInt();
        for (int tc = 0; tc < K; tc++) {
            int start = sc.nextInt() - 1;
            int end = sc.nextInt() - 1;

            System.out.println(D[start][end]);
        }
    }
}
