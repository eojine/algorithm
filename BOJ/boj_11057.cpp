#include <stdio.h>

int n;
int temp[10];
int sum = 0;

int main() {
    scanf("%d", &n);
    
    temp[0] = 1;
    
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < 10; ++j) {
            temp[j] = (temp[j] + temp[j - 1]) % 10007;
        }
    }

    for (int i = 0; i < 10; ++i) {
        sum += temp[i];
    }

    printf("%d", sum % 10007);

    return 0;
}
