package aoc.day13

import aoc.utils.Computer
import aoc.utils.Intcode

fun main() {
    val program = Computer.ProgramReader.readProgram("InputDay13.txt")
    println("Input $program")
    val task1 = task1(program)
    val task2 = task2()

    println(
            """
              Day13
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

data class Loc(val x: Int, val y: Int)

fun Long.isBlock() = this == 2L

fun task1(program: Intcode): Int {
    val computer = Computer(program)
    computer.execute()
    val outputs = computer.outputs()
    val size = outputs.size / 3
    return (0 until size).map { outputs[it * 3 + 2] }.count { it.isBlock() }
}

fun task2() {}