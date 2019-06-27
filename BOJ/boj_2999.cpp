#include <iostream>

using namespace std;

int main() {

    char str[100];
    char arr[100][100];
    int cnt = 0;
    int x = 0;
    int R = 1, C = 1;

    cin >> str;

    while (str[cnt++] != '\0')

    for (int r = 1; r <= cnt; ++r)
        for (int c = 1; c <= cnt; ++c)
            if (r <= c && r * c == cnt)
                R = r, C = c;

    for (int i = 0; i < C; ++i)
        for (int j = 0; j < R; ++j)
            arr[i][j] = str[x++];

    for (int i = 0; i < R; ++i)
        for (int j = 0; j < C; ++j)
            cout << arr[j][i];

    return 0;
}
