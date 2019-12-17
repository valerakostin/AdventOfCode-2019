package aoc.day17

import aoc.utils.Computer

typealias Element = Char

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
}

fun Char.isEmpty() = this == '.'
fun Char.isNewLine() = this.toInt() == 10


fun main() {
    val outputs = getProgramOutputs()
    printProgramOutput(outputs)

    val sceneData = collectSceneData(outputs)
    drawScene(sceneData)

    val task1 = task1(sceneData)
    val task2 = task2()

    println(
            """
              Day17
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

fun printProgramOutput(outputs: MutableList<Long>) {
    println("Program output")
    for (item in outputs) {
        if (item == 10L)
            println()
        else
            print(item.toChar())
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

private fun getProgramOutputs(): MutableList<Long> {
    val program = Computer.ProgramReader.readProgram("InputDay17.txt")
    val computer = Computer(program)
    computer.execute()
    return computer.outputs()
}

fun task1(sceneData: Map<Loc, Element>): Int {
    return sceneData.keys.filter { sceneData.keys.containsAll(it.neighbours()) }.map { it.x * it.y }.sum()
}

fun task2() {}