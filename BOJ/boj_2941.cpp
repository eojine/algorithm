#include <string>
#include <iostream>

using namespace std;

int main() {
    string s;
    cin >> s;

    int cnt = s.length();
    int sLen = s.length();

    for (int i = 0; i < sLen - 1; ++i) {
        if (s.substr(i, 2) == "z=") {
            if (s.substr(i - 1, 1) == "d")
                cnt -= 2;
            else
                cnt -= 1;
        }
        if (s.substr(i, 2) == "c=")
            cnt -= 1;
        if (s.substr(i, 2) == "c-")
            cnt -= 1;
        if (s.substr(i, 2) == "d-")
            cnt -= 1;
        if (s.substr(i, 2) == "lj")
            cnt -= 1;
        if (s.substr(i, 2) == "nj")
            cnt -= 1;
        if (s.substr(i, 2) == "s=")
            cnt -= 1;
    }

    printf("%d", cnt);

    return 0;
}
