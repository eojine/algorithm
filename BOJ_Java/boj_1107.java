package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1107_리모컨 {

    static int channel;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        channel = Integer.parseInt(br.readLine());
        int brokenCnt = Integer.parseInt(br.readLine());

        boolean[] button = new boolean[10];

        if (brokenCnt > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < brokenCnt; i++) {
                button[Integer.parseInt(st.nextToken())] = true;
            }
        }

        if (channel == 100) {
            System.out.println(0);
            return;
        }

        min = Math.abs(channel - 100);
        System.out.println(solve(button));
    }

    private static int solve(boolean[] button) {
        int newMin = min;
        String tmp = "";
        String tMin = "";

        for (int i = 0; i < 1000000; i++) {
            tmp = i + "";
            boolean isPressable = true;

            for (int j = 0; j < tmp.length(); j++) {
                if (button[tmp.charAt(j) - '0']) {
                    isPressable = false;
                    break;
                }
            }

            if (!isPressable) continue;
            if (newMin > Math.abs(channel - i)) {
                newMin = Math.abs(channel - i);
                tMin = tmp;
            }

        }

        newMin += tMin.length();
        return min > newMin ? newMin : min;
    }

}
