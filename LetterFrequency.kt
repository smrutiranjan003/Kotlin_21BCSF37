fun main() {
    // Q4
    val inputStr = "aabbbccddde"
    var visitedChars = "" // To check if we have visited this letter before
    println("Character Frequency")

    for (char in inputStr) {
        var count = 0
        for (innerChar in inputStr) {
            if (char == innerChar) count++
        }
        if (!(char in visitedChars)) println("$char\t$count")
        visitedChars += char
    }
}
