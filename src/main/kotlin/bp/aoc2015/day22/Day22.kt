package bp.aoc2015.day22

import kotlin.math.max
import kotlin.math.min

const val BOSS_HIT_POINTS = 71
const val BOSS_DAMAGE = 10
const val PLAYER_HIT_POINTS = 50
const val PLAYER_MANA_POINTS = 500

const val MISSILE_COST = 53
const val MISSILE_DAMAGE = 4
const val DRAIN_COST = 73
const val DRAIN_DAMAGE = 2
const val DRAIN_HEAL = 2
const val SHIELD_COST = 113
const val SHIELD_ARMOR = 7
const val SHIELD_TURNS = 6
const val POISON_COST = 173
const val POISON_DAMAGE = 3
const val POISON_TURNS = 6
const val RECHARGE_COST = 229
const val RECHARGE_MANA = 101
const val RECHARGE_TURNS = 5

fun main() {
    val leastMana1 = playerTurn(BOSS_HIT_POINTS, PLAYER_HIT_POINTS, PLAYER_MANA_POINTS, 0, 0, 0, 0, false)
    println(leastMana1)
    //Solution 1824

    val leastMana2 = playerTurn(BOSS_HIT_POINTS, PLAYER_HIT_POINTS, PLAYER_MANA_POINTS, 0, 0, 0, 0, true)
    println(leastMana2)
    //Solution 1937
}

fun bossTurn(bossHitPoints: Int, playerHitPoints: Int, playerManaPoints: Int,
             spentMana: Int, shield: Int, poison: Int, recharge: Int, isHard: Boolean): Int {
    var result = Int.MAX_VALUE

    val poisonDamage = if (poison > 0) POISON_DAMAGE else 0
    if (bossHitPoints - poisonDamage <= 0) {
        return spentMana
    }

    val hit = BOSS_DAMAGE - (if (shield > 0) SHIELD_ARMOR else 0)
    val hitPoints = playerHitPoints - hit
    if (hitPoints > 0) {
        val recharged = if (recharge > 0) RECHARGE_MANA else 0
        result = playerTurn(
            bossHitPoints - poisonDamage, hitPoints, playerManaPoints + recharged,
            spentMana, max(shield - 1, 0), max(poison - 1, 0), max(recharge - 1, 0), isHard
        )
    }

    return result
}

fun playerTurn(bossHitPoints: Int, playerHitPoints: Int, playerManaPoints: Int,
               spentMana: Int, shield: Int, poison: Int, recharge: Int, isHard: Boolean): Int {
    var result = Int.MAX_VALUE

    val newPlayerHitPoint = playerHitPoints - (if (isHard) 1 else 0)
    if (newPlayerHitPoint <= 0) return result

    val newManaPoints = playerManaPoints + (if (recharge > 0) RECHARGE_MANA else 0)

    // Magic Missile
    if (newManaPoints >= MISSILE_COST) {
        val damage = MISSILE_DAMAGE + (if (poison > 0) POISON_DAMAGE else 0)
        result = if (bossHitPoints - damage <= 0) {
            spentMana + MISSILE_COST
        } else {
            bossTurn(bossHitPoints - damage, newPlayerHitPoint, newManaPoints - MISSILE_COST,
                spentMana + MISSILE_COST, max(shield - 1, 0), max(poison - 1, 0), max(recharge - 1, 0), isHard)
        }
    }

    // Drain
    if (newManaPoints >= DRAIN_COST) {
        val damage = DRAIN_DAMAGE  + (if (poison > 0) POISON_DAMAGE else 0)
        val r = if (bossHitPoints - damage <= 0) {
            spentMana + DRAIN_COST
        } else {
            bossTurn(bossHitPoints - damage, newPlayerHitPoint + DRAIN_HEAL, newManaPoints - DRAIN_COST,
                spentMana + DRAIN_COST, max(shield - 1, 0), max(poison - 1, 0), max(recharge - 1, 0), isHard)
        }
        result = min(result, r)
    }

    // Shield
    if (newManaPoints >= SHIELD_COST && shield == 0) {
        val damage = if (poison > 0) POISON_DAMAGE else 0
        val r = if (bossHitPoints - damage <= 0) {
            spentMana + SHIELD_COST
        } else {
            bossTurn(bossHitPoints - damage, newPlayerHitPoint, newManaPoints - SHIELD_COST,
                spentMana + SHIELD_COST, SHIELD_TURNS - 1, max(poison - 1, 0), max(recharge - 1, 0), isHard)
        }
        result = min(result, r)
    }

    // Poison
    if (newManaPoints >= POISON_COST && poison == 0) {
        val damage = POISON_DAMAGE
        val r = if (bossHitPoints - damage <= 0) {
            spentMana + POISON_COST
        } else {
            bossTurn(bossHitPoints - damage, newPlayerHitPoint, newManaPoints - POISON_COST,
                spentMana + POISON_COST, max(shield - 1, 0), POISON_TURNS - 1, max(recharge - 1, 0), isHard)
        }
        result = min(result, r)
    }

    // Recharge
    if (newManaPoints >= RECHARGE_COST && recharge == 0) {
        val damage = if (poison > 0) POISON_DAMAGE else 0
        val r = if (bossHitPoints - damage <= 0) {
            spentMana + RECHARGE_COST
        } else {
            bossTurn(bossHitPoints - damage, newPlayerHitPoint, newManaPoints - RECHARGE_COST,
                spentMana + RECHARGE_COST, max(shield - 1, 0), max(poison - 1, 0), RECHARGE_TURNS, isHard)
        }
        result = min(result, r)
    }

    return result
}