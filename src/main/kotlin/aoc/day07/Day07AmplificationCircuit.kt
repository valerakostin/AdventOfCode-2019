package aoc.day07

import aoc.utils.Computer
import java.util.*


fun main() {

    val program = Computer.ProgramReader.readProgram("InputDay07.txt")

    val task1 = task1(program)
    val task2 = task2(program)

    println(
            """
              Day07
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}


fun task1(program: MutableMap<Int, Long>): Pair<Int, List<Long>>? {
    val sequence = mutableListOf(0L, 1L, 2L, 3L, 4L)
    val permute = permute(sequence)
    return permute.map { Pair(processSequence(program, it), it) }.maxBy { it.first }
}

fun task2(program: MutableMap<Int, Long>): Pair<Long, List<Long>>? {

    val sequence = mutableListOf(5L, 6L, 7L, 8L, 9L)
    val permute = permute(sequence)
    return permute.map { Pair(processSequenceWithFeedback(program, it), it) }.maxBy { it.first }
}

internal fun processSequenceWithFeedback(program: MutableMap<Int, Long>, sequence: List<Long>): Long {

    val amps = (0 until sequence.size)
            .map { Computer(program.toMutableMap(), resumeOnOutput = true) }.toList()

    (0 until sequence.size).map {
        amps[it].inputSupplier = createSupplier(amps, it, sequence[it])
    }

    while (!amps.all { it.isReady() }) {
        amps.filter { !it.isReady() }.map { it.resume(); it.execute() }
    }
    return amps.last().output()
}

private fun createSupplier(amps: List<Computer>, index: Int, phase: Long): (Int) -> Long {
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

internal fun processSequence(program: MutableMap<Int, Long>, sequence: List<Long>): Int {
    val iterator = sequence.iterator()
    val computer = Computer(program.toMutableMap(), inputSupplier = { if (it == 0) iterator.next() else 0 })
    computer.execute()
    var output = computer.output()
    repeat(4)
    {
        val comp = Computer(program.toMutableMap(), inputSupplier = { if (it == 0) iterator.next() else output })
        comp.execute()
        output = comp.output()
    }
    return output.toInt()
}

private fun permute(num: MutableList<Long>): List<List<Long>> {
    var result = mutableListOf<MutableList<Long>>()

    result.add(mutableListOf())

    for (i in num.indices) {
        val current = mutableListOf<MutableList<Long>>()

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

