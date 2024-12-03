package day02

fun main() {
    val line = listOf(7, 6, 4, 2, 1)
    val line1 = listOf(1, 2, 7, 8, 9)
    val line2 = listOf(9, 7, 6, 2, 1)
    val line3 = listOf(1, 3, 2, 4, 5)
    val line4 = listOf(8, 6, 4, 4, 1)
    val line5 = listOf(1, 3, 6, 7, 9)

    val updatedLine = removeFirstError(line5.toMutableList())
    println("Updated $updatedLine")
    println("Result Increasing: ${allIncreasing(updatedLine)}")
    println("Result Decreasing: ${allDecreasing(updatedLine)}")
    println("Result correctMaxDistance: ${correctMaxDistance(updatedLine)}")
}
