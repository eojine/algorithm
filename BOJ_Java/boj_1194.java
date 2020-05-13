import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1194 {
    static int R, C, time;
    static boolean visit[][][];
    static char map[][];
    static Queue<Point> q;
    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        q = new LinkedList<>();
        map = new char[R][C];
        visit = new boolean[R][C][1 << 6];

        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = s.charAt(c);
                if (map[r][c] == '0') q.offer(new Point(r, c, 0));
            }
        }
        System.out.println(BFS());
    }

    static int BFS() {
        while (!q.isEmpty()) {
            int size = q.size();
            for (int qsize = 0; qsize < size; qsize++) {
                Point now = q.poll();

                // 다음이 출구이면 바로 빠져나옴.
                if (map[now.r][now.c] == '1') return time;

                for (int d = 0; d < dirs.length; d++) {
                    int nr = dirs[d][0] + now.r;
                    int nc = dirs[d][1] + now.c;
                    int key = now.key;

                    if (!inRange(nr, nc)) continue; // 다음이 범위 안에 없으면 다음으로
                    if (visit[nr][nc][key]) continue; // 현재 키를 가지고있는 상태에서 다음 위치에 도착한 적이 있을 때 다음으로.
                    if (map[nr][nc] == '#') continue; // 다음이 벽이면 다음으로.

                    // 열쇠로 이동했을 때 바로 true로 저장.
                    if ('a' <= map[nr][nc] && map[nr][nc] <= 'f') {
                        key |= (1 << map[nr][nc] - 97);
                    }

                    // 문으로 이동했을 때 열쇠가 없다면 다음으로.
                    else if ('A' <= map[nr][nc] && map[nr][nc] <= 'F') {
                        if ((key & (1 << map[nr][nc] - 65)) == 0) continue;
                    }

                    q.offer(new Point(nr, nc, key));
                    visit[nr][nc][key] = true;
                }
            }
            time++;
        } // end of while
        return -1;
    } // end of BFS

    static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    static class Point {
        int r, c, key;

        public Point(int r, int c, int key) {
            this.r = r;
            this.c = c;
            this.key = key;
        }
    }
}
