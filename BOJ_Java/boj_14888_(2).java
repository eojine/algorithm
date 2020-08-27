package BOJ.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14888_연산자끼워넣기 {

    static int N, MAX = Integer.MIN_VALUE, MIN = Integer.MAX_VALUE;
    static int[] numbers;
    static Character[] opArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader("6\n" +
                "1 2 3 4 5 6\n" +
                "2 1 1 1"));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        ArrayList<Character> ops = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                switch (i) {
                    case 0:
                        ops.add('+');
                        break;
                    case 1:
                        ops.add('-');
                        break;
                    case 2:
                        ops.add('*');
                        break;
                    case 3:
                        ops.add('/');
                        break;
                }
            }
        }

        opArr = ops.toArray(new Character[ops.size()]);
        perm(0);
        System.out.println(MAX);
        System.out.println(MIN);
    }

    private static void perm(int depth) {
        if (depth == opArr.length) {
            int res = numbers[0];
            for (int i = 1; i < numbers.length; i++) {
                switch (opArr[i - 1]){
                    case '+':
                        res += numbers[i];
                        break;
                    case '-':
                        res -= numbers[i];
                        break;
                    case '*':
                        res *= numbers[i];
                        break;
                    case '/':
                        res /= numbers[i];
                        break;
                }
            }
            MAX = Math.max(res, MAX);
            MIN = Math.min(res, MIN);
            return;
        }

        for (int i = depth; i < opArr.length; i++) {
            char temp = opArr[i];
            opArr[i] = opArr[depth];
            opArr[depth] = temp;

            perm(depth + 1);

            temp = opArr[i];
            opArr[i] = opArr[depth];
            opArr[depth] = temp;
        }
    }
}
