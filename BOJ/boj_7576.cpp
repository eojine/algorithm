#include <cstdio>
#include <iostream>
#include <queue>

using namespace std;

struct tomato {
    int x;
    int y;
};

int n, m, result, arr[1001][1001];
bool visit[1001][1001];
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, 1, 0, -1};
queue<tomato> q;

bool inRange(int x, int y) {
    return x >= 0 && x < n && y >= 0 && y < m;
}

void bfs() {
    while (!q.empty()) {
        int x = q.front().x;
        int y = q.front().y;
        q.pop();

        for (int i = 0; i < 4; ++i) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (!visit[nextX][nextY] && inRange(nextX, nextY) && !arr[nextX][nextY] ) {
                arr[nextX][nextY] = arr[x][y] + 1;
                visit[nextX][nextY] = true;
                q.push({nextX, nextY});
            }
        }
    }
}

int main() {

    scanf("%d %d", &n, &m);

    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            scanf("%d", &arr[j][i]);
            if (arr[j][i] == 1)
                q.push({j, i});
        }
    }

    bfs();

    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            if (arr[j][i] == 0) {
                printf("-1");
                return 0;
            }
            if (result < arr[j][i])
                result = arr[j][i];
        }
    }
    printf("%d", result-1);
    return 0;
}
