import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_15686_치킨배달 {
    static int N, M, result, map[][];
    static List<Point> home = new ArrayList<>();
    static List<Point> chicken = new ArrayList<>();
    static List<Point> tmpChicken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) chicken.add(new Point(i, j));
                if (map[i][j] == 1) home.add(new Point(i, j));
            }
        }
        makeChickenList();
        System.out.println(result);
    }

    private static void makeChickenList() {

        result = Integer.MAX_VALUE;

        for (int i = 0; i < 1 << chicken.size(); i++) {
            if (Integer.bitCount(i) == M) {
                tmpChicken = cloneList();

                for (int j = 0; j < chicken.size(); j++)
                    if ((i & (1 << j)) == 0)
                        tmpChicken.remove(chicken.get(j));

                int sum = 0;
                for (Point c : home) {
                    int min = Integer.MAX_VALUE;
                    for (Point h : tmpChicken)
                        min = Integer.min(distance(c, h), min);
                    sum += min;
                }
                result = Integer.min(sum, result);
            }
        }
    }

    static List<Point> cloneList() {
        List<Point> tmp = new ArrayList<>();
        for (int i = 0; i < chicken.size(); i++)
            tmp.add(chicken.get(i));
        return tmp;
    }

    static int distance(Point p1, Point p2) {
        return Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c);
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
