package bp.aoc2015.utils

class IntListPermutations(private val n: Int): Iterator<IntArray> {
    private var i = 0
    private val indexes = IntArray(n)
    private val elements = IntArray(n) { it }

    var totalPermutations = factorial(n)
    private var turn = 0L

    override fun hasNext() = turn < totalPermutations

    override fun next(): IntArray {
        turn++
        if (turn == 1L) return elements
        while (i < n) {
            if (indexes[i] < i) {
                swap(if (i % 2 == 0) 0 else indexes[i], i)
                indexes[i]++
                i = 0
                break
            } else {
                indexes[i] = 0
                i++
            }
        }
        return elements
    }

    private fun swap(k: Int, l: Int) {
        val tmp = elements[k]
        elements[k] = elements[l]
        elements[l] = tmp
    }

    fun reset() {
        i = 0
        for (d in 0..indexes.lastIndex) indexes[d] = 0
        for (d in 0..elements.lastIndex) elements[d] = d
        turn = 0L
    }

    private fun factorial(n: Int): Long {
        var i = 1
        var factorial: Long = 1
        while (i <= n) {
            factorial *= i.toLong()
            i++
        }
        return factorial
    }
}