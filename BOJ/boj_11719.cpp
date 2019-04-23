#include <iostream>
#include <string>

using namespace std;

int main() {
    string input;
    for (int i = 0; i < 100; i++) {
        getline(cin, input);
        printf("%s\n", input.c_str());
    }
    return 0;
}
