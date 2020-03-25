import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600_말이되고픈원숭이 {
    static int K, R, C, map[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(bfs());
    }

    static int[][][] dirs = {
            {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}, // 원숭이의 움직임 (상하좌우)
            {{-1, -2}, {1, -2}, {-2, -1}, {2, -1}, {-2, 1}, {2, 1}, {-1, 2}, {1, 2}} // 말의 움직임 (8방)
    };

    private static int bfs() {
        boolean[][][] visited = new boolean[K + 1][R][C]; // 31 * 200 * 200 : 1,200,000 byte
        Queue<int[]> q = new LinkedList<>();
        visited[K][0][0] = true;
        q.add(new int[]{K, 0, 0, 0}); // 남은 말 움직임 횟수, x,y좌표, 이동한 동작 횟수,

        while (!q.isEmpty()) {
            int[] front = q.poll();
            int k = front[0], r = front[1], c = front[2], moveCnt = front[3];

            if (r == (R - 1) && c == (C - 1)) return moveCnt;

            for (int h = 0; h < 2; h++) {
                if (h == 1) { // 말
                    if (k == 0) break; // 말의 움직임을 다 쓴 경우
                    else k--; // 말의 움직임이 남은 경우 횟수 차감.
                }
                for (int i = 0; i < dirs[h].length; i++) {
                    int nr = r + dirs[h][i][0];
                    int nc = c + dirs[h][i][1];
                    if (!inRange(nr, nc) || visited[k][nr][nc] || map[nr][nc] == 1) continue;

                    visited[k][nr][nc] = true;
                    q.add(new int[]{k, nr, nc, moveCnt + 1});
                }
            }
        }
        return -1;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
