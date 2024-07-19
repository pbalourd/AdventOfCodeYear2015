package bp.aoc2015.day03

import java.io.File

fun main() {
    val moves = File("src/main/kotlin/bp/aoc2015/day03/input03.txt")
        .readText()

    var x = 0
    var y = 0
    val houses = mutableSetOf<Pair<Int, Int>>()
    houses.add(Pair(x, y))
    for (move in moves) {
        when (move) {
            '^' -> y++
            'v' -> y--
            '>' -> x++
            '<' -> x--
        }
        houses.add(Pair(x, y))
    }
    println(houses.size)
    // Solution 2565

    houses.clear()
    var x1 = 0
    var y1 = 0
    var x2 = 0
    var y2 = 0
    houses.add(Pair(x1, y1))
    for ((index, ch) in moves.withIndex()) {
        if (index % 2 == 0) {
            when (ch) {
                '^' -> y1++
                'v' -> y1--
                '>' -> x1++
                '<' -> x1--
            }
            houses.add(Pair(x1, y1))
        } else {
            when (ch) {
                '^' -> y2++
                'v' -> y2--
                '>' -> x2++
                '<' -> x2--
            }
            houses.add(Pair(x2, y2))
        }
    }
    println(houses.size)
    //Solution 2639
}
