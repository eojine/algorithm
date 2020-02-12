import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            int N = sc.nextInt();
            
            if(N == 0)
                return;

            int[] arr = new int[N];
            int[] temp = new int[6];

            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            lotto(0, 0, arr, temp);
            System.out.println();
        }
    }

    public static void lotto(int cnt, int start, int[] arr, int[] temp) {
        if (cnt == 6) {
            for (int i = 0; i < 6; i++) {
                System.out.print(temp[i] + " ");
            }
            System.out.println();

            return;
        }

        for (int i = start; i < arr.length; i++) {
            temp[cnt] = arr[i];
            lotto(cnt + 1, i + 1, arr, temp);
        }
    }
}
