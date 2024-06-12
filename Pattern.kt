fun main() {
    // Pattern of New Q1
    val maxRows = 5
    
    // Upper part of the pattern
    for (i in 1..maxRows) {
        for (k in maxRows - i downTo 1) {
            print(' ')
        }
        for (j in 1..i) {
            print('*')
        }
        for (l in 1 until i) {
            print('*')
        }
        println()
    }
    
    // Lower part of the pattern
    for (i in maxRows downTo 1) {
        for (k in 1 until maxRows - i + 1) {
            print(' ')
        }
        for (j in i downTo 1) {
            print('*')
        }
        for (l in 1 until i) {
            print('*')
        }
        println()
    }
}
