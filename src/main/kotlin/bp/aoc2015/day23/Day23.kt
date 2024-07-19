package bp.aoc2015.day23

import java.io.File

data class CpuState(var programCounter: Int, var regA: Int, var regB: Int)

fun main() {
    val program = File("src/main/kotlin/bp/aoc2015/day23/input23.txt").readLines()
        .map{ line -> listOf(line.substring(0, 3), line.substring(4)) }

    val programLines = program.size

    for (a in 0..1) {
        val state = CpuState(0, a, 0)
        while (state.programCounter < programLines) {
            execution(state, program[state.programCounter])
        }
        println(state.regB)
    }
    //Solution 307
    //Solution 160

}

fun execution(s: CpuState, c: List<String>) {
     when (c[0]) {
         "hlf" -> {
             if (c[1] == "a") s.regA /= 2 else s.regB /= 2
             s.programCounter++
         }
         "tpl" -> {
             if (c[1] == "a") s.regA *= 3 else s.regB *= 3
             s.programCounter++
         }
         "inc" -> {
             if (c[1] == "a") s.regA++ else s.regB++
             s.programCounter++
         }
         "jmp" -> {
             val sign = if (c[1][0] == '+') 1 else -1
             s.programCounter += sign * c[1].substring(1).toInt()
         }
         "jie" -> {
             val (r, offset) = c[1].split(", ")
             val reg = if (r == "a") s.regA else s.regB
             val sign = if (offset[0] == '+') 1 else -1
             if (reg % 2 == 0) s.programCounter += sign * offset.substring(1).toInt()
             else s.programCounter++
         }
         "jio" -> {
             val (r, offset) = c[1].split(", ")
             val reg = if (r == "a") s.regA else s.regB
             val sign = if (offset[0] == '+') 1 else -1
             if (reg == 1) s.programCounter += sign * offset.substring(1).toInt()
             else s.programCounter++
         }
    }
}