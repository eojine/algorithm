#include <iostream>
#include <vector>

using namespace std;

//회전
vector<vector<int>> rotateKey(vector<vector<int>> key) {
    vector<vector<int>> rotateKey;
    vector<int> v;
    for (int i = 0; i < key.size(); ++i) {
        for (int j = key.size() - 1; j >= 0; --j) {
            v.push_back(key[j][i]);
        }
        rotateKey.push_back(v);
        v.clear();
    }
    return rotateKey;
}

bool inRange(int first, int second, int y, int x, int lockSize) {
    return first + y >= 0 && second + x >= 0 && first + y < lockSize && second + x < lockSize;
}

bool solution(vector<vector<int>> key, vector<vector<int>> lock) {

    int lockSize = lock.size();
    int keySize = key.size();
    int lockNum = 0;
    vector<vector<int>> tmpKey;

    tmpKey = key;

    // 홈 개수 체크
    for (int y = 0; y < lockSize; y++) {
        for (int x = 0; x < lockSize; x++) {
            if (lock[y][x] == 0)
                lockNum++;
        }
    }

    for (int i = 0; i < 4; i++) {

        tmpKey = rotateKey(tmpKey);
        vector<pair<int, int>> keyNum;
        int tmpLockNum;
        int flag = 0;

        // 홈 위치 저장
        for (int y = 0; y < keySize; y++) {
            for (int x = 0; x < keySize; x++) {
                if (tmpKey[y][x] == 1)
                    keyNum.push_back({y, x});
            }
        }

        for (int y = 0; y <= 20; y++) {
            for (int x = 0; x <= 20; x++) {

                tmpLockNum = lockNum;
                flag = 0;

                // +x, +y
                for (int z = 0; z < keyNum.size(); z++) {
                    if (inRange(keyNum[z].first, keyNum[z].second, y, x, lockSize)) {
                        if (lock[keyNum[z].first + y][keyNum[z].second + x] == 0) {
                            tmpLockNum--;
                        } else {
                            flag = 1;
                            break;
                        }
                    }
                }

                if (flag == 0 && tmpLockNum == 0)
                    return true;


                tmpLockNum = lockNum;
                flag = 0;

                // -x, -y
                for (int z = 0; z < keyNum.size(); z++) {
                    if (inRange(keyNum[z].first, keyNum[z].second, -y, -x, lockSize)) {
                        if (lock[keyNum[z].first - y][keyNum[z].second - x] == 0) {
                            tmpLockNum--;
                        } else {
                            flag = 1;
                            break;
                        }
                    }
                }

                if (flag == 0 && tmpLockNum == 0)
                    return true;

                tmpLockNum = lockNum;
                flag = 0;

                // -y, +x
                for (int z = 0; z < keyNum.size(); z++) {
                    if (inRange(keyNum[z].first, keyNum[z].second, -y, x, lockSize)) {
                        if (lock[keyNum[z].first - y][keyNum[z].second + x] == 0) {
                            tmpLockNum--;
                        } else {
                            flag = 1;
                            break;
                        }

                    }
                }
                if (flag == 0 && tmpLockNum == 0)
                    return true;

                tmpLockNum = lockNum;
                flag = 0;

                // +y, -x
                for (int z = 0; z < keyNum.size(); z++) {
                    if (inRange(keyNum[z].first, keyNum[z].second, y, -x, lockSize)) {
                        if (lock[keyNum[z].first + y][keyNum[z].second - x] == 0) {
                            tmpLockNum--;
                        } else {
                            flag = 1;
                            break;
                        }

                    }
                }
                if (flag == 0 && tmpLockNum == 0)
                    return true;
            }
        }
    }
    return false;
}
