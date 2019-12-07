func solution(_ n:Int64) -> [Int] {
    var scattered = String(n).map{ String($0) }
    scattered.reverse()
    let res = scattered.compactMap{ Int($0) }
    return res
}
