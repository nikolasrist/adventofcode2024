package day05

import java.io.File

fun main() {
    val lines = File("src/day05/Day05Part02_input").readLines()
    val rules = lines.filter { it.contains("|") }.map { it.split("|") }.map { listOf(it[0].toInt(), it[1].toInt()) }
    val updates = lines.filter { it.contains(",") }.map { it.split(",") }.map { valueList -> valueList.map { it.toInt() } }

    val wrongOrderedUpdates =
        updates
            .filter { updateLine ->
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
                    !valid
                }
            }.map { updateLine ->
                run {
                    val updateArray = updateLine.toIntArray()
                    for (i in updateLine.indices) {
                        run {
                            val applyingRules = rules.filter { it[0] == updateLine[i] }
                            for (rule in applyingRules) {
                                val baseIndex = updateArray.indexOf(rule[0])
                                val foundIndex = updateArray.indexOf(rule[1])
                                if (foundIndex != -1 && foundIndex < baseIndex) {
                                    val tempValue = updateArray[baseIndex]
                                    updateArray[baseIndex] = updateArray[foundIndex]
                                    updateArray[foundIndex] = tempValue
                                }
//                                println(
//                                    "${updateArray.toList()} - Rule: $rule - Value:${updateArray[i]} - i: $baseIndex - foundIndex:$foundIndex",
//                                )
                            }
                        }
                    }
                    updateArray.toList()
                }
            }

    val sum = wrongOrderedUpdates.sumOf { it[it.size / 2] }

    println(wrongOrderedUpdates)
    println(sum)
}
