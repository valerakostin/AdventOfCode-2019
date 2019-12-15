package aoc.day11

import aoc.utils.Computer
import aoc.utils.Intcode

private fun Int?.isWhite() = this == 1

typealias PanelColor = Int

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

data class Position(val loc: Loc, val dir: Direction) {
    private val TURN_LEFT = 0
    fun move(m: Int): Position {
        when (dir) {
            Direction.UP -> {
                return if (m == TURN_LEFT)
                    Position(Loc(loc.x - 1, loc.y), Direction.LEFT)
                else
                    Position(Loc(loc.x + 1, loc.y), Direction.RIGHT)

            }
            Direction.LEFT -> {
                return if (m == TURN_LEFT)
                    Position(Loc(loc.x, loc.y + 1), Direction.DOWN)
                else
                    Position(Loc(loc.x, loc.y - 1), Direction.UP)
            }
            Direction.DOWN -> {
                return if (m == TURN_LEFT)
                    Position(Loc(loc.x + 1, loc.y), Direction.RIGHT)
                else
                    Position(Loc(loc.x - 1, loc.y), Direction.LEFT)

            }
            Direction.RIGHT -> {
                return if (m == TURN_LEFT)
                    Position(Loc(loc.x, loc.y - 1), Direction.UP)
                else
                    Position(Loc(loc.x, loc.y + 1), Direction.DOWN)
            }
        }
    }
}


data class Loc(val x: Int, val y: Int)
class Robot(val program: Intcode) {
    private val visitedPanels = mutableMapOf<Loc, PanelColor>()
    private var current = Position(Loc(0, 0), Direction.UP)


    fun move(initialColor: Int = 0) {
        visitedPanels[Loc(0, 0)] = initialColor
        val computer = Computer(program, inputSupplier = { visitedPanels.getOrDefault(current.loc, 0).toLong() }, resumeOnOutput = true)
        computer.execute()
        while (!computer.isReady()) {
            val color = computer.output()
            visitedPanels[current.loc] = color.toInt()
            computer.resume()
            val direction = computer.output()
            current = current.move(direction.toInt())
            computer.resume()
        }
    }

    fun visited(): Int {
        return visitedPanels.keys.size
    }


    fun draw() {
        val minX = visitedPanels.keys.map { it.x }.min()!!
        val maxX = visitedPanels.keys.map { it.x }.max()!!

        val minY = visitedPanels.keys.map { it.y }.min()!!
        val maxY = visitedPanels.keys.map { it.y }.max()!!

        for (j in minY..maxY) {
            for (i in minX..maxX) {
                val loc = Loc(i, j)
                val color = visitedPanels[loc]

                if (color.isWhite())
                    print("\uD83D\uDD35")
                else
                    print("➖")
            }
            println()
        }
    }
}

fun main() {
    val task1 = task1(Computer.ProgramReader.readProgram("InputDay11.txt"))
    println(
            """
              Day11
               Task1: $task1
               Task2:
            """.trimIndent())
    task2(Computer.ProgramReader.readProgram("InputDay11.txt"))
}

fun task1(intcode: Intcode): Int {
    val robot = Robot(intcode)
    robot.move()
    return robot.visited()
}

fun task2(intcode: Intcode) {
    val robot = Robot(intcode)
    robot.move(initialColor = 1)
    robot.draw()
}