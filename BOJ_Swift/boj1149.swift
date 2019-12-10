let N = Int(readLine()!)!
var house = [[Int]]()
var dp = Array(repeating: Array(repeating: 0, count: 3), count: N)

for _ in 0..<N {
    house.append(readLine()!.split(separator: " ").compactMap{ Int(String($0)) })
}

for i in 0..<3 {
    dp[0][i] = house[0][i]
}

for i in 1..<N {
    // R : dp[i][0]
    // G : dp[i][1]
    // B : dp[i][2]
    // 각각 색을 선택했을때의 모든 최솟값을 dp에 저장함.
    dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + house[i][0]
    dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + house[i][1]
    dp[i][2] = min(dp[i - 1][1], dp[i - 1][0]) + house[i][2]
}

print(min(min(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]))
