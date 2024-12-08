fun main() {
    val inputString = """

    """.trimIndent()

    val (parsedLeft, parsedRight) = parseToSortedLists(inputString, "   ")
    
    val similiarityPairs: MutableSet<Pair<Int, Int>> = mutableSetOf()
    
    parsedLeft.flatMap { entry ->
      	parsedRight.filter { it == entry }.map { entry }
  	}.forEach { addOrUpdate(it, similiarityPairs) }
    
    val similarityProducts = similiarityPairs.map { it.first * it.second}
    
    println("Similarity Sum: ${similarityProducts.sum()}")
}

fun addOrUpdate(key: Int, pairSet: MutableSet<Pair<Int, Int>>) {
    val existingPair = pairSet.firstOrNull { it.first == key }

    if (existingPair != null) {
        pairSet.remove(existingPair)
        pairSet.add(existingPair.copy(second = existingPair.second + 1))
    } else {
        pairSet.add(key to 1)
    }
}

fun parseToSortedLists(input: String, delimitier: String): Pair<List<Int>, List<Int>> {
    val lines = input.lines()

    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()

    for (line in lines) {
        val (left, right) = line.split(delimitier)
        leftList.add(left.toInt())
        rightList.add(right.toInt())
    }
    
    return Pair(leftList.sorted(), rightList.sorted())
}
