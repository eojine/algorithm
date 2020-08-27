package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class BOJ_10775_공항 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader("4\n" +
                "6\n" +
                "2\n" +
                "2\n" +
                "3\n" +
                "3\n" +
                "4\n" +
                "4"));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        int result = 0;
        boolean[] gate = new boolean[G];

        for (int i = 0; i < P; i++) {
            int range = Integer.parseInt(br.readLine()) - 1;

            // 만약 범위중 가장 높은 수의 게이트가 차 있다면,
            if (gate[range]) {
                boolean flag = false;
                int index = 0;

                // 그 아래중에서 가장 높은 수의 게이트를 찾는다.
                for (int j = range; j >= 0; j--) {
                    if (!gate[j]) {
                        flag = true;
                        index = j;
                        break;
                    }
                }

                // 만약 가능한 게이트가 없다면
                if (!flag) break;

                // 가능한 게이트가 있다면!
                gate[index] = true;
                result += 1;

            } else {
                gate[range] = true;
                result += 1;
            }
        }

        System.out.println(result);
    }
}
