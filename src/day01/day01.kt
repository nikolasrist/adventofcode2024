import java.io.File

fun main() {
    val (left, right) = parseInputToSortedLists("src/day01/20241201_input")
    println("Result Distance: ")
    println(calcDistance(left, right))

    println("Result Count Distance: ")
    println(calcAppearanceDistance(left, right))
}

fun parseInputToSortedLists(filePath: String): Pair<List<Int>, List<Int>> {
    val lines = File(filePath).readLines()
    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()

    lines.map { line ->
        val (first, second) = line.split(",").map { it.toInt() }
        leftList.add(first)
        rightList.add(second)
    }
    return Pair(leftList.sorted(), rightList.sorted())
}

fun calcDistance(
    left: List<Int>,
    right: List<Int>,
): Int = left.mapIndexed { index, i -> kotlin.math.abs(i - right[index]) }.sum()

fun calcAppearanceDistance(
    left: List<Int>,
    right: List<Int>,
): Int = left.sumOf { i -> i * right.count { it == i } }
