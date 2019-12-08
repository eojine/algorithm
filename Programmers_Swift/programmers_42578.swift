func solution(_ clothes:[[String]]) -> Int {
    
    var dic: [String: Int] = [:]
    
    // dic에 모든 옷 종류, 개수 저장
    clothes.forEach { (cloth) in
        // 만약 dic에 옷이 저장되어있으면 +1 아니면 1 추가.
        if dic.keys.contains(cloth[1]) {
            dic[cloth[1]]! += 1
        } else {
            dic[cloth[1]] = 1
        }
    }
    
    // (첫번째 옷 종류 + 1) * ... * (마지막 옷 종류 + 1) -1
    // +1을 한 이유? 옷을 벗고있을 수도 있음
    // -1을 한 이유? 옷을 하나는 입고있음
    
    var result = 1
    for val in dic {
        result *= (val.value + 1)
    }
    
    return result - 1
}
