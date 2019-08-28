#include <string>
#include <vector>
#include <queue>
#include <map>

using namespace std;

struct User {
    string status;
    string userId;
};

vector<string> solution(vector<string> record) {
    vector<string> answer;
    queue<User> q;
    map<string, string> m;

    for (int i = 0; i < record.size(); ++i) {
        string tmp;
        vector<string> v;

        for (int j = 0; j < record[i].size(); ++j) {
            if (record[i][j] == ' ') {
                v.push_back(tmp);
                tmp.clear();
            } else {
                tmp.push_back(record[i][j]);
            }
        }
        v.push_back(tmp);

        if (v[0] == "Change") {
            m[v[1]] = v[2];
        } else if (v[0] == "Leave") {
            q.push({v[0], v[1]});
        } else {
            q.push({v[0], v[1]});
            m[v[1]] = v[2];
        }
    }

    while (!q.empty()) {
        string x = q.front().status;
        string y = q.front().userId;

        string result;

        q.pop();

        if (x == "Enter") {
            result = m[y] + "님이 들어왔습니다.";
        } else {
            result = m[y] + "님이 나갔습니다.";
        }
        answer.push_back(result);
    }
    return answer;
}
