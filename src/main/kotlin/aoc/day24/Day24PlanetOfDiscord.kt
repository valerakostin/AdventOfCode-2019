package aoc.day24

import aoc.utils.Utils
import kotlin.math.pow

data class Loc(val x: Int, val y: Int)

data class State(val width: Int, val height: Int, val bugs: Set<Loc>) {

    fun nextState(): State {
        val newLocations = mutableSetOf<Loc>()

        for (i in 0 until height) {
            for (j in 0 until width) {
                val currentLoc = Loc(i, j)
                if (bugs.contains(currentLoc)) {
                    if (liveInNextStep(currentLoc))
                        newLocations.add(currentLoc)
                } else {
                    if (infectedInNextStep(currentLoc)) {
                        newLocations.add(currentLoc)
                    }
                }
            }
        }
        return State(width, height, newLocations)
    }

    fun computeBioDiversityRating(): Int {
        var sum = 0
        for (i in 0 until width) {
            for (j in 0 until height) {
                if (bugs.contains(Loc(i, j))) {
                    val position = width * i + j
                    val value = 2.toDouble().pow(position.toDouble())
                    sum += value.toInt()
                }
            }
        }
        return sum
    }

    private fun liveInNextStep(loc: Loc): Boolean {
        val count = bugNeighbours(loc)
        return count == 1
    }

    private fun infectedInNextStep(loc: Loc): Boolean {
        val count = bugNeighbours(loc)
        return count == 1 || count == 2
    }

    private fun bugNeighbours(loc: Loc): Int {
        val list = listOf(Loc(loc.x - 1, loc.y), Loc(loc.x + 1, loc.y), Loc(loc.x, loc.y - 1), Loc(loc.x, loc.y + 1))
        return list.filter { bugs.contains(it) }.count()
    }


    fun printState() {
        for (i in 0 until width) {
            for (j in 0 until height) {
                if (bugs.contains(Loc(i, j)))
                    print("#")
                else
                    print('.')
            }
            println()
        }
    }
}


fun main() {

    val lines =
            Utils.linesFromResource("InputDay24.txt")
    val initialState = lines.parseState()

    val task1 = task1(initialState)
    val task2 = task2()

    println(
            """
              Day24
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

fun List<String>.parseState(): State {
    val height = this.size
    val width = this[0].length
    val locations = mutableSetOf<Loc>()
    for (i in 0 until height) {
        for (j in 0 until width) {
            if (this[i][j] == '#')
                locations.add(Loc(i, j))
        }
    }
    return State(width, height, locations)
}

fun task1(initialState: State): Int {
    val states = LinkedHashSet<State>()
    states.add(initialState)
    var state = initialState
    while (true) {
        state = state.nextState()

        if (states.contains(state)) {
            state.printState()
            return state.computeBioDiversityRating()
        } else
            states.add(state)
    }
}

fun task2() {}