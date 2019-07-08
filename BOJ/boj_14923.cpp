#include <iostream>
#include <string.h>
#include <queue>

using namespace std;

struct maze {
    int x, y, z;
};

int n, m, hx, hy, ex, ey;
int a[1000][1000];
int dist[1000][1000][2];
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, 1, -1};

void bfs() {

    queue<maze> q;

    q.push({hx - 1, hy - 1, 0});
    dist[hx - 1][hy - 1][0] = 0;

    while (!q.empty()) {

        int x = q.front().x;
        int y = q.front().y;
        int z = q.front().z;

        q.pop();

        if (x == ex - 1 && y == ey - 1) {
            cout << dist[x][y][z] << endl;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int nz = z;

            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                continue;

            if (a[nx][ny] == 1) {
                if (nz == 1)
                    continue;
                else
                    nz = 1;
            }

            if (dist[nx][ny][nz] == -1) {
                q.push({nx, ny, nz});
                dist[nx][ny][nz] = dist[x][y][z] + 1;
            }
        }
    }
    cout << "-1" << endl;
}

int main() {
    ios::sync_with_stdio(false);
    memset(dist, -1, sizeof(dist));

    cin >> n >> m >> hx >> hy >> ex >> ey;

    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            cin >> a[i][j];

    bfs();

    return 0;
}
