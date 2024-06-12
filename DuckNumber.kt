fun main() {
    // Modified code to check if a number is a duck number
    var number = 2045
    var tempNumber = number
    var isDuck = false

    while (tempNumber != 0) {
        val digit = tempNumber % 10
        if (digit == 0) {
            println("$number is a Duck number")
            isDuck = true
            break
        }
        tempNumber /= 10
    }

    if (!isDuck) println("$number is not a Duck number")
}
