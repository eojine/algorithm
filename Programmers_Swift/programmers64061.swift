import Foundation

var result = 0
func solution(_ board: [[Int]], _ moves:[Int]) -> Int {
    var stack = [Int]()
    var newBoard = board
    for move in moves {
        for i in 0..<board.count {
            if newBoard[i][move - 1] != 0 {
                bomb(&stack, num: newBoard[i][move - 1])
                newBoard[i][move - 1] = 0
                break
            }
        }
    }
    return result
}

func bomb(_ stack: inout [Int], num: Int) {
    if let last = stack.last, last == num {
        stack.removeLast()
        result += 2
        return
    }
    stack.append(num)
}
