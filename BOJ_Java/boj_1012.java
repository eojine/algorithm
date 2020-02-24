import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1012_유기농배추 {

    public static int N, M, K;
    public static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][M];
            boolean[][] visit = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int m = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());

                map[n][m] = 1;
            }

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j] != 0 && !visit[i][j]) {
                        visit[i][j] = true;
                        map[i][j] += 1;
                        bfs(i, j, map, visit);

                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    public static void bfs(int n, int m, int[][] map, boolean[][] visit) {
        Queue<Dot> q = new LinkedList<>();
        q.add(new Dot(n, m));

        while(!q.isEmpty()) {

            Dot first = q.poll();

            for (int i = 0; i < dir.length; i++) {
                int nn = first.n + dir[i][0];
                int nm = first.m + dir[i][1];

                if(!inRange(nn, nm) || map[nn][nm] == 0 || visit[nn][nm]) {
                    continue;
                }

                visit[nn][nm] = true;
                map[nn][nm] = map[n][m] + 1;
                q.add(new Dot(nn, nm));
            }
        }
    }

    public static boolean inRange(int n, int m) {
        return n >= 0 && m >= 0 && n < N && m < M;
    }

    public static class Dot {
        int n, m;

        public Dot(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}
