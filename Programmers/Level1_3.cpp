#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;
    vector<int> newArr;

    for (int i = 0; i < commands.size(); ++i) {
        for (int j = commands[i][0]-1; j < commands[i][1]; j++)
            newArr.push_back(array[j]);
        sort(newArr.begin(), newArr.end());
        answer.push_back(newArr[commands[i][2]-1]);
        newArr.clear();
    }
    return answer;
}
