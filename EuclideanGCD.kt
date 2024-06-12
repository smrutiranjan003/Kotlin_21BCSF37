fun main() {
    // GCD Calculation
    var number1 = 48
    var number2 = 18
    var remainder1 = number1
    var remainder2 = number2
    while (remainder2 != 0) {
        var mod = remainder1 % remainder2
        remainder1 = remainder2
        remainder2 = mod
    }
    println("GCD is $remainder1")
}
