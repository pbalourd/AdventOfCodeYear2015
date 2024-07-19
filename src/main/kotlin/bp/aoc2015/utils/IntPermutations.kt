package bp.aoc2015.utils

class IntPermutations(private val n: Int): Iterator<Int> {
    private var i = 0
    private val indexes = IntArray(n)
    private val elements = IntArray(n) { it }

    var totalPermutations = factorial(n) * n
    private var turn = 0L
    private var elementIndex = 0

    override fun hasNext() = turn < totalPermutations

    override fun next(): Int {
        turn++
        if (elementIndex == n) {
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
            elementIndex = 0
        }
        val a = elements[elementIndex]
        elementIndex++
        return a
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
        elementIndex = 0
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