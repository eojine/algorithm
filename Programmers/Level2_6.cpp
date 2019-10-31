#include <string>
#include <vector>

using namespace std;

vector<int> solution(int brown, int red) {
    vector<int> answer;
    int allTiles = brown + red;

    for (int i = 3; i < allTiles; i++) {
        if (allTiles % i == 0) {
            int j = allTiles / i;
            if ((((2 * i) + (2 * j) - 4) == brown) && allTiles - brown == red) {
                answer.push_back(j);
                answer.push_back(i);
                break;
            }
        }
    }
    return answer;
}
