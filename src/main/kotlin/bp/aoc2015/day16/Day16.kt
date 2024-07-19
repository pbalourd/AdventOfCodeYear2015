package bp.aoc2015.day16

import java.io.File

val ticker = mapOf(
    "children" to 3,
    "cats" to 7,
    "samoyeds" to 2,
    "pomeranians" to 3,
    "akitas" to 0,
    "vizslas" to 0,
    "goldfish" to 5,
    "trees" to 3,
    "cars" to 2,
    "perfumes" to 1
)

fun main() {
    val sueList = File("src/main/kotlin/bp/aoc2015/day16/input16.txt").readLines()
        .map { line ->
            line.substringAfter(": ")
                .split(", ")
                .map {
                    val s = it.split(": ")
                    s[0] to s[1].toInt()
                }.toMap()
        }

    for ((index, sue) in sueList.withIndex()) {
        var isSue = true
        for (element in sue) {
            if (ticker[element.key] != element.value) {
                isSue = false
                break
            }
        }
        if (isSue) {
            println(index + 1)
            break
        }
    }
    //Solution 373

    for ((index, sue) in sueList.withIndex()) {
        var isSue = true
        for (element in sue) {

            val catsAndTrees = (element.key == "cats" || element.key == "trees")
            val pomeraniansAndGoldfish = (element.key == "pomeranians" || element.key == "goldfish")

            if (catsAndTrees) {
                if (ticker[element.key]!! >= element.value) isSue = false
            }
            else if (pomeraniansAndGoldfish) {
                if (ticker[element.key]!! <= element.value) isSue = false
            }
            else if (ticker[element.key] != element.value) isSue = false
            if (!isSue) break
        }
        if (isSue) {
            println(index + 1)
            break
        }
    }
    // Solution 260
}
