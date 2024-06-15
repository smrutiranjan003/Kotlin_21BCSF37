# Kotlin Features: Lambda Functions and Concurrency

## Lambda Functions in Kotlin

Lambda functions in Kotlin are anonymous functions that can be treated as values. They are defined using the following syntax:

```kotlin
val lambdaName: (parameters) -> returnType = { arguments -> lambdaBody }
```

Lambdas are commonly used with higher-order functions like `map`, `filter`, `reduce`, etc., to perform operations on collections. This improves code readability and conciseness.

## Concurrency in Kotlin

Concurrency refers to the ability of a program to execute multiple tasks simultaneously, allowing for efficient utilization of resources and enhanced responsiveness.

### Coroutines in Kotlin

Coroutines are a feature in Kotlin that allows you to write asynchronous code sequentially. They are defined using `suspend` functions and managed by Kotlin's `CoroutineScope`.

To use coroutines in Kotlin, import the following package:

```kotlin
import kotlinx.coroutines.*
```

### runBlocking

`runBlocking` is a coroutine builder that creates a new coroutine and blocks the current thread where it is called until the coroutine completes. It is typically used in main functions to bridge synchronous and asynchronous code.

Example:

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        println("Hello")
        delay(1000L)
        println("World")
    }
}
```

- `launch`: Starts a new coroutine without blocking the current thread.
- `delay`: Suspends the coroutine for a specified time (1000L = 1 second).

### Dispatchers

Dispatchers specify the execution context of coroutines, helping manage thread pools and ensuring efficient resource utilization. Here are the types of dispatchers:

- `Dispatchers.Default`: Suitable for CPU-bound tasks.
- `Dispatchers.IO`: Optimized for I/O-bound tasks.
- `Dispatchers.Main`: Available on Android for UI updates from coroutines.
- `Dispatchers.Unconfined`: Starts the coroutine in the current thread but may resume execution in a different thread after suspension.

### Example Code

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    launch(Dispatchers.Default) {
        println("Running on Default Dispatcher")
        delay(500L)
        println("Default Dispatcher Task Complete")
    }

    launch(Dispatchers.IO) {
        println("Running on IO Dispatcher")
        delay(700L)
        println("IO Dispatcher Task Complete")
    }

    launch(Dispatchers.Unconfined) {
        println("Running on Unconfined Dispatcher")
        delay(300L)
        println("Unconfined Dispatcher Task Complete")
    }

    println("Main function execution")
}
```

This code demonstrates how to use different dispatchers in Kotlin coroutines to manage tasks with varying execution contexts efficiently.
