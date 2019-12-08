func solution(_ progresses:[Int], _ speeds:[Int]) -> [Int] {
    
    // 작업 개수
    let workCnt = progresses.count
    let remainProgress = progresses.compactMap{ 100 - $0 }
    var workQ: [Int] = []
    var resultArr: [Int] = []

    // 남은 작업 저장
    for i in 0..<workCnt {
        if remainProgress[i]%speeds[i] != 0 {
            workQ.append(remainProgress[i]/speeds[i] + 1)
        } else {
            workQ.append(remainProgress[i]/speeds[i])
        }
    }
    
    // 첫번째 작업 배포
    var workFirst = workQ.first!
    workQ.removeFirst()
    var cnt = 1
    while !workQ.isEmpty {
        let newWorkFirst = workQ.first!
        // 다음 작업이 먼저 배포된 작업보다 오래걸릴 때 다른날에 배포
        if workFirst < newWorkFirst {
            workFirst = newWorkFirst
            resultArr.append(cnt)
            cnt = 1
        } else {
            cnt += 1
        }
        workQ.removeFirst()
        if workQ.count == 0 {
            resultArr.append(cnt)
        }
    }
    return resultArr
}
