import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_2667_단지번호붙이기 {

    public static int N;
    public static int[][] map;
    public static boolean[][] visit;
    public static int cnt, danjiCnt = 1;
    public static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public static ArrayList<Integer> danji = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        br = new BufferedReader(new StringReader(src));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1 && !visit[i][j]) {
                    cnt++;
                    visit[i][j] = true;
                    dfs(i, j, cnt);

                    danji.add(danjiCnt);
                    danjiCnt = 1;
                }
            }
        }

        Collections.sort(danji);
        System.out.println(cnt);
        for (int item: danji) {
            System.out.println(item);
        }
    }

    public static void dfs(int r, int c, int cnt) {

        for (int i = 0; i < dir.length; i++) {
            int nr = r + dir[i][0];
            int nc = c + dir[i][1];

            if(!inRange(nr, nc) || visit[nr][nc] || map[nr][nc] == 0) {
                continue;
            }

            visit[nr][nc] = true;
            dfs(nr, nc, cnt);
            danjiCnt++;
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0  && c >= 0 && r < N && c < N;
    }

    public static String src = "7\n" +
            "0110100\n" +
            "0110101\n" +
            "1110101\n" +
            "0000111\n" +
            "0100000\n" +
            "0111110\n" +
            "0111000";
}
