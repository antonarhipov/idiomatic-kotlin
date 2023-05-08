package _8_dsl

fun buildStringExample() {
    val name = "Joe"
    val s = buildString {
        repeat(5) {
            append("Hello, ")
            append(name)
            appendLine("!")
        }
    }
    println(s)
}


// trailing lambdas
// extensions
// functional literal with receiver
// apply()
// type-safe builders

