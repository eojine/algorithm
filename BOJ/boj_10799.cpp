#include <string>
#include <vector>
#include <iostream>
#include <stack>

using namespace std;

int main() {
    stack<int> st;
    string str;
    int answer = 0;
    cin >> str;

    for (int i = 0; i < str.size(); ++i) {
        if (str[i] == '(')
            st.push(str[i]);
        else {
            st.pop();
            if (str[i - 1] == '(')
                answer += st.size();
            else
                answer++;
        }
    }

    printf("%d", answer);

    return 0;
}
