#include <iostream>

using namespace std;

int n, vipCnt, vip[42];
int dp[41];
int temp = 1;

int main() {

    scanf("%d %d", &n, &vipCnt);

    dp[0] = 1;
    vip[0] = 0;
    vip[vipCnt + 1] = n;

    for (int i = 1; i <= vipCnt; ++i)
        scanf("%d", &vip[i]);

    for (int i = 1; i <= 3; ++i)
        dp[i] = i;

    for (int i = 4; i <= n; ++i)
        dp[i] = dp[i - 1] + dp[i - 2];

    for (int i = 1; i <= vipCnt + 1; ++i) {
        int x = 0;
        if (i != vipCnt + 1)
            x = vip[i] - vip[i - 1] - 1;
        else
            x = vip[i] - vip[i - 1];
        temp *= dp[x];
    }

    printf("%d", temp);
    return 0;
}
