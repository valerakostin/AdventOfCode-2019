package aoc.day02

import aoc.utils.Computer
import aoc.utils.Intcode



fun main() {
    val program= Computer.ProgramReader.readProgram("InputDay02.txt")

    val task1 = task1(program)
    val task2 = task2()

    println(
            """
              Day02
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}


fun task1(program: Intcode): Int {
    program[1] = 12
    program[2] = 2
    val computer = Computer(program)
    computer.execute()
    return program[0]
}

/**
0,0 - 493_708   // initial value
1,0 - 736_708   // initial value + 243_000 * [1]
2,0 - 979_708   // initial value + 243_000 * [1]
3,0 - 1_222_708 // initial value + 243_000 * [1]

initial value = 493_708
diff = 243_000
fist input = x * 243_000
second input = value

result = 19_690_720
result = 493708 + first * 243_000 + second
19_690_720 - 493_708 = first * 243_000 +second
19_197_012= first * 243_000 +second
first = 19_197_012 % 243_000 = 79
second =19_197_012 - 243_000 * first = 12

 **/
fun task2() = 100 * 79 + 12
