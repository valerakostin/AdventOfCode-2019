package aoc.day07

import aoc.utils.Computer
import java.util.*


fun main() {

    val program = Computer.ProgramReader.readProgram("InputDay07.txt")

    val task1 = task1(program)
    println(task1)
    val task2 = task2(program)

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

fun task2(program: MutableList<Int>): Pair<Int, List<Int>>? {

    val sequence = mutableListOf(5, 6, 7, 8, 9)
    val permute = permute(sequence)
    return permute.map { Pair(processSequenceWithFeedback(program, it), it) }.maxBy { it.first }
}

internal fun processSequenceWithFeedback(program: MutableList<Int>, sequence: List<Int>): Int {

    val amps = (0 until sequence.size)
            .map { Computer(program.toMutableList(), resumeOnOutput = true) }.toList()

    (0 until sequence.size).map {
        amps[it].inputSupplier = createSupplier(amps, it, sequence[it])
    }

    while (!amps.all { it.isReady() }) {
        amps.filter { !it.isReady() }.map { it.resume(); it.execute() }
    }
    return amps.last().output()
}

private fun createSupplier(amps: List<Computer>, index: Int, phase: Int): (Int) -> Int {
    return if (index == 0) {
        { i: Int ->
            when {
                i == 1 -> 0
                i % 2 == 0 -> phase
                else -> amps[amps.size - 1].output()
            }
        }
    } else {
        { i: Int ->
            when {
                i % 2 == 0 -> phase
                else -> amps[index - 1].output()
            }
        }
    }
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

