package day03

import java.io.File

//
private var includeValue = true

//
fun main() {
//    val testString = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))]!^don't()_mul(5,5)+mul(32,64](mul(11,8)]!^don't()_mul(5,5)+mul(32,64](mul(11,8)"
//    println(extractValidMuls(testString))

    val lines = File("src/day03/Day03Part01_input").readLines()
    val sumResult = lines.sumOf { line -> extractValidMuls(line) }
    println("184122457")
    println("108609098")
    println(sumResult)
}

fun extractValidMuls(line: String): Int {
    val groups = Regex("mul\\(\\d+,\\d+\\)|don't\\(\\)|do\\(\\)").findAll(line).map { it.groupValues[0] }.toList()
    val matchingGroups = mutableListOf<String>()
    groups.forEach { group ->
        run {
            when (group) {
                "don't()" -> includeValue = false
                "do()" -> includeValue = true
            }
            if (group.startsWith("mul(") && includeValue) {
                matchingGroups.add(group)
            }
        }
    }
    return sumOfGroups(matchingGroups)
}
