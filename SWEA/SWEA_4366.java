import java.io.StringReader;
import java.util.*;

public class SWEA_4366 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc = new Scanner(new StringReader("1\n" +
                "1010\n" +
                "212"));

        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            char[] num2 = sc.next().toCharArray();
            char[] num3 = sc.next().toCharArray();
            HashSet<Integer> hs = new HashSet<>();
            int result = 0;

            for (int i = 0; i < num2.length; i++) {
                for (int j = 0; j < 2; j++) {
                    if (num2[i] == (char)(j + '0')) continue;
                    char x = num2[i];
                    num2[i] = (char)(j + '0');
                    String s = "";
                    for (int k = 0; k < num2.length; k++) s += num2[k];
                    int c = Integer.parseInt(s, 2);
                    num2[i] = x;
                    hs.add(c);
                }
            }

            for (int i = 0; i < num3.length; i++) {
                for (int j = 0; j < 3; j++) {
                    if (num3[i] == (char)(j + '0')) continue;
                    char x = num3[i];
                    num3[i] = (char)(j + '0');
                    String s = "";
                    for (int k = 0; k < num3.length; k++) s += num3[k];
                    int c = Integer.parseInt(s, 3);
                    num3[i] = x;
                    if (hs.contains(c)) {
                        result = c;
                        break;
                    }
                }
            } // end of for
            System.out.println("#" + tc + " " + result);
        } // end of TC
    } // end of main
} // end of class
