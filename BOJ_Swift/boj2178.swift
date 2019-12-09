var NM = readLine()!.split(separator: " ").compactMap{ Int($0) }
var map = [[Int]]()
var queue = [(Int, Int)]()
let dx = [-1, 0, 1, 0]
let dy = [0, -1, 0, 1]

for _ in 0..<NM[0] {
    let input = readLine()!.compactMap{ Int(String($0)) }
    map.append(input)
}

let N = NM[0]
let M = NM[1]

var visitTemp = [Bool](repeating: false, count: M)
var visit = [[Bool]]()

for _ in 0..<N {
    visit.append(visitTemp)
}

// map범위 안에 들어있는지 체크
func inRange(_ x: Int, _ y: Int) -> Bool {
    return x < N && y < M && y >= 0 && x >= 0
}

visit[0][0] = true
queue.append((0, 0))

while !queue.isEmpty {
    let x = queue.first?.0
    let y = queue.first?.1
    
    queue.removeFirst()
    
    for i in 0..<4 {
        let nextX = x! + dx[i]
        let nextY = y! + dy[i]
        
        if !inRange(nextX, nextY) { continue }
        
        if !visit[nextX][nextY] && map[nextX][nextY] == 1 {
            visit[nextX][nextY] = true
            map[nextX][nextY] = map[x!][y!] + 1
            queue.append((nextX, nextY))
        }
    }
}

/*
for i in 0..<visit.count {
    print(visit[i])
}
for i in 0..<map.count {
    print(map[i])
}
*/

print(map[N-1][M-1])
