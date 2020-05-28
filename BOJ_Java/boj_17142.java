import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

public class BOJ_17142_연구소3 {

    static int N, M, map[][], result = Integer.MAX_VALUE;
    static boolean[] selected;
    static boolean[][] visited;
    static List<Virus> virus;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader("7 3\n" +
                "2 0 0 0 1 1 0\n" +
                "0 0 1 0 1 2 0\n" +
                "0 1 1 0 1 0 0\n" +
                "0 1 0 0 0 0 0\n" +
                "0 0 0 2 0 1 1\n" +
                "0 1 0 0 0 0 0\n" +
                "2 1 0 0 0 0 2"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        virus = new ArrayList<>();

        boolean flag = false;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new Virus(i, j, 2));
                    map[i][j] = 3;
                } else if (map[i][j] == 0) flag = true;
            }
        }

        if (!flag) {
            System.out.println(0);
            return;
        }
        selected = new boolean[virus.size()];
        liveVirus(0, 0, virus.size());
        System.out.println(result == Integer.MAX_VALUE ? -1 : result - 2);
    }

    private static void liveVirus(int depth, int idx, int N) {
        if (depth == M) {
            Queue<Virus> q = new LinkedList<>();

            for (int i = 0; i < N; i++)
                if (selected[i])
                    q.offer(virus.get(i));

            int res = bfs(q);
            if (res != -1) result = Math.min(res, result);

            return;
        }

        for (int i = idx; i < N; i++) {
            if (selected[i]) continue;
            selected[i] = true;
            liveVirus(depth + 1, i, N);
            selected[i] = false;
        }
    }

    private static int bfs(Queue<Virus> q) {
        visited = new boolean[N][N];
        int[][] map = arrayCopy();

        while (!q.isEmpty()) {
            Virus now = q.poll();
            visited[now.r][now.c] = true;

            for (int i = 0; i < dirs.length; i++) {
                int nextR = now.r + dirs[i][0];
                int nextC = now.c + dirs[i][1];

                if (!inRange(nextR, nextC)) continue;
                if (visited[nextR][nextC]) continue;
                if (map[nextR][nextC] == 1) continue;

                q.add(new Virus(nextR, nextC, now.time + 1));
                visited[nextR][nextC] = true;

                if (map[nextR][nextC] == 0) {
                    map[nextR][nextC] = now.time + 1;
                } else if (map[nextR][nextC] == 2) {

                }
            }
        }
        return resultSearch(map);
    }

    private static int resultSearch(int[][] map) {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    return -1;
                }

                if (map[i][j] > max) max = map[i][j];
            }
        }

        return max;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

    private static int[][] arrayCopy() {
        int[][] tmpMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmpMap[i][j] = map[i][j];
            }
        }
        return tmpMap;
    }

    static class Virus {
        int r, c, time;

        public Virus(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
}
