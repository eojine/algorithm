import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1953_탈주범검거 {

    static int N, M, R, C, L, cnt, map[][];
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 위, 아래, 왼, 오
    static List<Integer>[] tunnel;
    static boolean[][] visited;
    static int[][] dirTunnel = new int[4][4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        tunnel = new List[8];
        for (int i = 0; i < 8; i++) {
            tunnel[i] = new ArrayList<>();
        }

        for (int i = 0; i < 4; i++) tunnel[1].add(i);
        tunnel[2].add(0); tunnel[2].add(1);
        tunnel[3].add(2); tunnel[3].add(3);
        tunnel[4].add(0); tunnel[4].add(3);
        tunnel[5].add(1); tunnel[5].add(3);
        tunnel[6].add(1); tunnel[6].add(2);
        tunnel[7].add(0); tunnel[7].add(2);

        dirTunnel[0] = new int[] {1, 2, 5, 6};
        dirTunnel[1] = new int[] {1, 2, 4, 7};
        dirTunnel[2] = new int[] {1, 3, 4, 5};
        dirTunnel[3] = new int[] {1, 3, 6, 7};

        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cnt = 0;
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();

            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++) 
                    if(visited[i][j]) cnt++;
                
            System.out.println("#" + tc + " " + cnt);

        }
    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        // 첫 시작점
        q.add(new Point(R, C, 1));
        visited[R][C] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();
            
            for (int d : tunnel[map[now.r][now.c]]) {
                int nextR = dirs[d][0] + now.r;
                int nextC = dirs[d][1] + now.c;
                if(now.time == L) return;
                if (!inRange(nextR, nextC)) continue;
                if (visited[nextR][nextC] || map[nextR][nextC] == 0) continue;
                int nextTunnelNum = map[nextR][nextC];

                for (int i = 0; i < 4; i++) {
                    // 만약 다음 위치의 터널이 현재 위치 터널에서 갈 수 있는 곳이라면
                    if (dirTunnel[d][i] == nextTunnelNum) {
                        // 간다.
                        q.offer(new Point(nextR, nextC, now.time + 1));
                        visited[nextR][nextC] = true;
                    }
                }
            }
        }
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

    static class Point {
        int r, c, time;
        public Point(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
}
