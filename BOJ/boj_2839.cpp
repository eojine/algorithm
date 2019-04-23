#include <cstdio>

int main() {
    int num, ans = 0, min = 0;
    scanf("%d", &num);

    while (true) {
        if (num % 5 == 0) {
            ans += (num / 5);
            break;
        }
        num -= 3;
        ans++;

        if (num < 0) {
            ans = -1;
            break;
        }
    }

    printf("%d", ans);

    return 0;
}
