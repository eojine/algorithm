#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> triangle) {
    int answer = 0;

    vector<vector<int>> dp(triangle.size(), vector<int>(triangle.size()));

    //루트
    dp[0][0] = triangle[0][0];
    //왼쪽 시작
    dp[1][0] = dp[0][0] + triangle[1][0];
    //오른쪽 시작
    dp[1][1] = dp[0][0] + triangle[1][1];
    
    for (int i = 1; i < triangle.size(); ++i) {
        for (int j = 0; j < triangle[i].size(); ++j) {
            dp[i][j] = max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
        }
    }
    for (int k = 0; k < dp.size(); ++k) {
        answer = max(answer, dp[dp.size() - 1][k]);
    }

    return answer;
}
