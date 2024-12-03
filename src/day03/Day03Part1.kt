package day03

import java.io.File

fun main() {
    val testString = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"

    val lines = File("src/day03/Day03Part01_input").readLines()

    val sumResult = lines.sumOf { line -> calcLineSum(line) }

    println(sumResult)
}

fun calcLineSum(line: String): Int {
    val groups = Regex("mul\\(\\d+,\\d+\\)").findAll(line).map { it.groupValues[0] }.toList()
    return sumOfGroups(groups)
}

fun sumOfGroups(groups: List<String>): Int =
    groups.sumOf { valueString ->
        Regex("\\d+,\\d+")
            .find(valueString)!!
            .groupValues
            .map { value ->
                value.split(",")
            }.sumOf { pair ->
                pair[0].toInt() * pair[1].toInt()
            }
    }
