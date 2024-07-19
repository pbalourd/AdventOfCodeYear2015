package bp.aoc2015.day21

import java.io.File

data class Item(val name: String, val cost: Int, val damage: Int, val armor: Int)

fun main() {
    val weapons = listOf(
        Item("Dagger", 8,4, 0),
        Item("Shortsword ", 10, 5, 0),
        Item("Warhammer", 25, 6, 0),
        Item("Longsword", 40, 7, 0),
        Item("Greataxe", 74, 8, 0)
    )

    val armors = listOf(
        Item("Leather", 13, 0, 1),
        Item("Chainmail", 31, 0, 2),
        Item("Splintmail", 53, 0, 3),
        Item("Bandedmail", 75, 0, 4),
        Item("Platemail", 102, 0, 5),
        Item("None", 0, 0, 0)
    )

    val rings = listOf(
        Item("Damage +1", 25, 1, 0),
        Item("Damage +2", 50, 2, 0),
        Item("Damage +3", 100, 3, 0),
        Item("Defense +1", 20, 0, 1),
        Item("Defense +2", 40, 0, 2),
        Item("Defense +3", 80, 0, 3),
        Item("None", 0, 0, 0),
        Item("None", 0, 0, 0)
    )

    val input = File("src/main/kotlin/bp/aoc2015/day21/input21.txt").readLines()

    val bossHitPoints = input[0].substringAfter("Hit Points: ").toInt()
    val bossDamage = input[1].substringAfter("Damage: ").toInt()
    val bossArmor = input[2].substringAfter("Armor: ").toInt()

    val playerHitPoints = 100
    var minGold = Int.MAX_VALUE
    var maxGold = Int.MIN_VALUE

    for (weapon in weapons) {
        for (armor in  armors) {
            for ((index1, ring1) in rings.withIndex()) {
                for ((index2, ring2) in rings.withIndex()) {
                    if (index1 != index2) {
                        val playerDamage = weapon.damage + ring1.damage + ring2.damage
                        val playerArmor = armor.armor + ring1.armor + ring2.armor
                        var playerHitBoss = playerDamage - bossArmor
                        if (playerHitBoss <= 0) playerHitBoss = 1
                        var bossHitPlayer = bossDamage - playerArmor
                        if (bossHitPlayer <= 0) bossHitPlayer = 1
                        val bossTotalHits = (bossHitPoints / playerHitBoss) + (if (bossHitPoints % playerHitBoss != 0) 1 else 0)
                        val playerTotalHits = (playerHitPoints / bossHitPlayer) + (if (playerHitPoints % bossHitPlayer != 0) 1 else  0)
                        val sum = weapon.cost + armor.cost + ring1.cost + ring2.cost
                        if (bossTotalHits  <= playerTotalHits) {
                            if (sum < minGold) minGold = sum
                        } else {
                            if (sum > maxGold) maxGold = sum
                        }
                    }
                }
            }
        }
    }
    println(minGold)
    //Solution 121

    println(maxGold)
    //Solution 201
}