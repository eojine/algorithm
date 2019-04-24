#include <iostream>
#include <string>

using namespace std;

int main() {
    string x, input;
    int testcase, starLoc;

    scanf("%d\n", &testcase);
    getline(cin, x);
    int xLen = x.length();

    for (int i = 0; i < xLen; ++i) {
        if (x.substr(i, 1) == "*") {
            starLoc = i;
        }
    }
    for (int i = 0; i < testcase; ++i) {
        getline(cin, input);
        int inputLen = input.length();
        if (inputLen < xLen - 1) {
            printf("NE\n");
        } else {
            if (x.substr(0, starLoc) == input.substr(0, starLoc)
                && x.substr(starLoc + 1, xLen - 1) ==
                   input.substr(input.length() - x.length() + starLoc + 1, input.length())) {
                printf("DA\n");
            } else {
                printf("NE\n");
            }
        }
    }
    return 0;
}
