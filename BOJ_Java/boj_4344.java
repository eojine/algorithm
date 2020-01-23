import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();

        for (int tc = 1; tc <= TC; tc++) {
            int stuNum = sc.nextInt();
            double[] stuArr = new double[stuNum];
            double sum = 0;
            for (int i = 0; i < stuNum; i++) {
                stuArr[i] = sc.nextInt();
                sum += stuArr[i];
            }

            double ave = sum/stuNum;

            double upperCnt = 0;
            for (int i = 0; i < stuNum; i++) {
                if (ave < stuArr[i])
                    upperCnt++;
            }

            System.out.printf("%.3f" , upperCnt/stuNum * 100);
            System.out.println("%");
        }
    }
}
