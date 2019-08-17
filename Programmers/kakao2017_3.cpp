#include <iostream>
#include <string>
#include <cmath>

using namespace std;

int powSDT(char str, int num) {
    if (str == 'S') {
        num = pow(num, 1);
    } else if (str == 'D') {
        num = pow(num, 2);
    } else if (str == 'T') {
        num = pow(num, 3);
    }
    return num;
}

int solution(string dartResult) {
    int answer = 0;
    int cnt = 0;
    int arr[3] = {0, 0, 0};
    bool isStar = false;

    for (int i = 0; i < dartResult.length(); ++i) {

        if (dartResult[i] == 'S' || dartResult[i] == 'D' || dartResult[i] == 'T') {
            int temp = dartResult[i - 1] - 48;
            if (temp == 0 && (dartResult[i - 2] - 48 == 1)) {
                temp = 10;
            }
            arr[cnt++] = powSDT(dartResult[i], temp);
            isStar = false;
        }
        
        if (dartResult[i] == '*') {
            if (cnt == 3) {
                for (int j = 1; j < cnt; ++j) {
                    arr[j] *= 2;
                }
            } else {
                for (int j = 0; j < cnt; ++j) {
                    arr[j] *= 2;
                }
            }
            isStar = true;
        }

        if (dartResult[i] == '#') {
            if (isStar == true) {
                arr[cnt - 1] *= -2;
            } else {
                arr[cnt - 1] *= -1;
            }
        }
    }

    for (int i = 0; i < 3; ++i) {
        answer += arr[i];
    }
    
    return answer;
}
