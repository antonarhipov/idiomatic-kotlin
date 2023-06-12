package _2_extensions

import _2_extensions.MyScope.yell

fun main() {
    "String".say()

    //can't restrict the usage of 'yell' as we can just import the function
    "String".yell()

    with(MyScope) {
        //won't work -> yell2 is private
        //"String".yell2()
    }

    with(MyScope) {
        "String".scream()
    }

}

private fun String.say() {
    println(this)
}

object MyScope {
    fun String.yell() = println("$this!!")
    private fun String.yell2() = println("$this!!")
}

context(MyScope)
private fun String.scream() = println("$this-$this-$this")