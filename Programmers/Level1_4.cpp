#include <string>
#include <vector>

using namespace std;

string solution(int a, int b) {
    string answer = "";
    string weekday[] = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
    int month[] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int daySum = 0;

    for (int i = 0; i < a; i++)
        daySum += month[i];
    daySum += b;
    answer = weekday[daySum%7];

    return answer;
}
