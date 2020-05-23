import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636_치즈 {
    static int R, C, map[][];
    static boolean visited[][];
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader("13 12\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 1 1 0 0 0\n" +
                "0 1 1 1 0 0 0 1 1 0 0 0\n" +
                "0 1 1 1 1 1 1 0 0 0 0 0\n" +
                "0 1 1 1 1 1 0 1 1 0 0 0\n" +
                "0 1 1 1 1 0 0 1 1 0 0 0\n" +
                "0 0 1 1 0 0 0 1 1 0 0 0\n" +
                "0 0 1 1 1 1 1 1 1 0 0 0\n" +
                "0 0 1 1 1 1 1 1 1 0 0 0\n" +
                "0 0 1 1 1 1 1 1 1 0 0 0\n" +
                "0 0 1 1 1 1 1 1 1 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        int result = 0, count = 0;
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            visited = new boolean[R][C];
            q.offer(new Point(0, 0));
            visited[0][0] = true;

            while (!q.isEmpty()) {
                Point now = q.poll();

                for (int d = 0; d < dirs.length; d++) {
                    int nextR = now.r + dirs[d][0];
                    int nextC = now.c + dirs[d][1];

                    if (!inRange(nextR,nextC)) continue;
                    if (map[nextR][nextC] == 0 && !visited[nextR][nextC]) {
                        visited[nextR][nextC] = true;
                        q.offer(new Point(nextR, nextC));
                    }
                    if(map[nextR][nextC] == 1 && !visited[nextR][nextC]) {
                        map[nextR][nextC] = 7;
                        visited[nextR][nextC] = true;
                    }
                }
            }


            boolean flag = false;
            int cnt = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if(map[i][j] == 7) {
                        map[i][j] = 0;
                        flag = true;
                        ++cnt;
                    }
                }
            }

            if (flag) {
                result++;
                if (cnt != 0) count = cnt;
            }

            if (cnt == 0) break;
        }
        System.out.println(result);
        System.out.println(count);
    }

    private static boolean inRange(int r, int c) {
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
