package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지안녕 {

    public static int R, C, T;
    public static int[][] map;
    public static int[] airCleaner; // 열은 무조건 0이기 때문에 행 정보만 저장한다.
    public static int[][] dir = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}}; // 상, 우, 좌, 하

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        airCleaner = new int[2];
        int tmpCnt = 0;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == -1) {
                    airCleaner[tmpCnt++] = i;
                }
            }
        }

        for (int t = 0; t < T; t++) {
            LinkedList<Info> dusts = new LinkedList<>(); // 먼지 정보는 queue에 넣어 퍼트린다.
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] > 0) {
                        dusts.add(new Info(i, j, map[i][j]));
                    }
                }
            }

            while (!dusts.isEmpty()) {
                Info dust = dusts.poll();
                int moveCnt = 0; // 퍼트린 횟수 저장
                int moveDust = 0; // 퍼트린 양 저장

                for (int i = 0; i < dir.length; i++) {
                    int nextDustR = dust.r + dir[i][0];
                    int nextDustC = dust.c + dir[i][1];

                    if (inRange(nextDustR, nextDustC) && map[nextDustR][nextDustC] != -1) {
                        moveDust = dust.amount / 5;
                        moveCnt++;
                        map[nextDustR][nextDustC] += moveDust;
                    }
                }
                // 현재 위치의 먼지는 퍼트린 먼지수만큼 빼주어야함.
                map[dust.r][dust.c] -= (moveDust * moveCnt);
            }

            int[][] copyMap = new int[R][C];

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    copyMap[i][j] = map[i][j];
                }
            }


            // 위쪽 공기청정기 - 반시계방향 우(1) 상(0) 좌(2) 하(3)
            int[] rclock = {1, 0, 2, 3};

            // 아래쪽 공기청정기 - 시계방향 우(1) 하(3) 좌(2) 상(0)
            int[] clock = {1, 3, 2, 0};

            // 위쪽 공기청정기
            int airR = airCleaner[0];
            int airC = 1; // 공기청정기의 오른쪽부터 시작
            map[airR][airC] = 0; // 공기청정기에서 나온 공기는 먼지가 0!

            // 4개의 방향으로 돌아감.
            for (int i = 0; i < 4; i++) {
                while (true) {
                    int nextR = airR + dir[rclock[i]][0];
                    int nextC = airC + dir[rclock[i]][1];

                    // 범위에 벗어나거나 공기청정기에 닿으면 종료.
                    if (!inRange(nextR, nextC) || map[nextR][nextC] == -1) break;

                    // 이동한 좌표는 이전의 정보를 넣어주어야 함.
                    map[nextR][nextC] = copyMap[airR][airC];
                    airR = nextR;
                    airC = nextC;
                }
            }

            // 아래쪽 공기청정기
            airR = airCleaner[1];
            airC = 1; // 공기청정기의 오른쪽부터 시작
            map[airR][airC] = 0; // 공기청정기에서 나온 공기는 먼지가 0!

            for (int i = 0; i < 4; i++) {
                while (true) {
                    int nextR = airR + dir[clock[i]][0];
                    int nextC = airC + dir[clock[i]][1];

                    if (!inRange(nextR, nextC) || map[nextR][nextC] == -1) break;

                    map[nextR][nextC] = copyMap[airR][airC];
                    airR = nextR;
                    airC = nextC;
                }
            }
        }

        System.out.println(allTheDust());
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && c < C && r < R;
    }

    public static class Info {
        int r, c, amount;

        public Info(int r, int c, int amount) {
            this.r = r;
            this.c = c;
            this.amount = amount;
        }
    }

    public static int allTheDust() {
        int allAmount = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1)
                    continue;
                allAmount += map[i][j];
            }
        }

        return allAmount;
    }

}
