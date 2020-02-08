package SSAFY_BOJ;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] onOff = new int[N];

        for (int i = 0; i < onOff.length; i++) {
            onOff[i] = sc.nextInt();
        }

        int stuNum = sc.nextInt();

        for (int i = 0; i < stuNum; i++) {
            int s = sc.nextInt();
            int sIdx = sc.nextInt();

            if (s == 1) {
                for (int j = 0; j < onOff.length; j++) {
                    if ((j + 1) % sIdx == 0) {
                        onOff = switchOnOff(onOff, j);
                    }
                }

            } else if (s == 2) {
                int newIdx = sIdx - 1;
                int cnt = 0;

                onOff = switchOnOff(onOff, newIdx);

                for (int j = newIdx; j < onOff.length; j++) {
                    cnt++;

                    if (newIdx + cnt >= onOff.length || newIdx - cnt < 0) {
                        break;
                    }

                    if (onOff[newIdx + cnt] == onOff[newIdx - cnt]) {
                        onOff = switchOnOff(onOff, newIdx + cnt);
                        onOff = switchOnOff(onOff, newIdx - cnt);
                    } else {
                        break;
                    }

                }
            }
        }

        for (int j = 0; j < onOff.length; j++) {
            System.out.print(onOff[j] + " ");
            if ((j + 1) % 20 == 0)
                System.out.println();
        }
    }

    public static int[] switchOnOff(int[] a, int idx) {
        if (a[idx] == 0)
            a[idx] = 1;
        else
            a[idx] = 0;
        return a;
    }
}
