#include <cstdio>
#include <algorithm>
#include <vector>
using namespace std;
vector<int> v;
int result(int n) {
    int ans = 0;
    int num;
    for (int i = 0; i < n; ++i) {
        scanf("%d", &num);
        v.push_back(num);
    }
    sort(v.begin(), v.end());
    do {
        int sum = 0;
        for (int i = 0; i < n-1; ++i) {
            sum += abs(v[i] - v[i+1]);
            ans = max(sum, ans);
        }
    } while ( next_permutation(v.begin(), v.end()));
    return ans;
}

int main () {
    int n;
    scanf("%d", &n);
    printf("%d", result(n));
    return 0;
}
