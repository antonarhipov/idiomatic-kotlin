package _3_scope_functions

import java.io.File

class BasicDataSource {
    lateinit var password: String
    lateinit var username: String
    lateinit var url: String
    lateinit var driverClassName: String

    var minIdle: Int = 0
    var maxIdle: Int = 0
    var maxTotal: Int = 0
}

fun applyExample() {
    val dataSource = BasicDataSource()
    dataSource.driverClassName = "com.mysql.jdbc.Driver"
    dataSource.url = "jdbc:mysql://domain:3309/db"
    dataSource.username = "username"
    dataSource.password = "password"
    dataSource.maxTotal = 40
    dataSource.maxIdle = 40
    dataSource.minIdle = 4

    val ds = BasicDataSource().apply {
        driverClassName = "com.mysql.jdbc.Driver"
        url = "jdbc:mysql://domain:3309/db"
        username = "username"
        password = "password"
        maxTotal = 40
        maxIdle = 40
        minIdle = 4
    }
}

fun letExample() {
    fun createDS(): BasicDataSource? = TODO()

    val ds = createDS()
    ds?.let {
        println(it.url)
    }
}

fun letAsAHelperForComplexCondition() {
    class SomeType(val attr: Boolean)

    fun createSomeType(): Any = SomeType(true)

    if (createSomeType().let { it is SomeType && it.attr }) {
        println("")
    }
}

fun alsoExample(){
    fun makeDirBad(path: String) = path.let { File(it) }.also { it.mkdirs() }

    fun makeDirSimple(path: String) : File {
        val file = File(path)
        file.mkdirs()
        return file
    }

    fun makeDirOK(path: String) = File(path).also { it.mkdirs() }
}