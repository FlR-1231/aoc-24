fun main() {
    val inputString = """
    
    """.trimIndent()

    val parsedInput = parseInputToLists(inputString)
    
    val amountOfValidReports = parsedInput.count { report ->
        val listOfChange = report.zipWithNext()
            .mapNotNull { (current, next) ->
                if (isTolerable(current, next)) current - next else null
            }
        listOfChange.size == report.size - 1 && isAllPositiveOrAllNegative(listOfChange)
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
