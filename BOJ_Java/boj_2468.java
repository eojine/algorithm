import java.util.Scanner;

public class BOJ_2468_안전영역 {
    public static int N;
    public static int inputMin = Integer.MAX_VALUE;
    public static int inputMax = Integer.MIN_VALUE;
    public static int resMax = Integer.MIN_VALUE;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int[][] map;
    public static boolean[][] sink;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();

                // 가장 작은 높이 찾기.
                if (map[i][j] < inputMin)
                    inputMin = map[i][j];

                // 가장 높은 높이 찾기.
                if (map[i][j] > inputMax)
                    inputMax = map[i][j];
            }
        }

        // 가장 낮은 건물의 높이부터 가장높은 건물의 높이까지 1증가하면서 체크한다.
        for (int depth = inputMin; depth <= inputMax; depth++) {
            sink = new boolean[N][N]; // 잠긴 여부 체크

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (map[x][y] <= depth) { // 만약 건물 높이가 물 깊이보다 낮으면
                        sink[x][y] = true; // 잠긴다.
                    }
                }
            }

            int cnt = 0; // 구역마다 번호 체크

            // 현재 가라앉지 않은 건물은 DFS를 돌린다!
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    // 만약 map의 높이가 물의 깊이보다 높거나 같고, 현재 잠기지 않았을때,
                    if (map[x][y] >= depth && !sink[x][y]) {
                        cnt++; // 구역의 번호 증가
                        DFS(x, y);
                    }
                }
            }
            resMax = Math.max(resMax, cnt);
        }

        // 비가 오지 않는 경우에는 안전영역개수가 1이다!
        if (resMax == 0)
            System.out.println("1");
        else
            System.out.println(resMax);

    } // end of main

    public static void DFS(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (!inRange(nextX, nextY) || sink[nextX][nextY])
                continue;

            sink[nextX][nextY] = true;
            DFS(nextX, nextY);
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && y >= 0 && y < N && x < N;
    }
}
