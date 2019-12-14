package aoc.day09

import aoc.utils.Computer
import aoc.utils.Intcode

fun main() {

    val program = Computer.ProgramReader.readProgram("InputDay09.txt")
    val task1 = computeBoostCode(program, 1)

    val program2 = Computer.ProgramReader.readProgram("InputDay09.txt")
    val task2 = computeBoostCode(program2, 2)

    println(
            """
              Day09
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

fun computeBoostCode(program: Intcode, input: Long): Long {
    val c = Computer(program, inputSupplier = { input })
    c.execute()
    return c.output()
}
