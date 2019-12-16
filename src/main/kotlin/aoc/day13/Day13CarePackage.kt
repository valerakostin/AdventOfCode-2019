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

fun Long.isEmpty() = this == 0L
fun Long.isWall() = this == 1L
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

class Game {
    private var ball: Loc = Loc(0, 0)
    private var paddle: Loc = Loc(0, 0)
    private var score: Long = 0

    private val points: MutableMap<Loc, Long> = mutableMapOf()


    fun play(program: Intcode, visualization: Boolean = false): Int {
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

            if (visualization) {
                val loc = Loc(output1.toInt(), output2.toInt())
                points[loc] = output3
                draw()
            }
        }
        return score.toInt()
    }

    private fun isScoreOutput(output1: Long, output2: Long) = output1 == -1L && output2 == 0L

    private fun joystick(counter: Int) = ball.x.compareTo(paddle.x).toLong()

    private fun draw() {
        println()
        println()
        val maxX = points.keys.map { it.x }.max()!!
        val maxY = points.keys.map { it.y }.max()!!

        for (j in 0 until maxY) {
            for (i in 0..maxX) {
                val loc = Loc(i, j)
                val type = points[loc]
                if (type != null) {
                    when {
                        type.isEmpty() -> print("➖️")
                        type.isWall() -> print("\uD83D\uDEA7")
                        type.isBlock() -> print("♦️")
                        type.isPaddle() -> print("\uD83C\uDFD3")
                        type.isBall() -> print("⚽️")
                    }
                }
            }
            println()
        }
        println("Score $score")
    }
}

fun task2(program: Intcode): Int {
    val game = Game()
    return game.play(program)
}


