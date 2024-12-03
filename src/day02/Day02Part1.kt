package day02

import java.io.File

fun main() {
    val lines = File("src/day02/day02_test").readLines()
    val count =
        lines
            .map { line -> line.split(" ").map { it.toInt() } }
            .filter { line -> allIncreasing(line) || allDecreasing(line) }
            .count { line -> correctMaxDistance(line) }

    println("Counted safe logs:")
    println(count)
}

fun allIncreasing(input: List<Int>): Boolean =
    input.foldIndexed(0) { index, acc, value -> if (value > (input.getOrNull(index - 1) ?: -1)) acc else acc + 1 } == 0

fun allDecreasing(input: List<Int>): Boolean =
    input.foldIndexed(0) { index, acc, value -> if (value < (input.getOrNull(index - 1) ?: Int.MAX_VALUE)) acc else acc + 1 } == 0

fun correctMaxDistance(input: List<Int>): Boolean =
    input.foldIndexed(0) { index, acc, value ->
        if (index == 0) {
            acc
        } else {
            if (kotlin.math.abs(value - input[index - 1]) <= 3) {
                acc
            } else {
                acc + 1
            }
        }
    } == 0
