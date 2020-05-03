package BOJ.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12100_2048 {
    static int N, max;
    static int[][] map, tmpMap;
    static boolean[][] visit;
    static int[] dirNums = new int[5];
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dirPerm(0);
        System.out.println(max);
    }

    private static void move(int i, int j, int dir) {
        int nextI = dirs[dir][0] + i;
        int nextJ = dirs[dir][1] + j;

        if (nextI >= 0 && nextI < N && nextJ >= 0 && nextJ < N && !visit[nextI][nextJ]) {
            if (tmpMap[nextI][nextJ] != 0 && tmpMap[i][j] == tmpMap[nextI][nextJ] && !visit[i][j]) {
                tmpMap[nextI][nextJ] = tmpMap[i][j] * 2;
                tmpMap[i][j] = 0;
                visit[nextI][nextJ] = true;
            } else if (tmpMap[nextI][nextJ] == 0) {
                tmpMap[nextI][nextJ] = tmpMap[i][j];
                tmpMap[i][j] = 0;
            }
            move(nextI, nextJ, dir);
        }
    }

    private static void dirPerm(int cnt) {
        if (cnt == 5) {
            tmpMap = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tmpMap[i][j] = map[i][j];
                }
            }

            for (int n = 0; n < 5; n++) {
                visit = new boolean[N][N];
                 switch (dirNums[n]) {
                     case 0:
                         for (int j = 0; j < N; j++) {
                             for (int i = 1; i < N; i++) {
                                 move(i, j, 0);
                             }
                         }
                         break;
                     case 1:
                         for (int j = 0; j < N; j++) {
                             for (int i = N - 2; i >= 0; i--) {
                                 move(i, j, 1);
                             }
                         }
                         break;
                     case 2:
                         for (int i = 0; i < N; i++) {
                             for (int j = 1; j < N; j++) {
                                 move(i, j, 2);
                             }
                         }
                         break;
                     case 3:
                         for (int i = 0; i < N; i++) {
                             for (int j = N - 2; j >= 0; j--) {
                                 move(i, j, 3);
                             }
                         }
                         break;
                 }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (tmpMap[i][j] > max)
                        max = tmpMap[i][j];
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            dirNums[cnt] = i;
            dirPerm(cnt + 1);
        }
    }
}
