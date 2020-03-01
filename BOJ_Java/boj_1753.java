import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753_최단경로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine()) - 1;
        ArrayList<Node>[] list = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken()); // 가중치

            list[u].add(new Node(v, w)); // 방향 그래프
        }

        int[] valArr = new int[V];

        for (int i = 0; i < V; i++) {
            if (i == start) continue;
            valArr[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));

        while(!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < list[now.idx].size(); i++) {
                int next = list[now.idx].get(i).idx;
                int nextVal = now.val + list[now.idx].get(i).val;

                if (valArr[next] > nextVal) {
                    valArr[next] = nextVal;
                    q.add(new Node(next, nextVal));
                }
            }
        }

        for (int i = 0; i < V; i++) {
            if (valArr[i] == Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(valArr[i]);
        }
    }

    public static class Node implements Comparable<Node> {
        int idx, val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }

    public static String src = "5 6\n" +
            "1\n" +
            "5 1 1\n" +
            "1 2 2\n" +
            "1 3 3\n" +
            "2 3 4\n" +
            "2 4 5\n" +
            "3 4 6";
}
