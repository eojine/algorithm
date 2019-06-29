#include <iostream>

using namespace std;

int N, L, R;
long dp[101][101][101];

int main() {

    cin >> N >> L >> R;

    dp[1][1][1] = 1;

    for (int n = 2; n <= N; ++n)
        for (int l = 1; l <= L; ++l)
            for (int r = 1; r <= R; ++r)
                dp[n][l][r] = (dp[n - 1][l - 1][r] + dp[n - 1][l][r - 1] + dp[n - 1][l][r] * (n - 2)) % 1000000007;

    printf("%ld", dp[N][L][R]);
    return 0;
}
