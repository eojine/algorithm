#include<iostream>
#include<algorithm>
#include<queue>

using namespace std;
int n, m, v;
int dot[1001][1001];
queue<int> bfsQ;
bool bfsVisit[1001];
bool dfsVisit[1001];

void dfs(int n, int v) {
    dfsVisit[v] = true;
    printf("%d ", v);

    for (int i = 1; i <= n; ++i) {
        if (!dfsVisit[i] && dot[v][i] == 1) {
            dfsVisit[i] = true;
            dfs(n, i);
        }
    }
}

void bfs(int n, int v) {

    bfsQ.push(v);
    bfsVisit[v] = true;
    printf("%d ", v);

    while (!bfsQ.empty()) {
        int front = bfsQ.front();
        bfsQ.pop();

        for (int i = 1; i <= n; ++i) {
            if (!bfsVisit[i] && dot[front][i] == 1) {
                bfsVisit[i] = true;
                bfsQ.push(i);
                printf("%d ", i);
            }
        }
    }
}

int main() {

    scanf("%d %d %d", &n, &m, &v);

    for (int i = 1; i <= m; ++i) {
        int x, y;
        scanf("%d %d", &x, &y);
        dot[x][y] = dot[y][x] = 1;
    }

    dfs(n, v);
    printf("\n");
    bfs(n, v);

    return 0;
}
