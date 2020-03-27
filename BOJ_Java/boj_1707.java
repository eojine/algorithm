import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class BOJ_1707_이분그래프 {
    static int visit[];
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            // 정점들을 0번부터 시작할 수 있게 한다....
            int V = Integer.parseInt(st.nextToken()) - 1; // 0 ~ (20000 - 1)
            int E = Integer.parseInt(st.nextToken()) - 1; // 0 ~ (200000 - 1)

            // 각 정점은 1~V까지 번호가 붙어있다.
            list = new ArrayList[V + 1];
            visit = new int[V + 1];
            
            // ArrayList 초기화하기!!
            for (int i = 0; i < V + 1; i++) {
                list[i] = new ArrayList<>();
            }

            // ArrayList에 각 정점의 정보들을 넣는다.
            for (int e = 0; e <= E; e++) {
                st = new StringTokenizer(br.readLine(), " ");
                int num1 = Integer.parseInt(st.nextToken()) - 1;
                int num2 = Integer.parseInt(st.nextToken()) - 1;

                list[num1].add(num2);
                list[num2].add(num1); // 무향 그래프이기 때문에 반대편에도 넣는다.
            }

            // 리스트에 담겨진 정점의 정보들이 이어져있는지 확인한다.
            for (int i = 0; i < V + 1; i++) {
                // 방문 : 0, 인접한 정점 표시 : -1, 1
                if (visit[i] == 0) { // 만약 방문한적이 없다면
                    bfs(i); // bfs돌린다.
                }
            }

            String result = "YES";
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < list[i].size(); j++) {
                    // 만약 인접한 정점이 하나라도 같은색이라면 "NO"
                    if (visit[i] == visit[list[i].get(j)]) {
                        result = "NO";
                        break;
                    }
                }
            }

            System.out.println(result);
        }
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start); // 큐에 현재 정점을 넣어준다.
        visit[start] = 1; // 방문 표시 해준다.

        while (!q.isEmpty()) {
            int front = q.poll();
            int visited = visit[front]; // 현재 어디로 방문했는지 확인하기 위한 변수

            for (int i = 0; i < list[front].size(); i++) { // 현재 정점에 연결되어있는 리스트를 방문한다.
                int next = list[front].get(i); // 현재 정점에 연결된 다음 정점.

                if (visit[next] == 0) { // 다음 정점에 방문한적이 없다면
                    if (visited == 1) visit[next] = -1; // 서로 반대로 방문 표시 해줌.
                    else if (visited == -1) visit[next] = 1;
                    q.add(next);
                }
            }
        }
    }
}
