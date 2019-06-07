#include <string>
#include <vector>
#include <algorithm>

using namespace std;
bool visit[200];

void dfs(int start, vector<vector<int>> &computers, int n) {
    visit[start] = 1;
    for (int i = 0; i < n; i++) {
        if (!visit[i] && computers[start][i])
            dfs(i, computers, n);
    }
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    for (int i = 0; i < n; i++) {
        if (!visit[i]) {
            answer++;
            dfs(i, computers, n);
        }
    }
    return answer;
}
