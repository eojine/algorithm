#include <string>
#include <vector>
#include <string.h>

using namespace std;

int solution(int cacheSize, vector<string> cities) {
    int answer = 0;

    vector<string> cache(cacheSize);

    if (cacheSize == 0) {
        answer = cities.size() * 5;
        return answer;
    }

    for (int i = 0; i < cities.size(); ++i) {
        bool contains = false;

        for (int j = 0; j < cacheSize; ++j) {
            if (!strcasecmp(cache[j].c_str(), cities[i].c_str())) {
                cache.erase(cache.begin() + j);
                cache.push_back(cities[i]);
                answer += 1;
                contains = true;
                break;
            } else {
                contains = false;
            }
        }

        if (!contains) {
            if (cacheSize > cache.size()) {
                cache.push_back(cities[i]);
            } else {
                cache.erase(cache.begin());
                cache.push_back(cities[i]);
            }

            answer += 5;
        }
    }

    return answer;
}
