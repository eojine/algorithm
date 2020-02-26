import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_3190_뱀 {

    public static int N, res;
    public static int dirIndex = 0; // 오른쪽이기 때문에 0으로 초기화함.
    public static int currentN = 0, currentM = 0;
    public static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우, 하, 좌, 상
    public static int[][] map;
    public static LinkedList<Info> turn = new LinkedList<>();
    public static LinkedList<Dot> tails = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int appleCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < appleCnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken()) - 1;

            map[n][m] = 1; // 사과의 위치는 1로 표시
        }

        int timeCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < timeCnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);

            turn.add(new Info(t, d));
        }

        // turn list에서 아무것도 뽑지 않았을 때 초기화
        char turnDir = '0';
        int turnTime = 0;

        map[0][0] = 2; // 처음시작하는 뱀!

        while (true) {
            // 시간증가
            res++;

            // 다음 N, M을 초기화해줌
            int nextN = currentN + dir[dirIndex][0];
            int nextM = currentM + dir[dirIndex][1];

            // 명령이 남아있지 않을때만 명령을 업데이트함.
            if (!turn.isEmpty()) {
                turnTime = turn.peek().time;
                turnDir = turn.peek().dir;
            }

            // 만약 현재 초가 명령내린 초와 같다면
            if (res == turnTime) {
                // dir 배열을 사용해서 그 안의 index값을 넣어주기위한 계산
                if (turnDir == 'L')
                    dirIndex = (dirIndex + 3) % 4;
                else
                    dirIndex = (dirIndex + 1) % 4;

                // 사용한 turn을 뽑아준다.
                turn.poll();
            }

            // 범위에 벗어나거나 1(사과) 이상의 수(뱀)라면 종료!
            if (!inRange(nextN, nextM) || map[nextN][nextM] > 1) break;

            // 꼬리는 무조건 현재위치로 add
            tails.add(new Dot(currentN, currentM));

            // 사과라면 그냥 머리만 뱀으로 칠해줌.
            if (map[nextN][nextM] == 1) {
                map[nextN][nextM] = 2;
            } else {
                // 사과가 아니라면 머리를 칠해주고 꼬리를 지워줌.
                map[nextN][nextM] = 2;
                Dot tail = tails.poll();
                map[tail.n][tail.m] = 0;
            }

            // 다음턴에 사용할 current들을 업데이트해줌.
            currentN = nextN;
            currentM = nextM;
        }

        System.out.println(res);

    }

    public static boolean inRange(int n, int m) {
        return n >= 0 && m >= 0 && n < N && m < N;
    }

    public static class Info {
        int time;
        char dir;

        public Info(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }

    public static class Dot {
        int n;
        int m;

        public Dot(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}
