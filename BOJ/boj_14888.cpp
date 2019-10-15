#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main() {

    int N;
    long long maxValue = -1000000000;
    long long minValue = 1000000000;
    vector<long long> numbers;
    vector<string> op;

    cin >> N;

    for (int i = 0; i < N; ++i) {
        int x;
        cin >> x;
        numbers.push_back(x);
    }

    for (int i = 0; i < 4; ++i) {
        int x;
        cin >> x;

        if (i == 0)
            for (int j = 0; j < x; ++j)
                op.push_back("+");
        else if (i == 1)
            for (int j = 0; j < x; ++j)
                op.push_back("-");
        else if (i == 2)
            for (int j = 0; j < x; ++j)
                op.push_back("*");
        else if (i == 3)
            for (int j = 0; j < x; ++j)
                op.push_back("/");
    }

    sort(op.begin(), op.end());

    do {
        vector<string> opTemp;
        long long value = 0;

        for (int i = 0; i < op.size(); i++)
            opTemp.push_back(op[i]);

        for (int i = 0; i < N; ++i) {

            if (i == 0)
                value += numbers[i];
            else {
                if (opTemp[i - 1] == "+")
                    value += numbers[i];
                else if (opTemp[i - 1] == "-")
                    value -= numbers[i];
                else if (opTemp[i - 1] == "*")
                    value *= numbers[i];
                else if (opTemp[i - 1] == "/")
                    value /= numbers[i];
            }

        }

        maxValue = max(value, maxValue);
        minValue = min(value, minValue);

        opTemp.clear();

    } while (next_permutation(op.begin(), op.end()));

    printf("%d\n%d", maxValue, minValue);

    return 0;
}
