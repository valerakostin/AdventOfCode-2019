package aoc.day05

import aoc.utils.Computer
import aoc.utils.Intcode


fun main() {

    val task1 = task1(Computer.ProgramReader.readProgram("InputDay05.txt"))
    val task2 = task2(Computer.ProgramReader.readProgram("InputDay05.txt"))

    println(
            """
              Day05
               Task1: $task1
               Task2: $task2
            """.trimIndent())
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

