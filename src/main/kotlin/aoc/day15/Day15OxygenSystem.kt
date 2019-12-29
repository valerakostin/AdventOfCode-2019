package aoc.day15

import aoc.utils.Computer
import aoc.utils.Intcode


typealias  Path = MutableList<Loc>

enum class Direction(val numericRepresentation: Int) {
    NORTH(1), SOUTH(2), WEST(3), EAST(4);

    fun opposite(): Direction {
        return when (this) {
            NORTH -> SOUTH
            SOUTH -> NORTH
            EAST -> WEST
            WEST -> EAST
        }
    }
}

enum class FieldType {
    WALL, SPACE, OXYGEN_SYSTEM;

    object Parser {
        fun parse(input: Long): FieldType {
            return when (input) {
                0L -> WALL
                1L -> SPACE
                2L -> OXYGEN_SYSTEM
                else -> throw IllegalArgumentException(input.toString())
            }
        }
    }
}

fun main() {

    val program = Computer.ProgramReader.readProgram("InputDay15.txt")
    val field = Field(program)
    val droid = Droid(field)
    droid.buildMaze()
    val path = droid.paths().minBy { it.size }
    if (path != null) {

        droid.field.draw(path)
        val task1 = path.size
        val task2 = field.fillWithOxygen()

        println(
                """
              Day15
               Task1: $task1
               Task2: $task2
            """.trimIndent())
    }
}

class Droid(val field: Field) {
    private val directions = listOf(Direction.WEST, Direction.SOUTH, Direction.EAST, Direction.NORTH)


    fun buildMaze() {
        val initialLocation = Loc(0, 0)
        movement(initialLocation)
    }

    private fun movement(location: Loc) {
        for (direction in directions) {
            val newLoc = location.forward(direction)
            if (!field.isVisited(newLoc)) {
                val type = field.location(newLoc, direction)
                if (type != FieldType.WALL) {
                    movement(newLoc)
                    field.moveBackward(direction)
                }
            }
        }
    }

    fun paths() = field.allPaths()
}

data class Loc(val x: Int, val y: Int) {
    fun forward(dir: Direction): Loc {
        return when (dir) {
            Direction.NORTH -> Loc(this.x, this.y - 1)
            Direction.SOUTH -> Loc(this.x, this.y + 1)
            Direction.WEST -> Loc(this.x - 1, y)
            Direction.EAST -> Loc(this.x + 1, y)
        }
    }

    fun neighbours() =
            listOf(
                    Loc(this.x, this.y + 1),
                    Loc(this.x - 1, this.y),
                    Loc(this.x, this.y - 1),
                    Loc(this.x + 1, this.y))
}

class Field(program: Intcode) {
    private val computer = Computer(program, resumeOnOutput = true, inputSupplier = ::inputSupplier)
    private val cells = mutableMapOf<Loc, FieldType>()
    private val inputList = mutableListOf<Direction>()

    init {
        cells[Loc(0, 0)] = FieldType.SPACE
    }

    fun isVisited(loc: Loc) = loc in cells

    fun location(current: Loc, dir: Direction): FieldType {
        val cell = moveForward(dir)
        cells[current] = cell
        return cell
    }

    private fun inputSupplier(i: Int): Long {
        val firstElement = inputList.removeAt(0)
        return firstElement.numericRepresentation.toLong()
    }

    private fun moveForward(direction: Direction): FieldType {
        return move(direction)
    }

    fun moveBackward(direction: Direction): FieldType {
        return move(direction.opposite())
    }

    private fun move(direction: Direction): FieldType {
        inputList.add(direction)
        computer.resume()
        val output = computer.output()
        return FieldType.Parser.parse(output)
    }

    private fun isNeighbour(loc: Loc): Boolean {
        val fieldType = cells[loc]
        return fieldType == FieldType.SPACE || fieldType == FieldType.OXYGEN_SYSTEM
    }

    fun allPaths(): List<Path> {
        val paths = mutableListOf<Path>()
        val visited = mutableSetOf<Loc>()

        fun traversePaths(from: Loc, to: Loc, path: Path) {
            visited.add(from)
            if (from == to) {
                paths.add(path.toMutableList())
                visited.remove(from)
            }

            val neighbours = from.neighbours().filter { isNeighbour(it) }

            for (loc in neighbours) {
                if (loc !in visited) {
                    path.add(loc)
                    traversePaths(loc, to, path)
                    path.remove(loc)
                }
            }
        }

        val key = cells.entries.find { it.value == FieldType.OXYGEN_SYSTEM }?.key
        if (key != null) {
            traversePaths(Loc(0, 0), key, mutableListOf())
        }
        return paths
    }


    fun draw(path: Collection<Loc> = emptySet()) {
        println()
        val min = cells.keys.map { it.x }.min()!!
        val max = cells.keys.map { it.x }.max()!!

        val wall = "⛰"
        for (j in min..max) {
            for (i in min..max) {
                val loc = Loc(i, j)
                val fieldType = cells[loc]
                if (fieldType != null) {
                    if (i == 0 && j == 0)
                        print("\uD83E\uDD16")
                    else if (fieldType == FieldType.OXYGEN_SYSTEM)
                        print("\uD83D\uDCCD")
                    else if (loc in path)
                        print("\uD83D\uDEF8")
                    else if (fieldType == FieldType.WALL)
                        print(wall)
                    else if (fieldType == FieldType.SPACE)
                        print("➖")


                } else {
                    print(wall)
                }
            }
            println()
        }
    }

    fun fillWithOxygen(): Int {
        val start = cells.entries.find { it.value == FieldType.OXYGEN_SYSTEM }?.key!!

        val cellsToFill = cells.entries
                .filter { it.value == FieldType.SPACE }
                .map { it.key }
                .toMutableSet()

        var steps = 0

        fun fillOxygen(workSet: Collection<Loc>) {
            while (cellsToFill.isNotEmpty()) {
                val nextLevel = workSet.flatMap { it.neighbours() }
                        .filter { it in cellsToFill }
                        .toSet()

                steps += 1
                cellsToFill.removeAll(workSet)

                fillOxygen(nextLevel)
            }
        }
        fillOxygen(listOf(start))
        return steps - 1
    }
}

