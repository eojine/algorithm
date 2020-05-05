package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1949 {
    static int N, K, max, map[][];
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            max = -1;
            N = Integer.parseInt(st.nextToken()); // map 크기
            K = Integer.parseInt(st.nextToken()); // 최대 공사할 수 있는 깊이
            map = new int[N][N];
            visit = new boolean[N][N];

            int high = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    high = Math.max(high, map[i][j]);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == high) dfs(i, j, 1, false);
                }
            }
            sb.append("#").append(tc).append(' ').append(max).append('\n');
        }
        System.out.println(sb);
    }

    private static void dfs(int i, int j, int cnt, boolean use) {
        max = Math.max(max, cnt);
        visit[i][j] = true;

        for (int d = 0; d < 4; d++) {
            int nextI = dirs[d][0] + i;
            int nextJ = dirs[d][1] + j;

            // 이미 방문했거나 범위안에 있지 않다면 진행X
            if (!inRange(nextI, nextJ)) continue;
            if (visit[nextI][nextJ]) continue;

            if (map[nextI][nextJ] < map[i][j]) {
                dfs(nextI, nextJ, cnt + 1, use);
            } else {
                if (!use) { // 깎지 않았다면
                    for (int k = 1; k <= K; k++) {
                        if (map[nextI][nextJ] - k < map[i][j]) {
                            map[nextI][nextJ] -= k;
                            dfs(nextI, nextJ, cnt + 1, true);
                            map[nextI][nextJ] += k;
                        }
                    }
                }
            }
        }
        visit[i][j] = false;
    }

    private static boolean inRange(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }
}
