import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class BOJ_17406_배열돌리기4 {

    static int N, M, K, map[][], result = Integer.MAX_VALUE;
    static Info[] kInfo;
    static int[][] turnMap;
    static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상

    static class Info {
        int c, r, s;

        public Info(int c, int r, int s) {
            this.c = c;
            this.r = r;
            this.s = s;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader("5 6 2\n" +
                "1 2 3 2 5 6\n" +
                "3 8 7 2 1 3\n" +
                "8 2 3 1 4 5\n" +
                "3 4 5 1 1 1\n" +
                "9 3 2 1 4 3\n" +
                "3 4 2\n" +
                "4 2 1"));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        kInfo = new Info[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine(), " ");
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            kInfo[k] = new Info(c, r, s);
        }
        permutation(0, new boolean[K], new int[K]); // cnt, visited 되었는지 여부
        System.out.println(result);
    }

    private static void permutation(int cnt, boolean[] visited, int[] tmp) {
        if (cnt == K) {
            turnMap = cloneArr(map); // 하나의 순열이 시작할 때 원래의 맵으로 초기화 해줌
            for (int i = 0; i < K; i++)
                turnArr(kInfo[tmp[i]]); // 순서대로 돌림.

            // 최소값 구함.
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int j = 0; j < M; j++)
                    sum += turnMap[i][j];
                if (min > sum) min = sum;
            }
            if (min < result) result = min;
            return;
        }

        for (int i = 0; i < K; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            tmp[cnt] = i;
            permutation(cnt + 1, visited, tmp);
            visited[i] = false;
        }
    }

    private static int[][] cloneArr(int[][]map) {
        int[][] tmpArr = new int[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                tmpArr[i][j] = map[i][j];
        return tmpArr;
    }

    private static void turnArr(Info current) {
        int[][] tmpMap = cloneArr(turnMap);
        int C = current.c - 1;
        int R = current.r - 1;
        int S = current.s;

        while(S > 0) {
            // 맨 처음 점 넣기.
            int n = C - S;
            int m = R - S;

            for (int d = 0; d < dirs.length; d++) {
                while (true) {
                    int nextN = n + dirs[d][0];
                    int nextM = m + dirs[d][1];
                    if (nextM < R - S || nextM > R + S || nextN < C - S || nextN > C + S) break;
                    turnMap[nextN][nextM] = tmpMap[n][m];
                    n = nextN;
                    m = nextM;
                }
            }
            S--;
        }
    }
}
