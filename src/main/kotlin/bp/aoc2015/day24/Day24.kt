package bp.aoc2015.day24

import bp.aoc2015.utils.combinations
import java.io.File

fun main() {
    val packages = File("src/main/kotlin/bp/aoc2015/day24/input24.txt").readLines()
        .map { line -> line.toInt() }

    val numOfPackages = packages.size
    val sameWeight1 = packages.sum() / 3

    val tempPackages = mutableListOf<Int>()

    var minPackages = numOfPackages
    var minEntanglement = Long.MAX_VALUE

    for (i in 1..numOfPackages - 2) {
        if (i > minPackages) break

        for (group1 in packages.combinations(i)) {
            if (group1.sum() != sameWeight1) continue

            tempPackages.clear()
            for (p in packages) tempPackages.add(p)
            group1.forEach { tempPackages.remove(it) }

            label1@ for (j in 1..numOfPackages - 1 - i) {

                for (group2 in tempPackages.combinations(j)) {
                    if (group2.sum() != sameWeight1) continue

                    if (i < minPackages) minPackages = i

                    val qe = group1.fold(1L) { acc, d -> acc * d }
                    if (minEntanglement > qe) minEntanglement = qe

                    break@label1
                }
            }
        }
    }
    println(minEntanglement)
    //Solution 10439961859

    val sameWeight2 = packages.sum() / 4
    val tempPackages1 = mutableListOf<Int>()
    val tempPackages2 = mutableListOf<Int>()
    minPackages = numOfPackages
    minEntanglement = Long.MAX_VALUE

    for (i in 1..numOfPackages - 3) {
        if (i > minPackages) break
        for (group1 in packages.combinations(i)) {
            if (group1.sum() != sameWeight2) continue

            tempPackages1.clear()
            for (p in packages) tempPackages1.add(p)
            group1.forEach { tempPackages1.remove(it) }

            label2@ for (j in 1..numOfPackages - 2 - i) {
                for (group2 in tempPackages1.combinations(j)) {
                    if (group2.sum() != sameWeight2) continue

                    tempPackages2.clear()
                    for (p in tempPackages1) tempPackages2.add(p)
                    group2.forEach { tempPackages2.remove(it) }

                    for (k in 1..numOfPackages - 1 - i - j) {
                        for (group3 in tempPackages2.combinations(k)) {
                            if (group3.sum() != sameWeight2) continue

                            if (i < minPackages) minPackages = i

                            val qe = group1.fold(1L) { acc, d -> acc * d }
                            if (minEntanglement > qe) minEntanglement = qe

                            break@label2
                        }
                    }
                }
            }
        }
    }
    println(minEntanglement)
    //Solution 72050269
}