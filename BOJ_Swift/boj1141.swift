import Foundation

let wordCnt = Int(readLine()!)!
var words = [String]()
var result = 0

(0..<wordCnt).forEach { _ in
    let str = String(readLine()!)
    words.append(str)
}

let sortedWords = words.sorted { (str1, str2) -> Bool in
    str1.count < str2.count
}

for i in 0..<wordCnt {
    let nowWord = sortedWords[i]
    var hasPrefix = false
    
    for j in (i + 1)..<wordCnt {
        if sortedWords[j].hasPrefix(nowWord) {
            hasPrefix = true
            break
        }
    }
    
    if !hasPrefix {
        result += 1
    }
}

print(result)
