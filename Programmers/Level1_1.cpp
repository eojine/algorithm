#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> answers) {
    vector<int> a = { 1, 2, 3, 4, 5 };
    vector<int> b = { 2, 1, 2, 3, 2, 4, 2, 5 };
    vector<int> c = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
    int cntA = 0, cntB = 0, cntC = 0;
    vector<int> result;
    int len = answers.size();
    
    for (int i = 0; i < len; ++i) {
        int tempA = a[i%5];
        int tempB = b[i%8];
        int tempC = c[i%10];
        
        if (tempA == answers[i])
            cntA++;
        if (tempB == answers[i])
            cntB++;
        if (tempC == answers[i])
            cntC++;
    }
    
    int maxCount = max(cntA, max(cntB, cntC));
    if(maxCount == cntA)
        result.push_back(1);
    if(maxCount == cntB)
        result.push_back(2);
    if(maxCount == cntC)
        result.push_back(3);
    
    return result;
}
