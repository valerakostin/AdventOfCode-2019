package aoc.day07

import aoc.utils.Computer
import aoc.utils.Utils
import java.util.*


fun main() {

    val program = Utils.lineFromResource("InputDay07.txt")
            .split(",")
            .map { it.toInt() }
            .toMutableList()

    val task1 = task1(program)
    println(task1)
    val task2 = null//task2(program)

    println(
            """
              Day07
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

fun task1(program: MutableList<Int>): Pair<Int, List<Int>>? {
    val sequence = mutableListOf(0, 1, 2, 3, 4)
    val permute = permute(sequence)
    return permute.map { Pair(processSequence(program, it), it) }.maxBy { it.first }
}

internal fun processSequence(program: MutableList<Int>, sequence: List<Int>): Int {
    val iterator = sequence.iterator()
    val computer = Computer(program.toMutableList(), inputSupplier = { if (it == 0) iterator.next() else 0 })
    computer.execute()
    var output = computer.output()
    repeat(4)
    {
        val comp = Computer(program.toMutableList(), inputSupplier = { if (it == 0) iterator.next() else output })
        comp.execute()
        output = comp.output()
    }
    return output
}

private fun permute(num: MutableList<Int>): List<List<Int>> {
    var result = mutableListOf<MutableList<Int>>()

    result.add(mutableListOf())

    for (i in num.indices) {
        val current = mutableListOf<MutableList<Int>>()

        for (l in result) {
            for (j in 0 until l.size + 1) {
                l.add(j, num[i])

                val temp = ArrayList(l)
                current.add(temp)
                l.removeAt(j)
            }
        }
        result = current.toMutableList()
    }

    return result
}

