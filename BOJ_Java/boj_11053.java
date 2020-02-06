import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int num = sc.nextInt();
		int[] a = new int[num];
		int[] d = new int[num];

		for (int i = 0; i < num; i++) {
			a[i] = sc.nextInt();
		}

		
		int max = 0;
		for (int i = 0; i < num; i++) {
           		d[i] = 1;
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j] && d[i] <= d[j]) {
					d[i] = d[j] + 1;
				} 
			}
		}

		for (int i = 0; i < num; i++) {
			if (max < d[i])
				max = d[i];
		}
		System.out.println(max);
	}
}
