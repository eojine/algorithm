import Foundation

var input = String(readLine()!).split(separator: " ").map{ Int($0)! }
let N = input[0]
let M = input[1]
var list = [[Int]](repeating: [], count: N)
var degree = [Int](repeating: 0, count: N)

for _ in 0..<M {
    input = String(readLine()!).split(separator: " ").map{ Int($0)! }
    let a = input[0] - 1
    let b = input[1] - 1
    
    list[a].append(b)
    degree[b] += 1
}

// 0인 진입차수 새로운 q에 넣기
var q = degree.indices.filter{ degree[$0] == 0 }
var resQ = [Int]()

while !q.isEmpty {
    let now = q.popLast()!
    resQ.append(now)
    
    // 현재 리스트에서 갈 수 있는 다음 정점들
    for next in list[now] {
        degree[next] -= 1
        if degree[next] == 0 {
            q.append(next)
        }
    }
}

for res in resQ {
    print(res + 1, terminator: " ")
}
