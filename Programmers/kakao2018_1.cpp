#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

struct stage {
    int stage;
    double fail;
};

bool cmp(const stage &x, const stage &y) {
    if (x.fail == y.fail) {
        return x.stage < y.stage;
    } else if (x.fail > y.fail) {
        return true;
    } else {
        return false;
    }
}

vector<int> solution(int N, vector<int> stages) {
    vector<int> answer(N);
    vector<stage> st(N + 1);
    int playerNum = stages.size();

    for (int i = 1; i < N + 1; ++i) {
        int oneStop = 0;
        int allStop = 0;
        for (int j = 0; j < playerNum; ++j) {
            if (stages[j] >= i) {
                allStop++;
            }
            if (stages[j] == i) {
                oneStop++;
            }
        }
        st[i] = {i, (double) oneStop / (double) allStop};
    }

    cout << endl;
    sort(st.begin() + 1, st.end(), cmp);

    for (int i = 0; i < N; ++i) {
        answer[i] = st[i + 1].stage;
        cout << st[i + 1].stage << " ";
    }
    return answer;
}
