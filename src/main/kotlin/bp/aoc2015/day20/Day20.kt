package bp.aoc2015.day20

import java.io.File

fun main() {
    val presents = File("src/main/kotlin/bp/aoc2015/day20/input20.txt")
        .readText()
        .toInt()

    val maxHouse = presents / 10

    val houses = IntArray(maxHouse)
    var minHouse = maxHouse

    for (elfNum in 1..< maxHouse) {
        for (i in elfNum..< maxHouse step elfNum) {
            houses[i] += elfNum
            if (houses[i] >= maxHouse && i < minHouse) minHouse = i
        }
    }
    println(minHouse)
    //Solution 831600

    for (m in houses.indices) houses[m] = 0

    minHouse = maxHouse

    for (elfNum in 1..< maxHouse) {
        var housesServed = 0
        for (i in elfNum..< maxHouse step elfNum) {
            houses[i] += elfNum * 11
            if (houses[i] >= presents && i < minHouse) minHouse = i
            housesServed++
            if (housesServed == 50) break
        }
    }
    println(minHouse)
    //Solution 884520
}