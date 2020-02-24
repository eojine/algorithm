import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178_미로탐색 {

    public static int N, M;
    public static int[][] map;
    public static boolean[][] visit;
    public static Queue<Info> q = new LinkedList<>();
    public static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            StringBuilder s = new StringBuilder(br.readLine());

            for (int j = 0; j < s.length(); j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        visit[0][0] = true;
        q.add(new Info(0, 0));

        while(!q.isEmpty()) {
            int frontN = q.peek().n;
            int frontM = q.poll().m;

            for (int i = 0; i < dir.length; i++) {
                int nn = frontN + dir[i][0];
                int nm = frontM + dir[i][1];

                if(!inRange(nn, nm) || visit[nn][nm] || map[nn][nm] == 0) {
                    continue;
                }

                q.add(new Info(nn, nm));
                visit[nn][nm] = true;
                map[nn][nm] = map[frontN][frontM] + 1;
            }
        }

        System.out.println(map[N-1][M-1]);
    }

    public static boolean inRange(int n, int m) {
        return n >= 0 && m >= 0 && n < N && m < M;
    }

    static class Info {
        int n, m;

        public Info(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}
