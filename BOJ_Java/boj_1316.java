package BOJ;

/**
 * 메모리 : 12988 kb
 * 시간 : 76 ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1316_그룹단어체커 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int CNT = Integer.parseInt(br.readLine());
        int result = 0;
        for (int cnt = 0; cnt < CNT; cnt++) {

            int[] alpha = new int[30];
            boolean flag = false;

            String s = br.readLine();
            alpha[s.charAt(0) - 'a'] = 1;

            for (int i = 1; i < s.length(); i++) {
                int bx = s.charAt(i - 1) - 'a';
                int x = s.charAt(i) - 'a';

                if (bx == x) continue;

                if (alpha[x] == 1) flag = true;
                else alpha[x] = 1;
            }
            if (!flag) result++;
        }
        System.out.println(result);
    }
}
