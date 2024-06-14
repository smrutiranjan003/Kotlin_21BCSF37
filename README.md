# Kotlin Collections

Kotlin provides powerful mechanisms to manage collections of similar items. Collections can be either immutable (read-only) or mutable. The main types of collections are lists, sets, and maps.

## Lists:

- An ordered collection of elements.
- Allows duplicates.
- Use `mutableListOf` to create a mutable list.

Example:
```kotlin
val readOnlyList = listOf(10, 20, 30, 40)
val mutableList = mutableListOf(10, 20, 30, 40)
```

## Sets:

- An unordered collection of elements.
- Does not allow duplicates.
- Use `mutableSetOf` to create a mutable set.

Example:
```kotlin
val readOnlySet = setOf(10, 20, 30, 40)
val mutableSet = mutableSetOf(10, 20, 30, 40)
```

## Maps:

- A collection of key-value pairs.
- Keys are unique, but values can be duplicates.
- Use `mutableMapOf` to create a mutable map.

Example:
```kotlin
val readOnlyMap = mapOf("ten" to 10, "twenty" to 20, "thirty" to 30)
val mutableMap = mutableMapOf("ten" to 10, "twenty" to 20, "thirty" to 30)
```

## Transformations:

Transformations in Kotlin allow you to manipulate and process data effectively.

### 1. map

Transforms each element in the collection and returns a new collection.

```kotlin
fun mapExample() {
    val numbers = listOf(10, 20, 30, 40, 50)
    val squaredNumbers = numbers.map { it * it }
    println(squaredNumbers)
}
```

### 2. filter

Filters the collection based on a condition, returning only elements that match the condition.

```kotlin
fun filterExample() {
    val numbers = listOf(10, 20, 30, 40, 50, 60, 70)
    val evenNumbers = numbers.filter { it % 20 == 0 }
    println(evenNumbers)
}
```

## Null Safety

Kotlin's null safety features help prevent errors caused by null values.

To denote a variable as nullable, use the `?` operator:

```kotlin
val a: Int? = null
```

To safely call a function that might return a null value, use the safe call operator `?.`:

```kotlin
val a: String? = null
println(a?.length)
```

When you are certain that a value is not null, use the non-null assertion operator `!!.`:

```kotlin
val a: String? = "hello"
println(a!!.length)
```

### Elvis Operator (?:)

A concise way to handle nullable variables and provide default values if the variable is null.

```kotlin
val name: String? = "Kotlin"
val displayName = name ?: "Unknown"
println(displayName)
```

## let Keyword

The `let` function is a scope function that allows executing a block of code within the context of an object. It is useful for null checks, transformations, and processing data.

```kotlin
val name: String? = "Kotlin"
name?.let {
    println("Name length: ${it.length}")
}
```

Here, the code runs only when `name` is not null.
