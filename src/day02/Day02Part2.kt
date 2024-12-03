package day02

import java.io.File

fun main() {
    val lines = File("src/day02/day02_input").readLines()
    val count =
        lines
            .map { line -> line.split(" ").map { it.toInt() } }
            .map { line -> removeFirstError(line.toMutableList()) }
            .filter { line -> allIncreasing(line) || allDecreasing(line) }
            .count { line -> correctMaxDistance(line) }

    println("Counted safe logs:")
    println(count)
}

fun removeFirstError(line: MutableList<Int>): List<Int> {
    var errorIndex = 0
    for (x in line.indices) {
        if (x == 0 || x == line.lastIndex) continue
        if (line[x] == line[x - 1]) {
            errorIndex = x
            break
        }
        if (line[x - 1] < line[x] && line[x] > line[x + 1]) {
            errorIndex = x
            break
        }
        if (line[x - 1] > line[x] && line[x] < line[x + 1]) {
            errorIndex = x
            break
        }
        if (kotlin.math.abs(line[x] - line[x - 1]) > 3) {
            errorIndex = x
            break
        }
    }
    if (errorIndex != 0) {
        line.removeAt(errorIndex)
    }
    return line
}
