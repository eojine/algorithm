import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

public class BOJ_1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader("4 2\n" +
                "4 2\n" +
                "3 1"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        int[] degree = new int[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            list[x].add(y);
            degree[y]++;
        }

        for (int i = 0; i < N; i++) {
            System.out.println(list[i]);
        }

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            if(degree[i] == 0) {
                q.add(i);
            }
        }

        Queue<Integer> resQ = new LinkedList<>();
        while (!q.isEmpty()) {
            int now = q.poll();
            resQ.offer(now);

            for(int i : list[now]) {
                degree[i]--;
                if (degree[i] == 0) {
                    q.offer(i);
                }
            }
        }

        for (int i: resQ) {
            System.out.print(i + 1 + " ");
        }
    }
}
