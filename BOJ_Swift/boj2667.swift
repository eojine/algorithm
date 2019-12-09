let N = Int(readLine()!)!
var map = [[Int]]()
var visitTemp = [Bool](repeating: false, count: N)
var visit = [[Bool]]()

var danjiCnt = 10
var apartCnt = 1
var danjiArr = [Int]()

for _ in 0..<N {
    let input = readLine()!.compactMap{ Int(String($0)) }
    map.append(input)
    visit.append(visitTemp)
}

func inRange(_ x: Int, _ y: Int) -> Bool {
    return x >= 0 && y >= 0 && x < N && y < N
}

func BFS(_ x: Int, _ y: Int, _ danji: Int) {
    var queue = [(Int, Int)]()
    let dx = [-1, 0, 1, 0]
    let dy = [0, -1, 0, 1]
    
    queue.append((x, y))
    map[x][y] = danji
    
    while !queue.isEmpty {
        guard let x = queue.first?.0 else { break }
        guard let y = queue.first?.1 else { break }
        
        queue.removeFirst()
        
        for i in 0..<4 {
            let nx = x + dx[i]
            let ny = y + dy[i]
            
            if !inRange(nx, ny) {
                continue
            }
            
            if map[nx][ny] == 1 && !visit[nx][ny] {
                map[nx][ny] = danji
                visit[nx][ny] = true
                queue.append((nx, ny))
                apartCnt += 1
            }
        }
    }
}

for i in 0..<N {
    for j in 0..<N {
        if map[i][j] == 1 {
            BFS(i, j, danjiCnt)
            danjiArr.append(apartCnt)
            danjiCnt += 1
            apartCnt = 1
        }
    }
}

print(danjiArr.count)
danjiArr.sort()
danjiArr.forEach{ print($0) }
