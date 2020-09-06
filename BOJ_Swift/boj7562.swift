import Foundation

let dirs = [[-2, -1], [-1, -2], [-2, 1], [-1, 2], [1, 2], [2, 1], [2, -1], [1, -2]]
let testCase = Int(readLine()!)!

for _ in 0..<testCase {
    let l = Int(readLine()!)!
    let current = readLine()!.split(separator: " ").map{Int($0)!}
    let destination = readLine()!.split(separator: " ").map{Int($0)!}
    
    var map: [[Int]] = Array(repeating: Array(repeating: 0, count: l), count: l)
    var queue: [Point] = []
    
    queue.append(Point(current[0], current[1], 0))
    map[current[0]][current[1]] = 1
    
    while !queue.isEmpty {
        let now = queue.removeFirst()
        
        if now.r == destination[0] && now.c == destination[1] {
            print(now.count)
            break
        }
        
        for i in 0..<dirs.count {
            let nextR = dirs[i][0] + now.r
            let nextC = dirs[i][1] + now.c
            
            if !inRange(r: nextR, c: nextC, len: l) { continue }
            if map[nextR][nextC] == 1 { continue }
            queue.append(Point(nextR, nextC, now.count + 1))
            map[nextR][nextC] = 1
        }
    }
}

func inRange(r: Int, c: Int, len: Int) -> Bool {
    return r >= 0 && c >= 0 && r < len && c < len
}

class Point {
    var r: Int
    var c: Int
    var count: Int
    
    init(_ r: Int,_ c: Int,_ count: Int) {
        self.r = r
        self.c = c
        self.count = count
    }
}
