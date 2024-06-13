# Kotlin Examples

## Functions

### Basic Function
Functions in Kotlin are defined using the `fun` keyword. The return type is specified after the parentheses with a colon. 

```kotlin
fun double(num: Int): Int {
    return 2 * num
}
```

### Default Parameters
Kotlin allows default parameter values in functions.

```kotlin
fun double(num: Int = 0): Int {
    return 2 * num
}
```

### Single Expression Functions
These functions contain only a single expression.

```kotlin
fun sum(a: Int, b: Int): Int = a + b
```

### Varargs
Using the `varargs` keyword allows a function to accept any number of arguments.

```kotlin
fun printVars(vararg numbers: Int) {
    for (number in numbers) {
        println(number)
    }
}

fun main() {
    printVars(1, 2, 3, 4, 5)
}
```

### Real-life Example: Calculating Discounts
```kotlin
fun calculateTotalPrice(vararg prices: Double, discount: Double = 0.0): Double {
    val total = prices.sum()
    return total - (total * discount)
}

fun main() {
    val totalPrice = calculateTotalPrice(19.99, 29.99, 4.99, discount = 0.1)
    println("Total price after discount: $$totalPrice")
}
```

## Extension Functions
These functions add functionality to existing classes without inheritance.

```kotlin
class Rectangle(val length: Double, val breadth: Double) {
    fun area(): Double {
        return length * breadth
    }
}

fun Rectangle.perimeter(): Double {
    return 2 * (length + breadth)
}

fun main() {
    val rect = Rectangle(4.0, 5.0)
    println("Perimeter of rectangle is ${rect.perimeter()}")
    println("Area of rectangle is ${rect.area()}")
}
```

### Real-life Example: String Extension
```kotlin
fun String.isEmail(): Boolean {
    return this.contains("@") && this.contains(".")
}

fun main() {
    val email = "test@example.com"
    println("$email is a valid email: ${email.isEmail()}")
}
```

## Higher Order Functions
A higher-order function accepts another function as an argument, often using a lambda function.

```kotlin
val lambda = { a: Int, b: Int -> a + b }

fun higherFunc(lower: (Int, Int) -> Int) {
    val result = lower(2, 4)
    println("The sum of two numbers is: $result")
}

fun main() {
    higherFunc(lambda)
}
```

### Real-life Example: Sorting with Custom Comparator
```kotlin
fun <T> customSort(list: List<T>, comparator: (T, T) -> Int): List<T> {
    return list.sortedWith(Comparator(comparator))
}

fun main() {
    val names = listOf("John", "Alice", "Bob")
    val sortedNames = customSort(names) { a, b -> a.length - b.length }
    println("Sorted names by length: $sortedNames")
}
```

## Classes

A class is a blueprint for creating objects, defining initial values for state and behavior using member variables and functions.

```kotlin
class Person {
    var name: String = ""

    fun showName() {
        println(name)
    }
}

fun main() {
    val p = Person()
    p.name = "XYZ"
    p.showName()
}
```

### Real-life Example: Bank Account
```kotlin
class BankAccount(private var balance: Double) {
    fun deposit(amount: Double) {
        balance += amount
        println("Deposited $$amount. New balance: $$balance")
    }

    fun withdraw(amount: Double) {
        if (amount <= balance) {
            balance -= amount
            println("Withdrew $$amount. New balance: $$balance")
        } else {
            println("Insufficient funds. Current balance: $$balance")
        }
    }
}

fun main() {
    val account = BankAccount(100.0)
    account.deposit(50.0)
    account.withdraw(30.0)
    account.withdraw(150.0)
}
```

## Generic Classes

Generic classes in Kotlin allow the creation of classes, interfaces, and functions that can operate with different types of data while maintaining type safety.

```kotlin
class Box<T>(var content: T)

fun main() {
    val intBox: Box<Int> = Box(1)
    println("Content of intBox: ${intBox.content}")
    intBox.content = 42
    println("Updated content of intBox: ${intBox.content}")

    val stringBox: Box<String> = Box("Hello")
    println("Content of stringBox: ${stringBox.content}")
    stringBox.content = "World"
    println("Updated content of stringBox: ${stringBox.content}")
}
```

### Real-life Example: Storage Container
```kotlin
class Storage<T>(private val items: MutableList<T> = mutableListOf()) {
    fun addItem(item: T) {
        items.add(item)
    }

    fun getItems(): List<T> {
        return items
    }
}

fun main() {
    val stringStorage = Storage<String>()
    stringStorage.addItem("Apple")
    stringStorage.addItem("Banana")
    println("String Storage: ${stringStorage.getItems()}")

    val intStorage = Storage<Int>()
    intStorage.addItem(10)
    intStorage.addItem(20)
    println("Int Storage: ${intStorage.getItems()}")
}
```

---

