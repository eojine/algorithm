import Foundation

func solution(_ w: Int, _ h: Int) -> Int64{
    let gcd = GCD(w, h)
    let smallW = w/gcd
    let smallH = h/gcd
    
    let cutRectangle = (smallW + smallH - 1) * gcd
    
    return Int64(w * h - cutRectangle)
}

func GCD(_ min: Int, _ max: Int) -> Int {
    let remain = min % max
    if remain == 0 {
        return max
    } else {
        return GCD(max, remain)
    }
}
