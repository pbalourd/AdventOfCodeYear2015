package bp.aoc2015.day17

import java.io.File
import bp.aoc2015.utils.combinations

fun main() {
    val containers = File("src/main/kotlin/bp/aoc2015/day17/input17.txt")
        .readLines()
        .map { line -> line.toInt() }
        .toMutableList()

    val eggnogLiters = 150

    var minNum = containers.size
    var count = 0
    for (i in 1..containers.size) {
        containers.combinations(i)
            .forEach { c ->
                if (c.sum() == eggnogLiters) {
                    count++
                    if (i < minNum) minNum = i
                }
            }
    }

    println(count)
    // Solution 1638

    count = 0
    containers.combinations(minNum)
        .forEach { c ->
            if (c.sum() == eggnogLiters) count++
        }

    println(count)
    //Solution 17
}


