package aoc.day05

import aoc.Computer
import aoc.Intcode
import aoc.utils.Utils


fun main() {
    val task1 = task1(readProgram())
    val task2 = task2(readProgram())

    println(
            """
              Day05
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

private fun readProgram(): MutableList<Int> {
    return Utils.lineFromResource("InputDay05.txt")
            .split(",")
            .map { it.toInt() }
            .toMutableList()
}


fun task1(program: Intcode): Int {
    val c = Computer(program, 1)
    c.execute()
    return c.output()
}


fun task2(program: Intcode): Int {

    val c = Computer(program, 5)
    c.execute()
    return c.output()
}

