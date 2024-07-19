package bp.aoc2015.day02

import java.io.File
import kotlin.math.min

fun main() {
    val presents = File("src/main/kotlin/bp/aoc2015/day02/input02.txt")
        .readLines().map { it ->
            it.split("x")
                .map { s -> s.toInt() }
        }
    var area = 0
    for (p in presents) {
        val (l, w, h) = p
        area += 2*l*w + 2*w*h + 2*h*l + min( min(l * w, w * h), h * l )
    }
    println(area)
    // Solution 1586300

    var length = 0
    for (p in presents) {
        val (l, w, h) = p.sorted()
        length += 2 * l + 2 * w + l * w * h
    }
    println(length)
    //Solution 3737498
}