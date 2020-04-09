import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1197_최소스패닝트리 {

    static int[] parents;
    static int[] ranks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader("3 3\n" +
                "1 2 1\n" +
                "2 3 2\n" +
                "1 3 3"));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] edges = new int[E][3];
        parents = new int[V];
        ranks = new int[V];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            edges[i][0] = Integer.parseInt(st.nextToken()) - 1;
            edges[i][1] = Integer.parseInt(st.nextToken()) - 1;
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        // 가중치 순서로 정렬
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        for (int i = 0; i < V; i++) {
            makeSet(i);
        }

        int result = 0;
        int cnt = 0;
        for (int i = 0; i < E; i++) {
            int a = findSet(edges[i][0]);
            int b = findSet(edges[i][1]);

            if (a == b) continue;

            unionSet(a, b);

            result += edges[i][2];
            cnt++;

            if (cnt == V - 1) {
                break;
            }
        }

        System.out.println(result);
    }

    private static void makeSet(int i) {
        parents[i] = i;
    }

    private static int findSet(int x) {
        if (x == parents[x])
            return x;
        else {
            parents[x] = findSet(parents[x]);
            return parents[x];
        }
    }

    private static void unionSet(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);

        if (ranks[px] > ranks[py]) {
            parents[py] = px;
        } else {
            parents[px] = py;
            if (ranks[px] == ranks[py]) {
                ranks[py]++;
            }
        }
    }
}
