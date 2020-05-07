import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2146_다리만들기 {
    static int N, map[][], islandNum, minDist;
    static boolean visit[][];
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N][N];
        islandNum = 2;
        minDist = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j] || map[i][j] == 0) continue;
                BFS(i, j);
                islandNum++;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 1) bridgeBfs(i, j);
            }
        }
        System.out.println(minDist);
    }

    private static void bridgeBfs(int i, int j) {
        Queue<Point> q = new LinkedList<>();
        visit = new boolean[N][N];
        visit[i][j] = true;
        q.offer(new Point(i, j, map[i][j], 0));

        while (!q.isEmpty()) {
            Point now = q.poll();
            if (now.d >= minDist) break;
            for (int d = 0; d < dirs.length; d++) {
                int nr = dirs[d][0] + now.r;
                int nc = dirs[d][1] + now.c;
                if (!inRange(nr, nc) || visit[nr][nc]) continue;
                visit[nr][nc] = true;
                if (map[nr][nc] == now.cnt) continue;
                else if (map[nr][nc] == 0) q.offer(new Point(nr, nc, now.cnt, now.d + 1));
                else if (map[nr][nc] != now.cnt) {
                    minDist = Math.min(minDist, now.d);
                    return;
                }
            }
        }
    }

    private static void BFS(int i, int j) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(i, j, islandNum));
        visit[i][j] = true;
        map[i][j] = islandNum;
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int d = 0; d < dirs.length; d++) {
                int nr = dirs[d][0] + now.r;
                int nc = dirs[d][1] + now.c;
                if (!inRange(nr, nc)) continue;
                if (map[nr][nc] == 1 && !visit[nr][nc]) {
                    visit[nr][nc] = true;
                    map[nr][nc] = islandNum;
                    q.offer(new Point(nr, nc, islandNum));
                }
            }
        }
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c < N && c >= 0;
    }

    static class Point {
        int r, c, cnt;
        int d; //bfs의 depth

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        public Point(int r, int c, int cnt, int d) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.d = d;
        }
    }
}
