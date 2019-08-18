#include <string>
#include <set>
#include <vector>
#include <algorithm>
#include <math.h>

using namespace std;

bool inRange(int x, int y) {
    return (x >= 97 && x <= 122) && (y >= 97 && y <= 122);
}

void LowerString(string &str) {
    for (int i = 0; i < str.length(); i++)
        str[i] = tolower(str[i]);
}

int solution(string str1, string str2) {
    int answer = 0;
    multiset<string> s1; //중복 허용
    multiset<string> s2;
    vector<string> result; // 교집합, 합집합 넣을 벡터
    int unionCnt, interCnt;
    LowerString(str1);
    LowerString(str2);

    for (int i = 0; i < str1.length() - 1; ++i) {
        if (!inRange(str1[i], str1[i + 1])) {
            continue;
        }
        s1.insert(str1.substr(i, 2));
    }

    for (int i = 0; i < str2.length() - 1; ++i) {
        if (!inRange(str2[i], str2[i + 1])) {
            continue;
        }
        s2.insert(str2.substr(i, 2));
    }

    // 합집합
    result.resize(s1.size() + s2.size());
    auto itr = set_union(s1.begin(), s1.end(), s2.begin(), s2.end(), result.begin());
    result.erase(itr, result.end());
    unionCnt = result.size();

    //교집합
    result.clear();
    result.resize(s1.size() + s2.size());
    itr = set_intersection(s1.begin(), s1.end(), s2.begin(), s2.end(), result.begin());
    result.erase(itr, result.end());

    interCnt = result.size();

    if (interCnt == 0 && unionCnt == 0) {
        return answer = 65536;
    } else if (interCnt == 0) {
        return answer = 0;
    } else if (unionCnt == 0) {
        return answer = 65536;
    }

    answer = (int) floor(double(interCnt) / double(unionCnt) * 65536);

    return answer;
}
