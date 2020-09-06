import java.util.LinkedList;
import java.util.Queue;

public class 경주로건설 {
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        System.out.println(solution(new int[][]{{0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 1}, {0, 0, 1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}}));
        System.out.println(solution(new int[][]{{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}}));
        System.out.println(solution(new int[][]{{0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 0}, {0, 0, 1, 0, 0, 0}, {1, 0, 0, 1, 0, 1}, {0, 1, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0}}));
    }

    static final int CORNER = 500;
    static final int STRAIGHT = 100;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int solution(int[][] board) {
        return BFS(board);
    }

    static int BFS(int[][] map) {
        int min = Integer.MAX_VALUE;
        int N = map.length;

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, -1, 0));
        map[0][0] = 1;

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.r == N - 1 && now.c == N - 1) {
                min = Math.min(min, now.cost);
            }

            for (int d = 0; d < dirs.length; d++) {
                int nr = now.r + dirs[d][0];
                int nc = now.c + dirs[d][1];
                int nd = d;
                int nCost = now.cost + ((now.dir == -1 ? d : now.dir) == d ? STRAIGHT : CORNER + STRAIGHT);

                if (!inRange(nr, nc, N) || map[nr][nc] == 1) continue;

                // 이미 다른 경로로 한 번 거쳤을 때
                if (map[nr][nc] != 0) {
                    // 만약 맵 경로 돈이 같거나 더 크면 맵에 넣어줌
                    if (map[nr][nc] >= nCost) {
                        map[nr][nc] = nCost;
                        q.offer(new Point(nr, nc, nd, nCost));
                    }
                }

                // 한번도 경로 거친적이 없을 때
                else {
                    map[nr][nc] = nCost;
                    q.offer(new Point(nr, nc, nd, nCost));
                }
            }
        }
        return map[N - 1][N - 1];
    }

    static boolean inRange(int r, int c, int len) {
        return r >= 0 && c >= 0 && r < len && c < len;
    }

    static class Point {
        int r, c, dir, cost;

        public Point(int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }
    }
}
