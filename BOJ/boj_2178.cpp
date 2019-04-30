#include <cstdio>
#include <iostream>
#include <queue>

using namespace std;

struct miro {
    int x;
    int y;
};

queue<miro> q;
int arr[101][101];
bool visit[101][101];
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};
int n, m, result=0;

bool inRange(int x, int y){
    return x < n && x >= 0 && y >= 0 && y < m;
}

void bfs(){

    visit[0][0] = true;
    q.push({0, 0});

    while(!q.empty()){
        int x = q.front().x;
        int y = q.front().y;
        q.pop();

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!visit[nx][ny] && inRange(nx, ny) && arr[nx][ny] == 1){
                visit[nx][ny] = true;
                arr[nx][ny] = arr[x][y] + 1;
                q.push({nx, ny});
            }
        }

    }
}

int main(){

    scanf("%d %d", &n ,&m);

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j)
            scanf("%1d", &arr[i][j]);
    }

    bfs();
    
    printf("%d ", arr[n-1][m-1]);
    return 0;
}
