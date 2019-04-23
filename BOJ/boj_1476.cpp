#include <cstdio>
int e, s, m;
int year = 0;
int main() {
    scanf("%d %d %d", &e, &s, &m);
    while(true) {
        year += 1;
        if ((year-e)%15 == 0 && (year-s)%28 == 0 && (year-m)%19 == 0)
            break;
    }
    printf("%d", year);
    return 0;
}
