package bp.aoc2015.day14

import java.io.File

const val TIME_DURATION = 2503

data class flyData(val speed: Int, val flyTime: Int, val restTime: Int)

fun main() {
    val reindeerFlyData = File("src/main/kotlin/bp/aoc2015/day14/input14.txt").readLines()
        .map { line -> line.substringBefore(" seconds.").split(" can fly ", " km/s for ", " seconds, but then must rest for " ) }
        .map { data -> flyData(data[1].toInt(), data[2].toInt(), data[3].toInt()) }

    var maxDistance = 0
    reindeerFlyData.forEach { data ->
        val loopTime = data.flyTime + data.restTime
        val totalLoops = (TIME_DURATION - 1) / loopTime
        val residualTime = (TIME_DURATION - 1) % loopTime
        val distance = totalLoops * data.speed * data.flyTime +
                if (residualTime < data.flyTime) data.speed * residualTime
                else data.speed * data.flyTime
        if (distance > maxDistance) maxDistance = distance
    }
    println(maxDistance)
    //Solution 2640

    val points = MutableList<Int>(reindeerFlyData.size) { 0 }
    val distances = MutableList<Int>(reindeerFlyData.size) { 0 }
    for (second in 0 until TIME_DURATION) {
        for ((index, data) in reindeerFlyData.withIndex()) {
            val loopTime = data.flyTime + data.restTime
            val residualTime = second % loopTime
            if (residualTime < data.flyTime) distances[index] += data.speed
        }
        val maxOfDistances = distances.maxBy { it }
        distances.forEachIndexed() { index, distance ->
            if (distance == maxOfDistances) {
                points[index]++
            }
        }
    }

    println(points.maxBy { it })
    //Solution 1102
}