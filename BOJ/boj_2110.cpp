#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {

    vector<long long> v;
    long long left = 1, right, d = 0, ans = 0;
    int N, C;

    cin >> N >> C;

    for (int i = 0; i < N; ++i) {
        long long x;
        cin >> x;
        v.push_back(x);
    }

    sort(v.begin(), v.end());

    right = v.back() - v.front();

    while (left <= right) {
        long long mid = (left + right) / 2;
        long long start = v.front();
        int cnt = 1;

        for (int i = 1; i < N; i++) {
            d = v[i] - start;
            if (mid <= d) {
                ++cnt;
                start = v[i];
            }
        }

        if (cnt >= C) {
            ans = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    cout << ans;

    return 0;
}
