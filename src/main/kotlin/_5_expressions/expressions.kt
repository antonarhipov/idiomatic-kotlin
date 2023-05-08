package _5_expressions

// Hit Alt+Enter as much as you can!
fun adjustSpeed(weather: Weather): Drive {
    val result: Drive
    if (weather is Rainy) {
        result = Safe
    } else {
        result = Calm
    }
    return result
}

sealed class Drive
object Safe : Drive()
object Calm : Drive()

sealed class Weather
object Rainy : Weather()
object Windy : Weather()
object Sunny : Weather()


fun whenStatementWithBoolean() {
    val condition = fun(): Boolean { return true }() // gaming static analysis ;)

    when (condition) {
        true -> println("Lorem ipsum dolor sit amet")
        false -> println("Consectetur adipiscing elit")
    }
}