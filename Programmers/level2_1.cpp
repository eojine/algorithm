#include <iostream>
#include <vector>

using namespace std;

int solution(vector<vector<int> > land)
{
    int answer = 0;
    int height = land.size();
    int width = 4;
    int memo[100001][4] = {0,};

    for (int i = 0; i < width; ++i)
        memo[0][i] = land[0][i];

    for (int i = 1; i < height; ++i)
        for (int j = 0; j < width; ++j)
            for (int k = 0; k < width; ++k)
                if (j != k)
                    memo[i][j] = max(memo[i][j], memo[i - 1][k] + land[i][j]);


    for (int i = 0; i < width; ++i)
        answer = max(answer, memo[height - 1][i]);
    
    return answer;
}
