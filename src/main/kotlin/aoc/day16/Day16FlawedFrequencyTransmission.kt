package aoc.day16

import kotlin.math.abs

fun main() {

    val input = "59728776137831964407973962002190906766322659303479564518502254685706025795824872901465838782474078135479504351754597318603898249365886373257507600323820091333924823533976723324070520961217627430323336204524247721593859226704485849491418129908885940064664115882392043975997862502832791753443475733972832341211432322108298512512553114533929906718683734211778737511609226184538973092804715035096933160826733751936056316586618837326144846607181591957802127283758478256860673616576061374687104534470102346796536051507583471850382678959394486801952841777641763547422116981527264877636892414006855332078225310912793451227305425976335026620670455240087933409".map { Character.getNumericValue(it) }.toList()
    val task1 = task1(input)
    val task2 = task2()
    println(
            """
              Day16
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

fun task2() {
    for (phase in 1..30) {
        for (index in 1..30) {
            val value = getValue(phase, index)
            print(value.toString().padStart(2, ' '))
        }
        println()
    }
    // todo compute backwards

    // last = last[n]
    // last -1 = (last[n-1] + last[n])
    // etc

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

