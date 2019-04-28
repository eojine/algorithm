#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int tc, aptCnt = 0, comCnt = 0;
int apt[25][25];
bool check[25][25];
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, 1, 0, -1};
vector<int> v;

void dfs(int x, int y) {
    for (int i = 0; i < 4; ++i) {
        int nextX = dx[i] + x;
        int nextY = dy[i] + y;

        if (!check[nextX][nextY] && apt[nextX][nextY] == 1 && nextX >= 0 && nextX < tc && nextY >= 0 && nextY < tc) {
            check[nextX][nextY] = true;
            aptCnt++;
            dfs(nextX, nextY);
        }
    }

}

int main() {

    scanf("%d", &tc);

    for (int i = 0; i < tc; ++i)
        for (int j = 0; j < tc; ++j)
            scanf("%1d", &apt[i][j]);

    for (int i = 0; i < tc; ++i) {
        for (int j = 0; j < tc; ++j) {
            if (!check[i][j] && apt[i][j] == 1) {
                check[i][j] = true;
                comCnt++;
                aptCnt++;
                dfs(i, j);
                v.push_back(aptCnt);
                aptCnt = 0;
            }
        }
    }

    sort(v.begin(), v.end());
    int size = v.size();
    printf("%d\n", comCnt);

    for (int i = 0; i < size; ++i)
        printf("%d\n", v[i]);

    return 0;
}
