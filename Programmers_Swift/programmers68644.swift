func solution(_ numbers: [Int]) -> [Int] {
    var plusSet = Set<Int>()
    for i in 0..<numbers.count {
        for j in i + 1..<numbers.count {
            plusSet.insert(numbers[i] + numbers[j])
        }
    }
    return plusSet.sorted()
}
