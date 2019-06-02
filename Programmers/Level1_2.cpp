#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
    int len = participant.size();
    sort(participant.begin(), participant.end());
    sort(completion.begin(), completion.end());
    
    for(int i; i < len; i++){
        if(participant[i] != completion[i]){
            answer = participant[i];
            return answer;
        }
    }
    answer = participant[len - 1];
    return answer;
}
