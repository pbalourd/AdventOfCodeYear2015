package bp.aoc2015.day19

import java.io.File
import kotlin.random.Random

fun main() {
    val input = File("src/main/kotlin/bp/aoc2015/day19/input19.txt").readLines()

    val replacements = input
        .takeWhile { it.isNotBlank() }
        .map { it.split(" => ") }

    val initialMolecule = input.last()

    val molecules = mutableSetOf<String>()
    for (rep in replacements) {
        var index = initialMolecule.indexOf(rep.first())
        while (index != -1) {
            molecules.add(initialMolecule.replaceRange(index,index + rep.first().length, rep.last()))
            index = initialMolecule.indexOf(rep.first(), index + 1)
        }
    }
    println(molecules.size)
    //Solution 576

    val start = "e"
    var steps = 0
    var molecule = initialMolecule
    while (molecule != start) {
        val replacement = replacements[Random.nextInt(0,replacements.lastIndex)]
        val index = molecule.indexOf(replacement.last())
        if (index != -1) {
            molecule = molecule.replaceRange(index, index + replacement.last().length, replacement.first())
            println(molecule)
            steps++
        }
    }

    println(steps)
    //Solution 207
}