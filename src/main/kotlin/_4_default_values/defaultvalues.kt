package _4_default_values

fun find1(name: String) {
    find1(name, true)
}

fun find1(name: String, recursive: Boolean) {}

fun find2(name: String, recursive: Boolean = true) {}

fun useFind() {
    find1("myfile.txt")
    find2("myfile.txt")
}


class Figure(
    val width: Int = 1,
    val height: Int = 1,
    val depth: Int = 1,
    color: Color = Color.BLACK,
    description: String = "This is a 3d figure",
)

enum class Color { BLACK, WHITE, GREEN, RED, BLUE }

fun useFigure() {
    Figure(color = Color.RED, description = "Red figure")
}






