package bp.aoc2015.day13

import bp.aoc2015.utils.IntListPermutations
import java.io.File

fun main() {
    val associations = File("src/main/kotlin/bp/aoc2015/day13/input13.txt").readLines()
        .map { line -> line.split(" would ", " happiness units by sitting next to ", " ") }
        .associate { data ->
            Pair(
                data[0],
                data[3].substring(0, data[3].length - 1)
            ) to data[2].toInt() * (if (data[1] == "gain") 1 else -1)
        }

    val people = associations.keys.map { it.first }.distinct()

    println(maximumHappiness(people, associations))
    //Solution 664

    val peopleWithMe = mutableListOf("Me")
    peopleWithMe.addAll(people)

    val associationsWithMe = mutableMapOf<Pair<String, String>, Int>()
    associationsWithMe += associations
    for (p in people) {
        associationsWithMe[Pair(p, "Me")] = 0
        associationsWithMe[Pair("Me", p)] = 0
    }

    println(maximumHappiness(peopleWithMe, associationsWithMe))
    //Solution 640
}

private fun maximumHappiness(people: List<String>, associations: Map<Pair<String, String>, Int>): Int {
    val numOfPeople = people.size
    val permutations = IntListPermutations(numOfPeople)
    var maxHappiness = 0

    for (per in permutations) {
        var happiness = associations[Pair(people[per[0]], people[per[numOfPeople - 1]])]!! +
                associations[Pair(people[per[numOfPeople - 1]], people[per[0]])]!!
        for (i in 0..numOfPeople - 2) {
            happiness += associations[Pair(people[per[i]], people[per[i + 1]])]!! +
                    associations[Pair(people[per[i + 1]], people[per[i]])]!!
        }
        if (happiness > maxHappiness) maxHappiness = happiness
    }
    return maxHappiness
}
