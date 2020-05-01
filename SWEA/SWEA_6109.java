import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class SWEA_6109 {

    public static int[] di = {-1, 1, 0, 0}; // 상 하 좌 우
    public static int[] dj = {0, 0, -1, 1};
    public static int[][] map;
    public static boolean[][] visit;
    public static int N;

    public static void move(int i, int j, int d) {
        int ni = i + di[d];
        int nj = j + dj[d];

        if(0 <= ni && ni < N && 0 <= nj && nj < N && !visit[ni][nj]) {
            if(map[i][j] != 0 && map[i][j] == map[ni][nj] && !visit[i][j]) {
                map[ni][nj] = map[i][j] * 2;
                map[i][j] = 0;
                visit[ni][nj] = true;
            } else if (map[ni][nj] == 0) {
                map[ni][nj] = map[i][j];
                map[i][j] = 0;
            }
            move(ni, nj, d);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        br = new BufferedReader(new StringReader(src));

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
            }

            visit = new boolean[N][N];
            switch (dir) {
                case "up": // 0
                    for (int j = 0; j < N; j++) {
                        for (int i = 1; i < N; i++) {
                            move(i, j, 0);
                        }
                    }
                    break;
                case "down": // 1
                    for (int j = 0; j < N; j++) {
                        for (int i = N - 2; i >= 0; i--) {
                            move(i, j, 1);
                        }
                    }
                    break;
                case "left": // 2
                    for (int i = 0; i < N; i++) {
                        for (int j = 1; j < N; j++) {
                            move(i, j, 2);
                        }
                    }
                    break;
                case "right": // 3
                    for (int i = 0; i < N; i++) {
                        for (int j = N - 2; j >= 0; j--) {
                            move(i, j, 3);
                        }
                    }
                    break;
            }
            sb.append("#" + tc + "\n");
            for(int[] ma: map) {
                for(int m: ma) {
                    sb.append(m + " ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
        br.close();
    }

    public static String src = "2\n" +
            "5 up\n" +
            "4 8 2 4 0\n" +
            "4 4 2 0 8\n" +
            "8 0 2 4 4\n" +
            "2 2 2 2 8\n" +
            "0 2 2 0 0\n" +
            "2 down\n" +
            "16 2\n" +
            "0 2\n";
}
