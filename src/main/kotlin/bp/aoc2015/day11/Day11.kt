package bp.aoc2015.day11

import java.io.File

fun main() {
    val password = File("src/main/kotlin/bp/aoc2015/day11/input11.txt").readText()

    val firstPassword = getNextPassword(password)
    println(firstPassword)
    // Solution hepxxyzz

    val secondPassword = getNextPassword(firstPassword)
    println(secondPassword)
}

fun getNextPassword(password: String): String {
    val p = password.toMutableList()
    while (true) {
        incrementPassword(p)
        if (notContainIOL(p) && straightLetters(p) && hasTwoPairs(p)) break
    }
    return p.joinToString("")
}

fun incrementPassword(password: MutableList<Char>) {
    var pos = password.size - 1
    var carry = 1
    while (carry == 1 && pos > 0) {
        password[pos]++
        if (password[pos] == '{') {
            password[pos] = 'a'
            carry = 1
        } else carry = 0
        pos--
    }
}

fun notContainIOL(password: MutableList<Char>): Boolean {
    return !(password.contains('i') || password.contains('o') || password.contains('o'))
}

fun straightLetters(password: MutableList<Char>): Boolean {
    for (i in 0..5) {
        if (password[i] + 1 == password[i + 1] && password[i] + 2 == password[i + 2]) return true
    }
    return false
}

fun hasTwoPairs(password: MutableList<Char>): Boolean {
    for (i in 0..6) {
        if (password[i] == password[i + 1]) {
            for (j in i + 2..6) {
                if (password[j] == password[j + 1])
                    return true
            }
        }
    }
    return false
}
