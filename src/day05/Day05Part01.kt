package day05

import java.io.File

fun main() {
    val lines = File("src/day05/Day05_input").readLines()
    val rules = lines.filter { it.contains("|") }.map { it.split("|") }.map { listOf(it[0].toInt(), it[1].toInt()) }
    val updates = lines.filter { it.contains(",") }.map { it.split(",") }.map { valueList -> valueList.map { it.toInt() } }

    val correctOrderedUpdates =
        updates.filter { updateLine ->
            run {
                var valid = true
                updateLine.forEachIndexed { index, value ->
                    run {
                        val applyingRules = rules.filter { it[0] == value }
                        val violatingRule =
                            applyingRules.find { rule ->
                                val foundIndex = updateLine.indexOf(rule[1])
                                foundIndex != -1 && foundIndex < index
                            }
                        if (violatingRule != null) valid = false
                    }
                }
                valid
            }
        }

    val sum = correctOrderedUpdates.sumOf { it[it.size / 2] }

    println(correctOrderedUpdates)
    println(sum)
}
