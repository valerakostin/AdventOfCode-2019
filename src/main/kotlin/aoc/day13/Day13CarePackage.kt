package aoc.day13

import aoc.utils.Computer
import aoc.utils.Intcode

fun main() {
    val task1 = task1(Computer.ProgramReader.readProgram("InputDay13.txt"))
    val task2 = task2(Computer.ProgramReader.readProgram("InputDay13.txt"))

    println(
            """
              Day13
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

data class Loc(val x: Int, val y: Int)

fun Long.isBlock() = this == 2L
fun Long.isBall() = this == 4L
fun Long.isPaddle() = this == 3L

fun task1(program: Intcode): Int {
    val computer = Computer(program)
    computer.execute()
    val outputs = computer.outputs()
    val size = outputs.size / 3
    return (0 until size).map { outputs[it * 3 + 2] }.count { it.isBlock() }
}

fun task2(program: Intcode): Int {
    val game = Game()
    return game.play(program)
}


class Game {
    private var ball: Loc = Loc(0, 0)
    private var paddle: Loc = Loc(0, 0)
    private var score: Long = 0

    fun play(program: Intcode): Int {
        program[0] = 2
        val c = Computer(program, inputSupplier = ::joystick, resumeOnOutput = true)
        c.execute()
        while (!c.isReady()) {
            val output1 = c.output()
            c.resume()
            val output2 = c.output()
            c.resume()
            val output3 = c.output()

            if (isScoreOutput(output1, output2))
                score = output3
            else if (output3.isBall())
                ball = Loc(output1.toInt(), output2.toInt())
            else if (output3.isPaddle())
                paddle = Loc(output1.toInt(), output2.toInt())
            c.resume()
        }
        return score.toInt()
    }

    private fun isScoreOutput(output1: Long, output2: Long) = output1 == -1L && output2 == 0L

    private fun joystick(counter: Int) = ball.x.compareTo(paddle.x).toLong()
}
