#include <string>
#include <vector>

using namespace std;

vector<string> solution(int n, vector<int> arr1, vector<int> arr2) {
    string temp;
    vector<string> answer;

    for (int i = 0; i < n; ++i) {
        for (int j = 1; j < 1<<n; j = j << 1) {
            if ((arr1[i] & j) | (arr2[i] & j)) {
                temp = "#" + temp;
            } else {
                temp = " " + temp;
            }
        }
        answer.push_back(temp);
        temp = "";
    }
    return answer;
}
