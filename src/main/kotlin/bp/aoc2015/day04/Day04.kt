package bp.aoc2015.day04

import java.security.MessageDigest

fun main() {
    val key = "ckczppom"
    var pattern = "00000"
    println(findPattern(key, pattern))
    // Solution 117946

    pattern = "000000"
    println(findPattern(key, pattern))
    // Solution 3938038
}

fun findPattern(key: String, pattern: String): Long {
    var isFound = false
    var num = 0L
    while (!isFound) {
        num++
        val str = key + num.toString()
        val md5 = hashStringMd5(str)
        if (md5.substring(0, pattern.length) == pattern) {
            isFound = true
        }
    }
    return num
}

fun hashStringMd5(input: String): String {
    val bytes = MessageDigest
        .getInstance("MD5")
        .digest(input.toByteArray())
    return byteArrayToHexString(bytes)
}

fun byteArrayToHexString(bytes: ByteArray): String {
    return buildString {
        bytes.forEach {byte: Byte -> append(String.format("%02X", byte)) }
    }
}