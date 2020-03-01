import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1238_파티 {

    public static int N, M, X;
    public static ArrayList<Node>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        list = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            list[u].add(new Node(v, d));
        }

        int max = 0;
        for (int s = 0; s < N; s++) {
            if (s == X - 1) continue;
            
            // 학생 s가 파티장까지 가는 최단경로
            int a = dijkstra(s, X - 1);
            
            // 파티장에서 학생 s네 마을까지의 최단 경로
            int b = dijkstra(X - 1, s);

            // 학생들의 최단경로중 가장 먼 거리 구하기
            if (max < a + b) {
                max = a + b;
            }
        }

        System.out.println(max);
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        int[] D = new int[N];
        for (int i = 0; i < N; i++) {
            if (i == start) continue;
            D[i] = Integer.MAX_VALUE;
        }

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.idx == end) break;

            for (int i = 0; i < list[now.idx].size(); i++) {
                int nextIdx = list[now.idx].get(i).idx;
                int nextDis = now.dis + list[now.idx].get(i).dis;

                if (D[nextIdx] > nextDis) {
                    D[nextIdx] = nextDis;
                    pq.add(new Node(nextIdx, nextDis));
                }
            }
        }

        return D[end];
    }

    private static class Node implements Comparable<Node> {
        int idx, dis;

        public Node(int idx, int dis) {
            this.idx = idx;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return this.dis - o.dis;
        }
    }
}
