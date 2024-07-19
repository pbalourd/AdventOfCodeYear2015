package bp.aoc2015.day10

import java.io.File

// 1321131112
fun main() {
    val input = File("src/main/kotlin/bp/aoc2015/day10/input10.txt").readText()

    printLookAndSayTimes(input, 40)
    //Solution 492982

    printLookAndSayTimes(input, 50)
    //Solution 6989950
}

fun printLookAndSayTimes(input: String, n: Int) {
    var str = input
    repeat(n) {
        str = str.lookAndSay()
    }
    println(str.length)
}

fun String.lookAndSay(): String {
    val regex = Regex("(\\d)\\1*")
    return regex
        .findAll(this)
        .toList()
        .joinToString("") { matchResult ->
            listOf(
                matchResult.value.length.toString(),
                matchResult.value[0]
            ).joinToString("")
        }
}