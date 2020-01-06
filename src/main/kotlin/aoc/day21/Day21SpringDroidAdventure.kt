package aoc.day21

import aoc.utils.Computer
import aoc.utils.Intcode

fun main() {

    val program = Computer.ProgramReader.readProgram("InputDay21.txt")

    val task1 = task1(program)
    val program1 = Computer.ProgramReader.readProgram("InputDay21.txt")
    val task2 = task2(program1)

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

    return executeScript(script, program)
}

private fun executeScript(script: String, program: Intcode): Long {
    fun input(`in`: Int): Long {
        val inputOffset = 20
        val char = script[`in` - inputOffset]
        return char.toLong()
    }

    val computer = Computer(program, resumeOnOutput = true, inputSupplier = ::input)
    while (!computer.isReady()) {
        computer.resume()
       // print(computer.output().toChar())
    }
    return computer.output()
}

fun task2(program: Intcode): Long {
    val script = """ 
    OR A J
    AND B J
    AND C J
    NOT J J
    AND D J
    OR E T
    OR H T
    AND T J
    RUN
            
""".trimIndent()

    return executeScript(script, program)
}