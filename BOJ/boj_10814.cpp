#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

struct person {
    int age;
    char name[101];
};

bool cmp(const person &x, const person &y) {
    return x.age < y.age;
}

int main() {
    int n;
    scanf("%d", &n);

    vector<person> value(n);

    for (int i = 0; i < n; ++i) {
        scanf("%d %s", &value[i].age, value[i].name);
    }

    stable_sort(value.begin(), value.end(), cmp);


    for (int i = 0; i < n; ++i) {
        printf("%d %s\n", value[i].age, value[i].name);
    }

    return 0;
}
