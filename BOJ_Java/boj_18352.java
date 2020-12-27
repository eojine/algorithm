import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

public class Main {

    static int cityCnt, roadCnt, distance, start;
    static ArrayList<Integer> result = new ArrayList<>();
    static ArrayList<Integer>[] cities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader("4 5 3 1\n" +
                "1 2\n" +
                "1 3\n" +
                "2 3\n" +
                "2 4\n" +
                "4 1"));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        cityCnt = Integer.parseInt(st.nextToken());
        roadCnt = Integer.parseInt(st.nextToken());
        distance = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken()) - 1;

        cities = new ArrayList[cityCnt];
        for (int i = 0; i < cityCnt; i++) {
            cities[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < roadCnt; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int city1 = Integer.parseInt(st.nextToken()) - 1;
            int city2 = Integer.parseInt(st.nextToken()) - 1;

            cities[city1].add(city2);
        }

        BFS();

        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            Collections.sort(result);
            for(int res: result) {
                System.out.println(res + 1);
            }
        }
    }

    public static void BFS() {
        boolean[] visit = new boolean[cityCnt];
        Queue<City> q = new LinkedList<>();
        
        q.add(new City(start, 0));
        
        while(!q.isEmpty()) {
            City now = q.poll();
            
            if (now.dist == distance && now.city != start) {
                result.add(now.city);
                continue;
            }

            for(int next: cities[now.city]) {
                if (!visit[next]) {
                    q.add(new City(next, now.dist + 1));
                    visit[next] = true;
                }
            }
        }
    }

    public static class City {
        int city;
        int dist;

        public City(int num, int count) {
            this.city = num;
            this.dist = count;
        }
    }

}
