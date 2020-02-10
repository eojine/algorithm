
import java.util.Arrays;
import java.util.Scanner;

public class JO_1828_냉장고 {

    public static class Info implements Comparable<Info> {
        int low, high;

        public Info(int low, int high) {
            this.low = low;
            this.high = high;
        }

        @Override
        public int compareTo(Info o) {
            if (this.low > o.low) { // 가장 낮은 온도순으로 정렬
                return 1;
            } else
                return -1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        Info[] arr = new Info[N];


        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            arr[i] = new Info(x, y);
        }

        Arrays.sort(arr);

        int high = arr[0].high;

        int cnt = 1;
        for (int i = 1; i < arr.length; i++) {

            if (arr[i].high < high)
                high = arr[i].high;

            if (high < arr[i].low) {
                cnt++;
                high = arr[i].high;
            }
        }
        System.out.println(cnt);
    }
}

/* 음 최저 온도로 정렬 했을 때,
A : |----------|
B :    |----------|
C :     |-----|
D :            |----|
뒤에 나오는 화학 약품이 앞에 나온 화학 약품보다 온도가 낮을 일이 없으니깐 이젠 최고 온도만 보면 돼
부분해를 가지고 전체 해를 예상해보면,
A는 일단 첫 시작이니 그냥 두고,
B는 A보다 최고 온도가 더 높으니 B의 최저 온도에서 ~ A의 최고 온도 사이에 보관할 수 있고
C는 A보다 최고 온도가 낮으니 C의 최저 온도에서 C의 최고 온도 사이에 보관 할 수 있어.
 (이 때 B는 C의 최고 온도보다 보관 할 수 있는 최고 온도보다 높기 때문에
 무조건, C의 최저 온도와 C의 최고 온도 사이에서 보관 할 수 있어)
문제는 그 다음인데, D 같은 경우는 A의 최고 온도 보다 낮은 온도에서 보관할 수는 있으나 C와 D는 같이 보관 할 수 없어.
따라서, 문제 풀 때, 최고 온도를 가능한 가장 낮은 온도로 계속 수정해가면서
 최저 온도가 수정된 최고 온도보다 낮으면 함께 보관할 수 있다는 가정을 내려서 전체에 적용하면 돼
 */
