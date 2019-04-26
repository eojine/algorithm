#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int n;
int k[13];
bool check[13];

int main() {

    while (true) {

        scanf("%d", &n);
        
        if (n == 0)
            break;
        
        for (int i = 0; i < n; ++i)
            scanf("%d", &k[i]);

        for (int i = 0; i < 6; i++)
            check[i] = true;

        do {
            for (int i = 0; i < n; i++)
                if (check[i])
                    printf("%d ", k[i]);
            printf("\n");
        } while (prev_permutation(check, check + n));
        
        printf("\n");
    }

    return 0;
}
