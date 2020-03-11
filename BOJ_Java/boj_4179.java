import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * #: 벽
 * .: 지나갈 수 있는 공간
 * J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
 * F: 불이난 공간
 *
 * BFS로 푼다.
 * 불은 J와 .을 지나갈 수 있다.
 * 지훈이는 .만을 지나갈 수 있다. 지훈이의 time에 + 1 을 한다.
 *
 * 미로를 통과하는 가장 최소값을 찾아야 했는데, 그 부분을 놓쳤었다.
 */

public class BOJ_4179_불 {

    static int R, C;
    static char[][] map;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static Queue<Point> fire = new LinkedList<>();
    static Queue<Point> jihun = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = s.charAt(c);
                if (map[r][c] == 'J') jihun.add(new Point(r, c, 0));
                if (map[r][c] == 'F') fire.add(new Point(r, c, 0));
            }
        }
        
        int tmp = Integer.MAX_VALUE; // 가장 큰 값으로 초기화해놓는다.
        while (true) { // 분단위로 계속 진행되기 때문에 무한루프로 설정.
            if (jihun.isEmpty()) break; // 지훈이가 비어있으면 미로는 종료된다.
            addFire();
            tmp = Math.min(go(), tmp);
        }
        System.out.println(tmp == Integer.MAX_VALUE ? "IMPOSSIBLE": tmp);
    }

    private static int go() {
        int size = jihun.size();
        for (int s = 0; s < size; s++) {
            Point first = jihun.poll();
            for (int i = 0; i < dir.length; i++) {
                int nr = first.r + dir[i][0];
                int nc = first.c + dir[i][1];
                if (!inRange(nr, nc)) return first.time + 1;
                if (map[nr][nc] == '.') {
                    map[nr][nc] = 'J';
                    jihun.add(new Point(nr, nc, first.time + 1));
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static void addFire() {
        int fireSize = fire.size();
        for (int s = 0; s < fireSize; s++) {
            Point first = fire.poll();
            for (int i = 0; i < dir.length; i++) {
                int nr = first.r + dir[i][0];
                int nc = first.c + dir[i][1];
                if (inRange(nr, nc) && map[nr][nc] != '#' && map[nr][nc] != 'F') {
                    map[nr][nc] = 'F';
                    fire.add(new Point(nr, nc, 0));
                }
            }
        }
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
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
