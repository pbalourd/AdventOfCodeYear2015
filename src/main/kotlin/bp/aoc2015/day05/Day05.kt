package bp.aoc2015.day05

import java.io.File

fun main() {
    val lineList = File("src/main/kotlin/bp/aoc2015/day05/input05.txt")
        .readLines().toMutableList()

    val doubleNaughty = listOf("ab", "cd", "pq", "xy")
    var numOfNice = 0
    label@for (line in lineList) {
        if (line.count { it in "aeiou" } < 3) continue

        var count = 0
        for (i in 1..line.lastIndex) if (line[i] == line[i - 1]) count++
        if (count == 0) continue

        for (l in doubleNaughty) if (line.contains(l)) continue@label

        numOfNice++
    }
    println(numOfNice)
    // Solution 255

    numOfNice = 0
    for (line in lineList) {
        var arePairsOfLetters = false
        var isLetterRepeated = false
        for (i in 2..line.lastIndex) {
            val pairLetters = line.substring(i - 2, i)
            if (!arePairsOfLetters && line.substring(i).contains(pairLetters)) arePairsOfLetters = true
            if (!isLetterRepeated && line[i -2] == line[i]) isLetterRepeated = true
        }
        if (!arePairsOfLetters || !isLetterRepeated) continue
        numOfNice++
    }
    println(numOfNice)
    //Solution 55
}