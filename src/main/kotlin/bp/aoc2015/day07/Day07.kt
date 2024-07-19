package bp.aoc2015.day07

import java.io.File

val values = mutableMapOf<String, Int>()
val wireOperations = mutableMapOf<String, Pair<String, List<String>>>()

fun main() {
    val lineList = File("src/main/kotlin/bp/aoc2015/day07/input07.txt")
        .readLines()

    parseInput(lineList)

    val value = getWireValue("a")
    println(value)
    //Solution 956

    wireOperations["b"] = Pair("NULL", listOf(value.toString()))
    println(getWireValue("a"))
    //Solution 40149
}

fun getWireValue(wire: String): Int {
    values.clear()
    val value = evaluateWire(wire)
    return value
}

fun evaluateWire(wire: String): Int {
    if (values[wire] != null) return values[wire]!!
    val command = wireOperations[wire]!!.first
    val args = wireOperations[wire]!!.second

    val value = when (command) {
        "NULL" -> {
            if (args[0].toIntOrNull() == null) evaluateWire(args[0])
            else args[0].toInt()
        }
        "NOT" -> {
            evaluateWire(args[0]).inv().and(65535)
        }
        else -> {
            val a = if (args[0].toIntOrNull() == null) evaluateWire(args[0])
                else args[0].toInt()
            val b = if (args[1].toIntOrNull() == null) evaluateWire(args[1])
            else args[1].toInt()
            when (command) {
                "AND" -> a and b
                "OR" -> (a or b)
                "LSHIFT" -> return (a shl b)
                "RSHIFT" -> return (a shr b)
                else -> 0
            }
        }
    }
    values[wire] = value
    return value
}

fun parseInput(
    lineList: List<String>) {
    for (line in lineList) {
        val instructionParts = line.split(" -> ")

        val items = instructionParts[0].split(" ")
        when (items.size) {
            1 -> wireOperations[instructionParts[1]] = Pair("NULL", listOf(items[0]))
            2 -> wireOperations[instructionParts[1]] = Pair("NOT", listOf(items[1]))
            3 -> wireOperations[instructionParts[1]] = Pair(items[1], listOf(items[0], items[2]))
        }

    }
}
