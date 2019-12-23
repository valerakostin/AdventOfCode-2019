package aoc.day17

import aoc.utils.Computer

typealias Element = Char


fun main() {
    val sceneData = collectSceneData()
    drawScene(sceneData)

    val task1 = task1(sceneData)

    val input = task2(sceneData)
    val robot = Robot(input)
    val task2 = robot.execute()
    println(
            """
              Day17
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

fun task1(sceneData: Map<Loc, Element>): Int {
    return sceneData.keys.filter { sceneData.keys.containsAll(it.neighbours()) }.map { it.x * it.y }.sum()
}

fun task2(sceneData: Map<Loc, Element>): String {
    val steps = collectSteps(sceneData)

    val path = steps.joinToString(separator = ",") { it.toString() }
    println(path)

    //TODO implement zip algorithm
    //   L,12,R,4,R,4,R,12,R,4,L,12,R,12,R,4,L,12,R,12,R,4,L,6,L,8,L,8,R,12,R,4,L,6,L,8,L,8,L,12,R,4,R,4,L,12,R,4,R,4,R,12,R,4,L,12,R,12,R,4,L,12,R,12,R,4,L,6,L,8,L,8
    return "A,B,B,C,C,A,A,B,B,C\nL,12,R,4,R,4\nR,12,R,4,L,12\nR,12,R,4,L,6,L,8,L,8\nn\n"
}

private fun collectSteps(sceneData: Map<Loc, Element>): MutableList<Step> {
    val path = sceneData.keys.toMutableSet()

    val robot = sceneData.entries.first { it.value.isRobot() }
    path.remove(robot.key)
    var lastVector = getInitialDirection(robot.key, sceneData[robot.key])

    var currentPoint = Loc(robot.key.x, robot.key.y)
    val calibration = sceneData.keys.filter { sceneData.keys.containsAll(it.neighbours()) }.toSet()

    val steps = mutableListOf<Step>()
    while (path.isNotEmpty()) {
        val moveFunction = currentPoint.getNeighbourFunction(path)
        val segments = currentPoint.move(path, calibration, moveFunction)
        path.removeAll(segments)
        currentPoint = segments.last()

        val currentVector = Vector(segments[0], segments.last())
        val direction = lastVector.direction(currentVector)
        steps.add(Step(direction, segments.size))
        lastVector = currentVector
    }
    return steps
}


enum class Direction {
    LEFT, RIGHT;

    override fun toString(): String {
        return this.name[0].toString()
    }
}


data class Step(val direction: Direction, val steps: Int) {
    override fun toString(): String {
        return "$direction,$steps"
    }
}

data class Vector(val s: Loc, val e: Loc) {
    fun direction(another: Vector): Direction {
        val x = s.x - e.x
        val y = s.y - e.y

        val x1 = another.s.x - another.e.x
        val y1 = another.s.y - another.e.y
        val dot = x * -y1 + y * x1
        if (dot > 0)
            return Direction.RIGHT
        return Direction.LEFT
    }
}

fun Element.isEmpty() = this == '.'
fun Char.isRobot() = this == '^' || this == 'v' || this == '<' || this == '>'
fun Char.isNewLine() = this.toInt() == 10

internal fun collectSceneData(): Map<Loc, Element> {
    val program = Computer.ProgramReader.readProgram("InputDay17.txt")
    val computer = Computer(program)
    computer.execute()
    val outputs = computer.outputs()
    return collectSceneData(outputs)
}

class Robot(private var input: String) {
    private var index = 0
    fun execute(): Long {
        val program = Computer.ProgramReader.readProgram("InputDay17.txt")
        program[0] = 2
        val computer = Computer(program, inputSupplier = ::inputSupplier)
        computer.execute()
        val outputs = computer.outputs()
        val joinToString = outputs.map { it.toChar() }.joinToString(separator = "")
        println(joinToString)
        return computer.output()
    }

    private fun inputSupplier(i: Int): Long {
        val c = input[index]
        index += 1
        return c.toLong()
    }
}

fun drawScene(sceneData: Map<Loc, Element>) {
    val maxX = sceneData.keys.maxBy { it.x }?.x ?: 0
    val maxY = sceneData.keys.maxBy { it.y }?.y ?: 0

    for (x in 0..maxX) {
        for (y in 0..maxY) {
            val loc = Loc(x, y)
            val c = sceneData[loc]
            if (c == null) {
                print('.')
            } else {
                print(c)
            }
        }
        println()
    }
}

private fun collectSceneData(outputs: MutableList<Long>): Map<Loc, Element> {
    val sceneData = mutableMapOf<Loc, Element>()
    var row = 0
    var column = 0

    for (index in 0 until outputs.size) {
        val ch = outputs[index].toChar()
        if (!ch.isEmpty()) {
            if (ch.isNewLine()) {
                row += 1
                column = 0
            } else {
                sceneData[Loc(row, column)] = ch
                column += 1
            }
        } else {
            column += 1
        }
    }
    return sceneData
}

fun getInitialDirection(key: Loc, robotDirection: Element?): Vector {
    return when (robotDirection) {
        '^' -> Vector(key, Loc(key.x, key.y - 1))
        'V' -> Vector(key, Loc(key.x, key.y + 1))
        '<' -> Vector(key, Loc(key.x - 1, key.y))
        '>' -> Vector(key, Loc(key.x + 1, key.y))
        else -> throw IllegalArgumentException()
    }
}

data class Loc(val x: Int, val y: Int) {
    fun neighbours(): List<Loc> {
        val neighbours = mutableListOf<Loc>()
        if (x - 1 >= 0)
            neighbours.add(Loc(x - 1, y))
        neighbours.add(Loc(x + 1, y))
        if (y - 1 >= 0)
            neighbours.add(Loc(x, y - 1))
        neighbours.add(Loc(x, y + 1))
        return neighbours
    }


    fun move(path: Set<Loc>, crossPoints: Set<Loc>, moveFunction: (Loc) -> Loc): List<Loc> {
        val pathSegment = mutableListOf<Loc>()
        var next = this
        while (true) {
            val loc = moveFunction(next)
            if (path.contains(loc) || crossPoints.contains(loc)) {
                pathSegment.add(loc)
                next = loc
            } else
                break
        }
        return pathSegment
    }

    fun getNeighbourFunction(path: Set<Loc>): (Loc) -> Loc {

        when {
            path.contains(Loc(this.x - 1, this.y)) -> return { Loc(it.x - 1, it.y) }
            path.contains(Loc(this.x + 1, this.y)) -> return { Loc(it.x + 1, it.y) }
            path.contains(Loc(this.x, this.y - 1)) -> return { Loc(it.x, it.y - 1) }
            path.contains(Loc(this.x, this.y + 1)) -> return { Loc(it.x, it.y + 1) }
            else -> throw IllegalArgumentException()
        }
    }
}

