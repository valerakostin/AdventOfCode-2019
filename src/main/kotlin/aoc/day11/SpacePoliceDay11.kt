package aoc.day11

import aoc.utils.Computer
import aoc.utils.Intcode

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


    private val computer = Computer(program, inputSupplier = { visitedPanels.getOrDefault(current.loc, 0).toLong() }, resumeOnOutput = true)

    fun move() {
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
}

fun main() {

    val program = Computer.ProgramReader.readProgram("InputDay11.txt")

    val task1 = task1(program)
    val task2 = task2()
    println(
            """
              Day11
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

fun task1(intcode: Intcode): Int {
    val robot = Robot(intcode)
    robot.move()
    return robot.visited()

}

fun task2() {}