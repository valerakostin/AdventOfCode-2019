package aoc.day12

import kotlin.math.abs

data class Pos(var x: Int, var y: Int, var z: Int) {
    fun potEnergy() = abs(x) + abs(y) + abs(z)
}

data class Vel(var x: Int, var y: Int, var z: Int) {
    fun kinEnergy() = abs(x) + abs(y) + abs(z)
}

data class Item(val pos: Pos, val vel: Vel) {
    fun energy() = pos.potEnergy() * vel.kinEnergy()
}

data class State(val items: List<Item>) {
    fun nextStep(): State {
        for (i in 0 until 3) {
            for (j in i + 1 until 4) {
                val item1 = items[i]
                val item2 = items[j]

                if (item1.pos.x != item2.pos.x) {
                    if (item1.pos.x < item2.pos.x) {
                        item1.vel.x += 1
                        item2.vel.x += -1
                    } else {
                        item1.vel.x += -1
                        item2.vel.x += 1
                    }
                }
                if (item1.pos.y != item2.pos.y) {
                    if (item1.pos.y < item2.pos.y) {
                        item1.vel.y += 1
                        item2.vel.y += -1
                    } else {
                        item1.vel.y += -1
                        item2.vel.y += 1
                    }
                }
                if (item1.pos.z != item2.pos.z) {
                    if (item1.pos.z < item2.pos.z) {
                        item1.vel.z += 1
                        item2.vel.z += -1
                    } else {
                        item1.vel.z += -1
                        item2.vel.z += 1
                    }
                }
            }
        }

        val list = listOf(
                Item(Pos(items[0].pos.x + items[0].vel.x, items[0].pos.y + items[0].vel.y, items[0].pos.z + items[0].vel.z), items[0].vel),
                Item(Pos(items[1].pos.x + items[1].vel.x, items[1].pos.y + items[1].vel.y, items[1].pos.z + items[1].vel.z), items[1].vel),
                Item(Pos(items[2].pos.x + items[2].vel.x, items[2].pos.y + items[2].vel.y, items[2].pos.z + items[2].vel.z), items[2].vel),
                Item(Pos(items[3].pos.x + items[3].vel.x, items[3].pos.y + items[3].vel.y, items[3].pos.z + items[3].vel.z), items[3].vel))
        return State(list)
    }

    fun energy() = items.sumBy { it.energy() }

    fun print() {
        println("pos=<x=${items[0].pos.x}, y=${items[0].pos.y}, z=${items[0].pos.z}>, vel=<x=${items[0].vel.x}, y=${items[0].vel.y}, z=${items[0].vel.z}>")
        println("pos=<x=${items[1].pos.x}, y=${items[1].pos.y}, z=${items[1].pos.z}>, vel=<x=${items[1].vel.x}, y=${items[1].vel.y}, z=${items[1].vel.z}>")
        println("pos=<x=${items[2].pos.x}, y=${items[2].pos.y}, z=${items[2].pos.z}>, vel=<x=${items[2].vel.x}, y=${items[2].vel.y}, z=${items[2].vel.z}>")
        println("pos=<x=${items[3].pos.x}, y=${items[3].pos.y}, z=${items[3].pos.z}>, vel=<x=${items[3].vel.x}, y=${items[3].vel.y}, z=${items[3].vel.z}>")
        println()
    }
}


fun main() {

    val state = State(
            listOf(
                    Item(Pos(1, 2, -9), Vel(0, 0, 0)),
                    Item(Pos(-1, -9, -4), Vel(0, 0, 0)),
                    Item(Pos(17, 6, 8), Vel(0, 0, 0)),
                    Item(Pos(12, 4, 2), Vel(0, 0, 0))))

    var nextStep = state
    nextStep.print()
    repeat(1000) {
        nextStep = nextStep.nextStep()
    }

    val task1 = nextStep.energy()
    val task2 = task2()

    println(
            """
              Day12
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

fun task2() {}