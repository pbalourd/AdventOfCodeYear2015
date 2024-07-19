package bp.aoc2015.day08

import java.io.File

fun main() {
    val lineList = File("src/main/kotlin/bp/aoc2015/day08/input08.txt")
        .readLines()

    val numCharsStr = lineList
        .sumOf { line -> line.length }

    var numCharsMem = 0
    for (line in lineList){
        var count = 0
        var index = 1
        while (index < line.lastIndex) {
            if (line[index] == '\\') {
                if (line[index + 1] == '\\' || line[index + 1] == '\"') {
                    index++
                } else {
                    index += 3
                }
            }
            index++
            count++
        }
        numCharsMem += count
    }

    println(numCharsStr - numCharsMem)
    // Solution 1342

    val numCharsEncoded = lineList.map { line ->
        line
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("^".toRegex(), "\"")
            .replace("$".toRegex(), "\"")
    }
        .sumOf { line -> line.length }

    println(numCharsEncoded - numCharsStr)
    //Solution 2074
}
