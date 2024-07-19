package bp.aoc2015.day18

import java.io.File

fun main() {
    var grid = File("src/main/kotlin/bp/aoc2015/day18/input18.txt")
        .readLines()
        .map { line ->
            line.toList()
                .map { ch -> ch =='#' }
        }

    val ySize = grid.size
    val xSize = grid[0].size

    val numOfSteps = 100

    var gridCopy = List(ySize) { i -> List(xSize) { j -> grid[i][j]} }

    repeat(numOfSteps) {
        grid = step(grid, false)
    }

    println(grid.flatten().count { it })
    //Solution 768

    repeat(numOfSteps) {
        gridCopy = step(gridCopy, true)
    }

    println(gridCopy.flatten().count { it })
    //Solution 781
}

private fun step(grid: List<List<Boolean>>, stuckOn: Boolean): List<List<Boolean>> {
    val ySize = grid.size
    val xSize = grid[0].size
    val gridNew = List(ySize) { MutableList(xSize) { true } }
    for (y in 0..<ySize) {
        for (x in 0..<xSize) {
            var sum = 0
            for (i in -1..1) {
                for (j in -1..1) {
                    if (!(i == 0 && j == 0) && y + i in 0..<ySize && x + j in 0..<xSize && grid[y + i][x + j])
                        sum++
                }
            }
            gridNew[y][x] = (grid[y][x] && sum in 2..3) || (!grid[y][x] && sum == 3)
        }
    }
    if (stuckOn) {
        gridNew[0][0] = true
        gridNew[ySize - 1][0] = true
        gridNew[0][xSize - 1] = true
        gridNew[ySize - 1][xSize - 1] = true
    }
    return gridNew
}

fun printGrid(grid: List<List<Boolean>>) {
    for (y in grid.indices) {
        for (x in grid[0].indices) {
            if (grid[y][x]) print('#') else print('.')
        }
        println()
    }
}