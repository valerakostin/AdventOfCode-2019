package aoc.day04

typealias Password = String

fun main() {
    val range = 347312..805915
    val task1 = task1(range)
    val task2 = task2(range)

    println(
            """
              Day04
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

fun task1(range: IntRange): Int {
    return compute(range) { it.hasAdjustedNumbers() && it.isOrdered() }
}

fun task2(range: IntRange): Int {
    return compute(range) { it.has2AdjustedNumbers() && it.isOrdered() }
}

private fun compute(range: IntRange, filter: (Password) -> Boolean): Int {
    return range
            .map { it.asPassword() }
            .filter(filter)
            .count()
}

fun Int.asPassword() = this.toString()

fun Password.hasAdjustedNumbers(): Boolean {
    return (0 until this.length - 1).any { this[it] == this[it + 1] }
}

fun Password.isOrdered(): Boolean {
    return (1 until this.length).all { this[it - 1] <= this[it] }
}

fun Password.has2AdjustedNumbers(): Boolean {
    return this.groupingBy { it }.eachCount().values.contains(2)
}

