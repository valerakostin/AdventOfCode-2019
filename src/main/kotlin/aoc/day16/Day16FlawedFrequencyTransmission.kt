package aoc.day16

import aoc.utils.Utils
import kotlin.math.abs

fun main() {

    val input = Utils.lineFromResource("InputDay16.txt")
            .map { Character.getNumericValue(it) }
            .toList()

    val task1 = task1(input)
    val task2 = task2(input)
    println(
            """
              Day16
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

fun task1(initial: List<Int>): String {
    var input = initial

    repeat(100) {
        val results = mutableListOf<Int>()
        for (phase in 1..input.size) {
            var sum = 0
            for (index in 1..input.size) {
                sum += input[index - 1] * getValue(phase, index)
            }

            // lambda is 5 times slower
            //   val sum = input.withIndex()
            //          .map { it.value * getValue(i + 1, it.index + 1) }.sum()

            val digit = abs(sum) % 10
            results.add(digit)
        }
        input = results
    }
    return input.joinToString(separator = "").substring(0, 8)
}

fun task2(input: List<Int>): String {
    val offset = input.subList(0, 7).joinToString(separator = "").toInt()
    val listSize = input.size * 10_000
    val toCompute = listSize - offset
    val repeats = toCompute / input.size
    val lastNElementsInOriginalList = toCompute - repeats * input.size

    val prefix = input.subList(input.size - lastNElementsInOriginalList, input.size)
    val list = ArrayList<Int>(prefix)

    repeat(repeats)
    {
        list.addAll(input)
    }

    repeat(100)
    {
        for (i in list.size - 2 downTo 0)
            list[i] = list[i] + list[i + 1]

        for (i in 0 until list.size)
            list[i] = list[i] % 10
    }

    return list.joinToString(separator = "").substring(0, 8)
}

fun getValue(phase: Int, index: Int): Int {
    return when (val value = ((index) / phase) % 4) {
        0 -> 0
        1 -> 1
        2 -> 0
        3 -> -1
        else -> throw IllegalArgumentException(value.toString())
    }
}

