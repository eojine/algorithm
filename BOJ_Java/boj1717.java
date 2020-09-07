import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class BOJ_1717_집합의표현 {

    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader("7 8\n" +
                "0 1 3\n" +
                "1 1 7\n" +
                "0 7 6\n" +
                "1 7 1\n" +
                "0 3 7\n" +
                "0 4 2\n" +
                "0 1 1\n" +
                "1 1 1"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        rank = new int[N + 1];

        for (int i = 0; i <= N; i++)
            parent[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int caseNumber = Integer.parseInt(st.nextToken());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            switch (caseNumber) {
                case 0:
                    union(num1, num2);
                    break;
                case 1:
                    if (find(num1) == find(num2))
                        System.out.println("YES");
                    else
                        System.out.println("NO");
                    break;
            }
        }

    }

    public static int find(int x) {
        if (x == parent[x])
            return x;
        else {
            return parent[x] = find(parent[x]);
        }
    }

    public static void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        // 짧은 레벨을 긴쪽에 붙임.
        if (rank[px] > rank[py]) {
            parent[py] = px;
        } else {
            parent[px] = py;
            if (rank[px] == rank[py]) {
                rank[py]++;
            }
        }
    }

}
