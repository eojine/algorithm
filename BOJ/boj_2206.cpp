#include <iostream>
#include <queue>

using namespace std;

struct map {
    int x, y, w;
};

int N, M;
int arr[1000][1000];
int temp[1000][1000][2];
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};
queue<map> q;

bool inRange(int x, int y) {
    return x >= 0 && y >= 0 && x < N && y < M;
}

int main() {

    cin >> N >> M;

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j)
            scanf("%1d", &arr[i][j]);
    }

    q.push({0, 0, 0});
    temp[0][0][0] = 1;

    while (!q.empty()) {

        int x = q.front().x;
        int y = q.front().y;
        int wall = q.front().w;

        q.pop();

        if (x == N - 1 && y == M - 1) {
            cout << temp[x][y][wall];
            return 0;
        }

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int w = wall;

            if (inRange(nx, ny) && temp[nx][ny][w] == 0) {

                if (arr[nx][ny] == 0) {
                    temp[nx][ny][w] = temp[x][y][w] + 1;
                    q.push({nx, ny, w});
                }

                if (arr[nx][ny] == 1 && w == 0) {
                    temp[nx][ny][1] = temp[x][y][w] + 1;
                    q.push({nx, ny, 1});
                }
            }

        }
    }

    cout << "-1";

    return 0;
}
