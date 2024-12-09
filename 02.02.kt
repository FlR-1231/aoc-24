fun main() {
    val inputString = """

    """.trimIndent()

    val parsedInput = parseInputToLists(inputString)
    
    val amountOfValidReports: Int = parsedInput.count { isReportValid(it) || retryReport(it) }

    println("There are $amountOfValidReports valid reports.")
}

fun isReportValid(report: List<Int>): Boolean {
    val listOfChange = report.zipWithNext()
        .mapNotNull { (current, next) -> if (isTolerable(current, next)) current - next else null }

    return listOfChange.size == report.size - 1 && isAllPositiveOrAllNegative(listOfChange)
}

fun retryReport(report: List<Int>): Boolean = report.indices.any { i ->
        val modifiedReport = report.toMutableList().apply { removeAt(i) }
        isReportValid(modifiedReport)
    }

fun isAllPositiveOrAllNegative(list: List<Int>): Boolean = list.all { it > 0 } || list.all { it < 0 }

fun isTolerable(one: Int, two: Int): Boolean = one != two && kotlin.math.abs(one - two) in 1..3

fun parseInputToLists(input: String): List<List<Int>> = 
    input.lines()
        .map { row ->
            row.split(" ")
                .map { it.toInt() }
        }
