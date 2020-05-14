import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2252 {
    static int N, M; // 그래프 정점의 수, 간선의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] degree = new int[N];

        ArrayList<Integer>[] list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            list[x].add(y);
            degree[y]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (degree[i] == 0) q.offer(i);
        }

        if (q.size() == 0) return; // 사이클이 있다.
        Queue<Integer> resQ = new LinkedList<>();

        while (!q.isEmpty()) {
            int now = q.poll();
            resQ.offer(now);

            for(int i : list[now]) {
                degree[i]--;
                if(degree[i] == 0) q.offer(i);
            }
        }

        for(int res: resQ) {
            System.out.print(res + 1 + " ");
        }
    }
}
