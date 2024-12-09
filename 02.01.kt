fun main() {
    val inputString = """
    
    """.trimIndent()

    val result = parseInputToLists(inputString)
    
    var amountOfValidReports = 0
    for (list in result) {
        val listOfChange = mutableListOf<Int>()

        var isValid = true
        list.zipWithNext().forEach { (current, next) ->
            if (isTolerable(current, next)) {
                listOfChange.add(current - next)
            } else {
                isValid = false
                return@forEach
            }
        }

        if (isValid && isAllPositiveOrAllNegative(listOfChange)) {
            amountOfValidReports++
        }
    }

    println("There are $amountOfValidReports valid reports.")
}

fun isAllPositiveOrAllNegative(list: List<Int>): Boolean = list.all { it > 0 } || list.all { it < 0 }

fun isTolerable(one: Int, two: Int): Boolean = one != two && kotlin.math.abs(one - two) in 1..3

fun parseInputToLists(input: String): List<List<Int>> = 
    input.lines()
        .map { row ->
            row.split(" ")
                .map { it.toInt() }
        }
