package day02

fun main() {
    val line = listOf(7, 6, 4, 2, 1)
    val line1 = listOf(1, 2, 7, 8, 9)
    val line2 = listOf(9, 7, 6, 2, 1)
    val line3 = listOf(1, 3, 2, 4, 5)
    val line4 = listOf(8, 6, 4, 4, 1)
    val line5 = listOf(1, 3, 6, 7, 9)

    println("Line = Result Increasing: ${allIncreasing(line)}")
    println("Line = Result Decreasing: ${allDecreasing(line)}")
    println("Line = Result correctMaxDistance: ${correctMaxDistance(line)}")

    println("Line1 = Result Increasing: ${allIncreasing(line1)}")
    println("Line1 = Result Decreasing: ${allDecreasing(line1)}")
    println("Line1 = Result correctMaxDistance: ${correctMaxDistance(line1)}")

    println("Line2 = Result Increasing: ${allIncreasing(line2)}")
    println("Line2 = Result Decreasing: ${allDecreasing(line2)}")
    println("Line2 = Result correctMaxDistance: ${correctMaxDistance(line2)}")

    println("Line3 = Result Increasing: ${allIncreasing(line3)}")
    println("Line3 = Result Decreasing: ${allDecreasing(line3)}")
    println("Line3 = Result correctMaxDistance: ${correctMaxDistance(line3)}")

    println("Line4 = Result Increasing: ${allIncreasing(line4)}")
    println("Line4 = Result Decreasing: ${allDecreasing(line4)}")
    println("Line4 = Result correctMaxDistance: ${correctMaxDistance(line4)}")

    println("Line5 = Result Increasing: ${allIncreasing(line5)}")
    println("Line5 = Result Decreasing: ${allDecreasing(line5)}")
    println("Line5 = Result correctMaxDistance: ${correctMaxDistance(line5)}")
}
