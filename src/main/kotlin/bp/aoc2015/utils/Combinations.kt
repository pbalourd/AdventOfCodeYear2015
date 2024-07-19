package bp.aoc2015.utils

fun <T> Collection<T>.combinations(combinationSize: Int): Sequence<List<T>> {
    val list = this@combinations.toList()
    val collectionSize = list.size
    if (combinationSize < 0 || combinationSize > collectionSize) throw IllegalArgumentException("Wrong argument value")
    val indices = IntArray(combinationSize) { it }
    return sequence {
        while (true) {
            yield(indices.map { list[it] })
            var i = combinationSize
            do {
                i--
                if (i == -1) return@sequence
            } while (indices[i] == i + collectionSize - combinationSize)
            indices[i]++
            for (j in i + 1 until combinationSize) indices[j] = indices[j - 1] + 1
        }
    }
}