import Foundation

//var input = "25:09:1985:aa:091:4846:374:bb"
//var input = "1:2:3:4:5:6:7::"
var input = String(readLine()!)
var addrs = input.components(separatedBy: ":")

if addrs.last!.isEmpty { addrs.removeLast() }
while addrs.count < 8 {
    let index = addrs.firstIndex(of: "") ?? 0
    addrs.insert("0", at: index)
}

addrs = addrs.map { zeroString(str: $0, count: addrs.count) }

func zeroString(str: String, count: Int) -> String {
    var tmp = ""
    if str.count < 4 {
        for _ in 0..<4 - str.count {
            tmp.append("0")
        }
    }
    
    return tmp + str
}

print(addrs.joined(separator: ":"))
