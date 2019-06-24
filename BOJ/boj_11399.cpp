#include <iostream>
#include <algorithm>

using namespace std;

int n;
int p[1000];

int main(void) {
    scanf("%d", &n);
    for (int i = 0; i < n; ++i)
        scanf("%d", &p[i]);
    sort(p, p + n);

    int sum = 0;
    for (int i = 0; i < n; ++i)
        for (int j = 0; j <= i; ++j)
            sum += p[j];

    printf("%d", sum);

    return 0;
}
