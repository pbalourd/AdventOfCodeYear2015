package bp.aoc2015.day01

import java.io.File

fun main() {
    val input = File("src/main/kotlin/bp/aoc2015/day01/input01.txt").readText()

    val rightFloor: Long = input.sumOf { it -> if ( it == '(' ) 1L else -1L }

    println(rightFloor)
    // Solution 138

    var floor = 0
    for ((index,ch) in input.withIndex()) {
        floor += if (ch == '(') 1 else -1
        if (floor == -1) {
            println(index + 1)
            break
        }
    }
    // Solution 1771
}