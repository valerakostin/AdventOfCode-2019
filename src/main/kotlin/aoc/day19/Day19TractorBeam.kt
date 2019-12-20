package aoc.day19

import aoc.utils.Computer
import aoc.utils.Intcode

fun main() {

    val program = Computer.ProgramReader.readProgram("InputDay19.txt")
    val task1 = task1(program)
    val task2 = task2(program)

    println(
            """
              Day19
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

fun Intcode.copy(): Intcode {
    val copy = mutableMapOf<Int, Long>()
    copy.putAll(this)
    return copy
}

fun task1(program: Intcode): Int {

    var counter = 0
    for (i in 0..49) {
        for (j in 0..49) {
            val v = getFieldValue(program, i, j)
            if (v == 1L)
                counter += 1
        }
    }
    return counter
}

private fun getFieldValue(program: Intcode, i: Int, j: Int): Long {
    val copy = program.copy()
    val computer = Computer(copy, inputSupplier = { if (it == 0) i.toLong() else j.toLong() })
    computer.execute()
    return computer.output()
}


fun task2(program: Intcode): Int {
    val fieldSide = 99
    for (j in 500..2_000) {
        for (i in 400..2_000) {
            val leftCorner = getFieldValue(program, i, j)
            if (leftCorner == 1L) {
                if ((getFieldValue(program, i + fieldSide, j)) == 1L) {
                    val rightTopCorner = getFieldValue(program, i + fieldSide, j - fieldSide)
                    if (rightTopCorner == 1L) {
                        // left top corner i, j -fieldSide
                        return i * 10_000 + (j - fieldSide)
                    } else break
                } else break
            }
        }
    }
    return 0
}
