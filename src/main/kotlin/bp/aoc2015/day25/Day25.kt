package bp.aoc2015.day25

fun main() {
    val row = 3010
    val col = 3019

    val rankOfPassword = col + ((row + col - 1) * (row + col - 2)) /2

    var password = 20151125L

    repeat(rankOfPassword - 1) {
        password = (password * 252533) % 33554393
    }

    println(password)
    //Solution 8997277
}