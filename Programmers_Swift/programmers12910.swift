func solution(_ arr:[Int], _ divisor:Int) -> [Int] {
    var array = arr.filter{ $0 % divisor == 0 }
    array.sort()
    return  array == [] ? [-1] : array
}

print(solution(arr, divisor))
