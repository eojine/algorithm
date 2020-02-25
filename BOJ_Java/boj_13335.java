import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13335_트럭 {

    public static int N, W, L;
    public static Queue<Integer> bridge = new LinkedList<>();
    public static LinkedList<Integer> trucks = new LinkedList<>();
    public static int res;
    public static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            trucks.add(Integer.parseInt(st.nextToken()));
        }

        // 다리의 마지막을 -1로 만들기 위해 임시로 삽입
        trucks.add(-1);

        for (int i = 0; i < W; i++) {
            bridge.add(0);
        }

        // 다리를 q로 놓고, 마지막은 -1로 만듦.
        while(bridge.peek() != -1) {
            int truckFront = 0;
            int bridgeFront = 0;

            // 트럭이 비어있지 않을때만 맨 처음 트럭 뺌.
            if (!trucks.isEmpty()) {
                truckFront = trucks.peek();
                bridgeFront = bridge.peek();
            }

            if(sum + truckFront - bridgeFront <= L) {
                if (!trucks.isEmpty()) {
                    sum -= bridge.poll();
                    sum += truckFront;
                    bridge.add(trucks.poll());
                } else {
                    bridge.poll();
                }
            } else {
                sum -= bridge.poll();
                bridge.add(0);
            }
            res++;
        }
        System.out.println(res);
    }
}
