import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13913_숨바꼭질4 {
    static int START, END;
    static boolean[] visited;
    static int[] path;

    public static void main(String[] args) throws IOException {
        // 가중치 없는 그래프에서 최단은 BFS가 유리하다 (최단거리라 바로 종료 가능)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());
        visited = new boolean[100001]; // 0 ~ 100000
        path = new int[100001];
        Arrays.fill(path, -1);

        System.out.println(bfs());
        Stack<Integer> stack = new Stack<>();
        int temp = END;
        do {
            stack.push(temp);
            temp = path[temp];
        } while (temp != -1);

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        System.out.println(sb.toString());
    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        visited[START] = true; // 수빈이 위치
        q.offer(new int[]{START, 0});

        int nx;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == END) {
                return cur[1];
            }

            // 걷는 경우
            // 위
            nx = cur[0] - 1;
            if (nx >= 0 && !visited[nx]) {
                visited[nx] = true;
                path[nx] = cur[0]; // 직전 위치 기억하기
                q.offer(new int[]{nx, cur[1] + 1});
            }

            // 아래
            nx = cur[0] + 1;
            if (nx <= 100000 && !visited[nx]) {
                visited[nx] = true;
                path[nx] = cur[0]; // 직전 위치 기억하기
                q.offer(new int[]{nx, cur[1] + 1});
            }

            // 순간 이동하는 경우
            nx = cur[0] << 1;
            if (nx <= 100000 && !visited[nx]) {
                visited[nx] = true;
                path[nx] = cur[0]; // 직전 위치 기억하기
                q.offer(new int[]{nx, cur[1] + 1});
            }
        }

        return -1;
    }

}
