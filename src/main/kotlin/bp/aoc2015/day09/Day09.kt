package bp.aoc2015.day09

import java.io.File
import bp.aoc2015.utils.IntPermutations

fun main() {
    val cities = mutableSetOf<String>()
    val routes = mutableMapOf<Pair<String, String>, Int>()

    File("src/main/kotlin/bp/aoc2015/day09/input09.txt").readLines()
        .map { line -> line.split(" to ", " = ") }
        .forEach { list ->
            cities.add(list[0])
            cities.add(list[1])
            routes[Pair(list[0], list[1])] = list[2].toInt()
            routes[Pair(list[1], list[0])] = list[2].toInt()
        }

    val permutations = IntPermutations(cities.size)

    var minDistance = Int.MAX_VALUE
    repeat(permutations.totalPermutations.toInt()) {
        var distance = 0
        var start = cities.elementAt(permutations.next())
        repeat(cities.size - 1) {
            val next = cities.elementAt(permutations.next())
            distance += routes[Pair(start, next)]!!
            start = next
        }
        if (distance < minDistance) minDistance = distance
    }

    println(minDistance)
    //Solution 207

    permutations.reset()
    var maxDistance = 0
    repeat(permutations.totalPermutations.toInt()) {
        var distance = 0
        var start = cities.elementAt(permutations.next())
        repeat(cities.size - 1) {
            val next = cities.elementAt(permutations.next())
            distance += routes[Pair(start, next)]!!
            start = next
        }
        if (distance > maxDistance) maxDistance = distance
    }

    println(maxDistance)
    //Solution 804
}
