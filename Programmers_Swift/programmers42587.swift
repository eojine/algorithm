func solution(_ priorities:[Int], _ location:Int) -> Int {
    
    var pq: [(Int, Int)] = []
    var cnt = 0

    for i in 0..<priorities.count {
        pq.append((priorities[i], i))
    }
    
    while !pq.isEmpty {
        let pqFirstPriority = (pq.first?.0)!
        var isBig = true
        
        // 제일 큰 수인지 체크
        for i in 0..<pq.count {
            if pqFirstPriority < pq[i].0 {
                isBig = false
            }
        }
        
        if !isBig {
            let pqFirst = pq.first!
            pq.removeFirst()
            pq.append(pqFirst)
        } else {
            cnt += 1
            if pq.first?.1 == location {
                break;
            }
            pq.removeFirst()
        }
    }
    return cnt
}
