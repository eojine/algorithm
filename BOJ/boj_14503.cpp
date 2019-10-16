#include <iostream>

using namespace std;

int map[51][51];
int N, M;
int startX, startY, startDir;
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, 1, 0, -1};
int x, y, nx, ny, dir;
int clean_cnt, turn_cnt;

bool inRange() {
    return nx >= 0 && ny >= 0 && nx < N && ny < M;
}

void cleanRoom() {

    x = startX;
    y = startY;
    dir = startDir;
    clean_cnt = 1;

    while (1) {
        map[x][y] = 2;
        dir = (dir - 1 + 4) % 4;
        turn_cnt++;

        nx = x + dx[dir];
        ny = y + dy[dir];

        if (inRange() && map[nx][ny] == 0) {
            clean_cnt++;
            x = nx;
            y = ny;
            turn_cnt = 0;
            
        } else if (turn_cnt >= 4) {
            if (dir == 0) {
                nx = x + dx[2];
                ny = y + dy[2];
            } else if (dir == 1) {
                nx = x + dx[3];
                ny = y + dy[3];
            } else if (dir == 2) {
                nx = x + dx[0];
                ny = y + dy[0];
            } else {
                nx = x + dx[1];
                ny = y + dy[1];
            }

            if (!inRange() || map[nx][ny] == 1) {
                break;
            } else {
                x = nx;
                y = ny;
                turn_cnt = 0;
            }
        }
    }
}

int main() {

    cin >> N >> M;
    cin >> startX >> startY >> startDir;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> map[i][j];
        }
    }

    cleanRoom();

    cout << clean_cnt;

    return 0;
}
