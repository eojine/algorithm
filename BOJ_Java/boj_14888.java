import java.util.Scanner;

public class BOJ_14888_연산자끼워넣기 {
    static int N;
    static char[] op = new char[12];
    static int[] arr;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int opCnt = 0;
        for (int i = 0; i < 4; i++) {
            int num = sc.nextInt();
            char ch = ' ';
            switch (i) {
                case 0:
                    ch = '+';
                    break;
                case 1:
                    ch = '-';
                    break;
                case 2:
                    ch = '*';
                    break;
                case 3:
                    ch = '/';
                    break;
                default:
                    break;
            }

            for (int j = 0; j < num; j++) {
                op[opCnt++] = ch;
            }
        }

        int[] indexes = new int[N - 1]; // 순열의 index 순서를 지정해주는 배열
        backTrack(indexes, 0);

        System.out.println(max);
        System.out.println(min);
    }

    public static void backTrack(int[] indexes, int k) {
        if (k == N - 1) { // 종료파트
            process_solution(indexes, k); // 현재단계에서 만들어낸 순열을 뽑기
        } else { // 재귀파트
            int[] c = new int[indexes.length]; // 후보군을 담을 배열. 사용하지 않은 숫자만 묶어 후보군으로 지정.
            int ncands = make_candidates(indexes, k, c); // 후보군의 개수

            // 후보군의 개수만큼 반복, 배열의 원소를 넣고 재귀호출
            for (int i = 0; i < ncands; i++) {
                indexes[k] = c[i];
                backTrack(indexes, k + 1);
            }
        }
    }

    /** 후보군 배열을 세팅 후, 후보군 개수를 리턴 */
    public static int make_candidates(int[] indexes, int k, int[] c) {
        boolean[] in_perm = new boolean[indexes.length]; // 사용한 숫자인지 체크할 플래그 변수
        for (int i = 0; i < k; i++) { // 현재 단계 전까지 사용한 숫자를 체크
            in_perm[indexes[i]] = true;
        }

        // 플래그 변수에서 false인 숫자를 후보군으로 만들기
        int ncands = 0; // 후보군 개수 저장.
        for (int i = 0; i < in_perm.length; i++) {
            if (!in_perm[i]) {
                c[ncands++] = i;
            }
        }

        return ncands; // 후보군의 개수 리턴
    }

    /** indexes배열의 원소를 보고 순열을 출력하기 */
    public static void process_solution(int[] indexes, int k) {

        int res = arr[0];

        for (int i = 0; i < k; i++) { // 왜 k??
//            System.out.print(arr[i + 1] + " " + op[indexes[i]] + " "); // 저장된 순번 출력.

            switch (op[indexes[i]]) {
                case '+':
                    res += arr[i + 1];
                    break;
                case '-':
                    res -= arr[i + 1];
                    break;
                case '*':
                    res *= arr[i + 1];
                    break;
                case '/':
                    res /= arr[i + 1];
                    break;
                default:
                    break;
            }
        }

        max = Math.max(max, res);
        min = Math.min(min, res);
    }

}
