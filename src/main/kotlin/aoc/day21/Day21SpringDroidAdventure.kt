package aoc.day21

import aoc.utils.Computer
import aoc.utils.Intcode

fun main() {

    val program = Computer.ProgramReader.readProgram("InputDay21.txt")

    val task1 = task1(program)
    val task2 = task2()

    println(
            """
              Day21
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

internal fun task1(program: Intcode): Long {

    val script = """
    NOT A J 
    NOT C T
    AND D T
    OR T J
    WALK
            
""".trimIndent()

    fun input(`in`: Int): Long {
        val inputOffset = 20
        val char = script[`in` - inputOffset]
        return char.toLong()
    }

    val computer = Computer(program, resumeOnOutput = true, inputSupplier = ::input)
    while (!computer.isReady()) {
        computer.resume()
    }
    return computer.output()
}

fun task2() {}