#include <iostream>
#include <queue>
#include <vector>

using namespace std;

#define MAX_NUM 8

struct virus {
    int x;
    int y;
};

int N, M;
int map[MAX_NUM][MAX_NUM];
int tempMap[MAX_NUM][MAX_NUM];
int dx[] = {0, 1, 0, -1};
int dy[] = {1, 0, -1, 0};
int ans = 0;
queue<virus> q;
vector<virus> v;

bool inRange(int x, int y) {
    return x >= 0 && y >= 0 && x < N && y < M;
}

void copyMap(int(*a)[MAX_NUM], int(*b)[MAX_NUM]) {
    for (int i = 0; i < N; i++)
        for (int j = 0; j < M; j++)
            a[i][j] = b[i][j];
}

void bfsVirus() {

    int afterMap[MAX_NUM][MAX_NUM];
    copyMap(afterMap, tempMap);

    for (int k = 0; k < v.size(); ++k) {
        q.push({v[k].x, v[k].y});
    }

    while (!q.empty()) {
        int x = q.front().x;
        int y = q.front().y;

        q.pop();

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (inRange(nx, ny)) {
                if (afterMap[nx][ny] == 0) {
                    afterMap[nx][ny] = 2;
                    q.push({nx, ny});
                }
            }
        }
    }


    // 안전영역의 크기를 구한다.
    int safetySize = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (afterMap[i][j] == 0)
                safetySize++;
        }
    }

    ans = max(ans, safetySize);
}

void recurWall(int cnt) {
    if (cnt == 3) {
        bfsVirus();
        return;
    }

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            if (tempMap[i][j] == 0) {
                tempMap[i][j] = 1;
                recurWall(cnt + 1);
                tempMap[i][j] = 0;
            }
        }
    }
}

int main() {

    scanf("%d %d", &N, &M);

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            scanf("%d", &map[i][j]);
            if (map[i][j] == 2)
                v.push_back({i, j});
        }
    }

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            if (map[i][j] == 0) {
                copyMap(tempMap, map);
                tempMap[i][j] = 1;
                recurWall(1);
                tempMap[i][j] = 0;
            }
        }
    }
    printf("%d", ans);

    return 0;
}
