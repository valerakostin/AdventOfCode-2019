package aoc.day02

import aoc.utils.Utils

typealias Intcode = MutableList<Int>

fun main() {
    val program = Utils.lineFromResource("InputDay02.txt")
            .split(",")
            .map { it.toInt() }
            .toMutableList()

    val task1 = task1(program)
    val task2 = task2()

    println(
            """
              Day02
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

class Computer(private val program: Intcode) {
    private var counter = 0

    fun execute() {
        while (!program[counter].isHaltOpcode()) {
            when (program[counter]) {
                1 -> addCommand()
                2 -> mulCommand()
                else -> throw IllegalArgumentException()
            }
        }
    }

    private fun executeCommand(op: (Int, Int) -> Int) {
        val operand1Address = program[counter + 1]
        val operand2Address = program[counter + 2]
        val resultAddress = program[counter + 3]
        program[resultAddress] = op(program[operand1Address], program[operand2Address])
        counter += 4
    }

    private fun addCommand() {
        executeCommand { o1, o2 -> o1 + o2 }
    }

    private fun mulCommand() {
        executeCommand { o1, o2 -> o1 * o2 }
    }
}

private fun Int.isHaltOpcode() = this == 99


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
