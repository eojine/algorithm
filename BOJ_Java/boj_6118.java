import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_6118_숨바꼭질 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] list = new ArrayList[N];
        LinkedList<Node> q = new LinkedList<>();

        for (int i = 0; i < N; i++)
            list[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            list[x].add(y);
            list[y].add(x);
        }

        q.add(new Node(0, 0));

        int[] d = new int[N];

        // 각 정점의 거리는 가장 큰 수로 초기화
        // 시작 정점을 제외한 1부터 N까지!
        for (int i = 1; i < N; i++)
            d[i] = Integer.MAX_VALUE;

        while(!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < list[now.idx].size(); i++) {
                int nextIdx = list[now.idx].get(i);
                int nextDis = now.dis + 1; // 각 정점 사이 거리는 1이기 때문.

                // 다음 정점에 있는 거리정보가 다음거리보다 작거나 같으면 continue해줌
                if (d[nextIdx] <= nextDis) continue;

                d[nextIdx] = nextDis; // 거리정보 갱신해줌.
                q.add(new Node(nextIdx, nextDis)); // 큐에 다음 노드를집어넣음.
            }
        }

        int index = 1, distance = 0, cnt = 1;

        for (int i = 1; i < N; i++) {
            if (d[i] > distance) {
                distance = d[i];
                index = i + 1;
                cnt = 1;
            } else if (d[i] == distance) {
                cnt++;
            }
        }

        System.out.println(index + " " + distance + " " + cnt);
    }

    private static class Node {
        int idx, dis;

        public Node(int idx, int dis) {
            this.idx = idx;
            this.dis = dis;
        }
    }
}
