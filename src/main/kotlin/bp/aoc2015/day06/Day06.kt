package bp.aoc2015.day06

import java.io.File

fun main() {
    val lineList = File("src/main/kotlin/bp/aoc2015/day06/input06.txt")
        .readLines()

    val grid = Array(1000) {BooleanArray(1000)}

    for (line in lineList) {
        val (order, points) = getData(line)
        when (order) {
            "turn on" -> setState(grid, points, true)
            "turn off" -> setState(grid, points, false)
            "toggle" -> toggle(grid, points)
        }
    }

    println(grid.flatMap { booleanArray -> booleanArray.toList() }.count { it })
    //Solution 377891

    val gridBrightness = Array(1000) {IntArray(1000)}

    for (line in lineList) {
        val (order, points) = getData(line)
        when (order) {
            "turn on" -> setBrightness(gridBrightness, points, 1)
            "turn off" -> setBrightness(gridBrightness, points, -1)
            "toggle" -> setBrightness(gridBrightness, points, 2)
        }
    }

    println(gridBrightness.flatMap { intArray -> intArray.toList() }.sumOf { it })
    //Solution 14110788
}

fun setBrightness(gridBrightness: Array<IntArray>, points: List<Int>, n: Int) {
    for (i in points[0]..points[2])
        for (j in points[1]..points[3]) {
            gridBrightness[i][j] += n
            if (gridBrightness[i][j] < 0) gridBrightness[i][j] = 0
        }
}

fun setState(grid: Array<BooleanArray>, points: List<Int>, state: Boolean) {
    for (i in points[0]..points[2])
        for (j in points[1]..points[3])
            grid[i][j] = state
}

fun toggle(grid: Array<BooleanArray>, points: List<Int>) {
    for (i in points[0]..points[2])
        for (j in points[1]..points[3])
            grid[i][j] = !grid[i][j]
}

fun getData(line: String): Pair<String, List<Int>> {
    val startingString = line.takeWhile { !it.isDigit() }
    val order = startingString.substring(0, startingString.length - 1)
    val points = line.substring(startingString.length)
    val k = points.split(" through ", ",").map { it.toInt() }

    return Pair(order, k)
}
