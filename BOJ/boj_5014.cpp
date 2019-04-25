#include <iostream>
#include <queue>

using namespace std;
bool check[1000001];
int pushBtn[1000001];
int F, S, G, U, D;

int main() {
    scanf("%d %d %d %d %d", &F, &S, &G, &U, &D);
    queue<int> q;

    q.push(S);
    check[S] = true;

    while (!q.empty()) {
        int current = q.front();
        q.pop();

        if (current + U <= F && !check[current + U]){
            pushBtn[current + U] = pushBtn[current] + 1;
            check[current + U] = true;
            q.push(current + U);
        }

        if (current - D >= 1 && !check[current - D]){
            pushBtn[current - D] = pushBtn[current] + 1;
            check[current - D] = true;
            q.push(current - D);
        }
    }

    if ( check[G] )
        printf("%d", pushBtn[G]);
    else
        printf("use the stairs");

    return 0;
}
