import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int NUM = num;
        int res = 0;

        while (true) {
            int first = num / 10;
            int second = num % 10;
            int tmp = first + second;

            num = ((num % 10) * 10) + tmp % 10;
            res++;
            
            if (num == NUM) break;
        }

        System.out.println(res);
    }
}
