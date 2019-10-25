#include <iostream>
#include <string>
#include <vector>

using namespace std;

string solution(string p) {
    string answer, u, v, ansTmp;
    int st = 0;
    bool isRight = true;

    if (p == "") return answer;

    //u, v 나눔
    for (int i = 0; i < p.length(); ++i) {

        if (p[i] == '(') st++;
        else if (p[i] == ')') st--;

        if (st < 0) isRight = false;

        if (st == 0) {
            u = p.substr(0, i + 1);
            v = p.substr(i + 1, p.length());
            break;
        }
    }

    if (isRight) {
        answer = answer + u + solution(v);
    } else {
        ansTmp += '(' + solution(v) + ')';
        for (int i = 1; i < u.size() - 1; i++) {
            if (u[i] == ')')
                ansTmp += '(';
            else
                ansTmp += ')';
        }
        answer = answer + ansTmp;
    }
    return answer;
}
