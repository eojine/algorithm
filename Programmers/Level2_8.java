import java.util.LinkedList;
import java.util.Queue;

public class 다리를지나는트럭 {
    public static void main(String[] args) {
        System.out.println(solution(100, 100, new int[]{10}));
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        int currentW = 0;
        Queue<Integer> currentB = new LinkedList<>();
        int top = -1;

        for (int i = 0; i < bridge_length; i++) {
            currentB.offer(0);
        }

        while (top < truck_weights.length - 1) {
            currentW -= currentB.poll();

            if (currentW + truck_weights[top + 1] <= weight) {
                currentB.offer(truck_weights[++top]);
                currentW += truck_weights[top];
            } else {
                currentB.offer(0);
            }

            System.out.println(currentB);
            answer++;
        }

        answer += bridge_length;
        return answer;
    }
}
