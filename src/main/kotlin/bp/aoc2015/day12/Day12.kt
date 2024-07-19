package bp.aoc2015.day12

import com.fasterxml.jackson.databind.*
import java.io.File

fun main() {
    val input = File("src/main/kotlin/bp/aoc2015/day12/input12.txt").readText()

    val sum = "[-]?\\d+".toRegex()
        .findAll(input)
        .toList()
        .sumOf { matchResult -> matchResult.value.toInt() }
    println(sum)
    //Solution 119433

    val mapper = ObjectMapper()
    val tree = mapper.readTree(input)
    println(sumObjects(tree))
    //Solution 68466
}

fun sumObjects(root: JsonNode): Int {
    return if (root.isObject) {
        val elements = root.elements()
        var count = 0
        for (element in elements) {
            if (element.isTextual && element.textValue() == "red") {
                count = 0
                break
            }
            count += sumObjects(element)
        }
        count
    } else if (root.isArray) {
        var count = 0
        for (i in 0 until root.size()) {
            count += sumObjects(root[i])
        }
        count
    } else {
        root.intValue()
    }
}