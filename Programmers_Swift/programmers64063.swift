import Foundation

var dict = [Int64: Int64]()

func solution(_ k: Int64, _ room_number: [Int64]) -> [Int64] {
    var answer = [Int64]()
    
    for number in room_number {
        let room = find(room: number)
        answer.append(room)
    }
    
    return answer
}

func find(room: Int64) -> Int64 {
    if dict[room] == nil {
        dict[room] = room + 1
        return room
    }
    
    dict[room] = find(room: dict[room]!)
    return dict[room]!
}
