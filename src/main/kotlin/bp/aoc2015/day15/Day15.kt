package bp.aoc2015.day15

import java.io.File

const val TEASPOONS = 100

fun main() {
    val recipes = File("src/main/kotlin/bp/aoc2015/day15/input15.txt").readLines()
        .map { line -> line.split(": capacity ", ", durability ", ", flavor ", ", texture ", ", calories ") }
        .associate { data -> data[0] to data.toMutableList().subList(1,6).map { it.toInt() }.toList()}

    var maxScore = 0
    var maxScoreWith500Cal = 0
    for (sugar in 0..TEASPOONS) {
        for (sprinkles in 0..TEASPOONS - sugar) {
            for (candy in 0.. TEASPOONS - sugar - sprinkles) {
                val chocolate = TEASPOONS - sugar - sprinkles - candy

                val counts = IntArray(4)
                for (i in 0..counts.lastIndex) {
                    counts[i] = sugar * recipes["Sugar"]!![i] +
                            sprinkles * recipes["Sprinkles"]!![i] +
                            candy * recipes["Candy"]!![i] +
                            chocolate * recipes["Chocolate"]!![i]
                    if (counts[i] < 0) counts[i] = 0
                }

                val score = counts.fold(1) { mul, item -> mul * item }
                if (score > maxScore) maxScore = score

                val calories = sugar * recipes["Sugar"]!![4] +
                        sprinkles * recipes["Sprinkles"]!![4] +
                        candy * recipes["Candy"]!![4] +
                        chocolate * recipes["Chocolate"]!![4]
                if (calories == 500 && score > maxScoreWith500Cal) maxScoreWith500Cal = score
            }
        }
    }
    println(maxScore)
    //Solution 222870

    println(maxScoreWith500Cal)
    //Solution 117936
}
