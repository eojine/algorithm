#include <cstdio>
#include <queue>
using namespace std;

queue<int> q;
int visit[100001];
int n, k;
int cnt = 0;

int main() {
    scanf("%d %d", &n, &k);

    q.push(n);
    visit[n] = 1;

    while (!q.empty()) {
        int size = q.size();
        for (int i = 0; i < size; ++i) {
            int v = q.front();
            q.pop();

            if (v == k)
                printf("%d", cnt);
            if (v - 1 >= 0 && visit[v - 1] == 0) {
                q.push(v - 1);
                visit[v - 1] = 1;
            }
            if (v + 1 <= 100000 && visit[v + 1] == 0) {
                q.push(v + 1);
                visit[v + 1] = 1;
            }
            if (v * 2 <= 100000 && visit[v * 2] == 0) {
                q.push(v * 2);
                visit[v * 2] = 1;
            }
        }
        cnt++;
    }
    return 0;
}
