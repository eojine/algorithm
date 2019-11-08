#include <algorithm>
#include <string>
#include <vector>

using namespace std;

bool solution(vector<string> phone_book) {
    bool answer = true;

    sort(phone_book.begin(), phone_book.end());

    for (int i = 0; i < phone_book.size() - 1; ++i) {
        string current = phone_book[i];
        string next = phone_book[i+1];

        if (current.size() > next.size() || current == next)
            continue;

        bool tmpAns = false;
        for (int j = 0; j < current.size(); ++j) {
            if (current[j] != next[j]) {
                tmpAns = true;
            }
        }
        if (answer)
            answer = tmpAns;
    }
    return answer;
}
