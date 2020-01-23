import java.util.Scanner;

public class Day1_1110 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();

        for (int tc = 0; tc < TC; tc++) {

            String s = sc.next() + "X";
            int result = 0;
            int idx = 0;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'O') {
                    idx++;
                    result += idx;
                } else {
                    idx = 0;
                }
            }
            
            System.out.println(result);
        }
    }
}
