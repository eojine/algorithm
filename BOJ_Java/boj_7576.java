import java.util.LinkedList;
import java.util.Scanner;

public class BOJ_7576 {

    static class Info {
        int n, m;

        public Info(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        int N = sc.nextInt();
        int[][] box = new int[N][M];
        boolean[][] visit = new boolean[N][M];
        int[] dn = {-1, 0, 1, 0};
        int[] dm = {0, 1, 0, -1};
        LinkedList<Info> q = new LinkedList<>();
        boolean isZero = false;
        int res = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                box[i][j] = sc.nextInt();
                if (box[i][j] == 0) {
                    isZero = true;
                }
                if (box[i][j] == 1) {
                    q.add(new Info(i, j));
                }
            }
        }

        if(!isZero) {
            System.out.println(0);
            return;
        }

        while(!q.isEmpty()) {

            int firstN = q.peek().n;
            int firstM = q.peek().m;
            q.poll();


            for (int i = 0; i < 4; i++) {
                int nextN = firstN + dn[i];
                int nextM = firstM + dm[i];


                if(nextM >= M || nextN >= N || nextN < 0 || nextM < 0) {
                    continue;
                }

                if(box[nextN][nextM] != 0 || visit[nextN][nextM]) {
                    continue;
                }

                box[nextN][nextM] = box[firstN][firstM] + 1;
                visit[nextN][nextM] = true;
                q.add(new Info(nextN, nextM));
            }

            res = box[firstN][firstM] - 1;
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(box[i][j] == 0 ){
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(res);
    }
}
