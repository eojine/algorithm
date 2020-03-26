import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17143_낚시왕 {
    static int R, C, M, sum;
    static ArrayList<Info> sharks;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // 위, 아래, 오른쪽, 왼쪽
    static Info[][] map;

    static class Info {
        int r, c, speed, dir, size;

        public Info(int r, int c, int speed, int dir, int size) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sharks = new ArrayList<>();
        map = new Info[R + 1][C + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()) - 1; // dir 배열은 0부터 시작
            int size = Integer.parseInt(st.nextToken());

            Info shark = new Info(r, c, speed, dir, size);
            sharks.add(shark);
            map[r][c] = shark;
        }

        for (int time = 1; time <= C; time++) {
            catchShark(time);
            moveShark();
        }
        System.out.println(sum);
    }

    private static void catchShark(int time) {
        Info target = null;
        for (int i = 1; i <= R; i++) {
            if (map[i][time] != null) {
                target = map[i][time];
                break;
            }
        }
        if (target != null) {
            map[target.r][target.c] = null;
            sum += target.size;
            sharks.remove(target);
        }
    }

    private static void moveShark() {
        // speed만큼 모든 상어가 움직임
        for (Info shark : sharks) {
            int r = shark.r, c = shark.c, speed = shark.speed, dir = shark.dir;

            while (speed > 0) {
                int nr = r + dirs[dir][0];
                int nc = c + dirs[dir][1];

                if (!inRange(nr, nc)) {
                    switch (dir) {
                        case 0: dir = 1; break;
                        case 1: dir = 0; break;
                        case 2: dir = 3; break;
                        case 3: dir = 2; break;
                    }
                    continue;
                }
                r = nr;
                c = nc;
                speed--;
            }

            if (shark.speed != 0) {
                shark.r = r;
                shark.c = c;
                shark.dir = dir;
            }
        }
        
        survive();
    }

    static void survive() {
        // 겹치는 상어가 있는지 확인( 각 좌표가 같은 상어가 있는지 확인해서 사이즈 큰것만 남김)
        map = new Info[R + 1][C + 1];
        int size = sharks.size();
        for (int i = size - 1; i >= 0; i--) {
            Info shark = sharks.get(i);
            if (map[shark.r][shark.c] == null) {
                map[shark.r][shark.c] = shark;
            } else {
                if (map[shark.r][shark.c].size > shark.size) {
                    sharks.remove(shark);
                } else {
                    sharks.remove(map[shark.r][shark.c]);
                    map[shark.r][shark.c] = shark;
                }
            }
        }
    }

    private static boolean inRange(int r, int c) {
        return r >= 1 && r <= R && c >= 1 && c <= C;
    }
}
