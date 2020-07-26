import Foundation

var arr = [5, 9, 7, 10]
var divisor = 5

func solution(_ arr:[Int], _ divisor:Int) -> [Int] {
    var array = arr.filter{ $0 % divisor == 0 }
    array.sort()
    return  array == [] ? [-1] : array
}

print(solution(arr, divisor))
