#include <iostream>

using namespace std;

int main() {

    int maxTrain = 0;
    int train = 0;

    for (int i = 0; i < 4; ++i) {
        int out, in;

        cin >> out >> in;

        train -= out;
        train += in;

        if (maxTrain <= train)
            maxTrain = train;
    }

    cout << maxTrain;

    return 0;
}
