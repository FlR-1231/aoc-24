fun main() {
    val inputString = """

    """.trimIndent()

    val (parsedLeft, parsedRight) = parseToSortedLists(inputString, "   ")
    
    val listOfDistances = mutableListOf<Int>()
    
    parsedLeft.zip(parsedRight) { left, right -> listOfDistances.add(kotlin.math.abs(left-right)) }
    
    println("Distance List: ${listOfDistances.sum()}")
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
