#include <cmath>
#include <string>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

bool isPrime(int number) {
    if (number == 2)
        return true;
    if (number % 2 == 0 || number == 1)
        return false;
    for (int i = 2; i <= sqrt(number); i++) {
        if (number % i == 0)
            return false;
    }
    return true;
}

int solution(string numbers) {
    int answer = 0;
    vector<int> v;
    set<int> primes;

    for (int i = 0; i < numbers.length(); ++i) {
        int num = stoi(numbers.substr(i, 1));
        v.push_back(num);
        if (isPrime(num))
            primes.insert(num);
    }

    for (int vSize = 1; vSize <= v.size(); ++vSize) {
        vector<int> ind(vSize, 1);
        for (int i = 1; i < v.size() - vSize; i++)
            ind.push_back(0);

        sort(ind.begin(), ind.end(), greater<int>());

        do {
            int tmpI = 0;
            int tmp = 1;

            for (int i = 0; i <= vSize; ++i) {
                if (ind[i] == 1) {
                    tmpI += (v[i] * tmp);
                    tmp *= 10;
                }
            }
            if (isPrime(tmpI))
                primes.insert(tmpI);
        } while (next_permutation(v.begin(), v.end()));
    }

    answer = primes.size();

    return answer;
}
