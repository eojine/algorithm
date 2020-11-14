import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1743_음식물피하기 {

    static int R, C, maxCount = 0;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] map;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br = new BufferedReader(new StringReader("3 4 5\n" +
                "3 2\n" +
                "2 2\n" +
                "3 1\n" +
                "2 3\n" +
                "1 1"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visit = new boolean[R][C];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 1;
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0 || visit[i][j]) continue;
                int count = BFS(i, j);
                maxCount = maxCount < count ? count : maxCount;
            }
        }

        System.out.println(maxCount);
        br.close();
    }

    public static int BFS(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        int count = 0;
        visit[r][c] = true;
        q.add(new Point(r, c));

        while (!q.isEmpty()) {
            Point now = q.poll();
            if (map[now.r][now.c] == 1) count++;

            for (int i = 0; i < dirs.length; i++) {
                int nextR = now.r + dirs[i][0];
                int nextC = now.c + dirs[i][1];

                if (!inRange(nextR, nextC) || map[nextR][nextC] == 0 || visit[nextR][nextC]) continue;
                q.add(new Point(nextR, nextC));
                visit[nextR][nextC] = true;
            }
        }

        return count;
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
