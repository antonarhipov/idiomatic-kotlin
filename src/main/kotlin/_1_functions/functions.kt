package _1_functions

fun main() {
    doSomething()
}

// top level function
fun doSomething() {
    // function references
    // https://kotlinlang.org/docs/reflection.html#function-references
    doMoreStuff(::finishWork)
}

// Higher order function
// https://kotlinlang.org/docs/lambdas.html
fun doMoreStuff(block: () -> Unit) {
    block()
}

fun finishWork() {
    // Nothing
    TODO("Not yet implemented")
}
