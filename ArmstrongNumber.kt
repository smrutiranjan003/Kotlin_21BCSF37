fun main() {
    // Q2
    var originalNumber = 370
    var tempNumber = originalNumber
    var calculatedSum = 0
    
    while (tempNumber != 0) {
        val digit = tempNumber % 10
        calculatedSum += digit * digit * digit
        tempNumber /= 10
    }
    
    if (originalNumber == calculatedSum) {
        println("Armstrong Number")
    } else {
        println("Not Armstrong number")
    }
}
