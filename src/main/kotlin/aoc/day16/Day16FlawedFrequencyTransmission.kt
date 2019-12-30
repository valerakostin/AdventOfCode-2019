package aoc.day16

fun main() {

    var input = "59728776137831964407973962002190906766322659303479564518502254685706025795824872901465838782474078135479504351754597318603898249365886373257507600323820091333924823533976723324070520961217627430323336204524247721593859226704485849491418129908885940064664115882392043975997862502832791753443475733972832341211432322108298512512553114533929906718683734211778737511609226184538973092804715035096933160826733751936056316586618837326144846607181591957802127283758478256860673616576061374687104534470102346796536051507583471850382678959394486801952841777641763547422116981527264877636892414006855332078225310912793451227305425976335026620670455240087933409".map { Character.getNumericValue(it) }.toList()
    val pattern = listOf(0, 1, 0, -1)
    val task1 = task1(input, pattern)
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

}

private fun generateList(step: Int, size: Int, pattern: List<Int>): List<Int> {
    val list = mutableListOf<Int>()

    repeat(step - 1)
    {
        list.add(pattern[0])
        if (list.size > size)
            return list
    }
    repeat(step)
    {
        list.add(pattern[1])
        if (list.size > size)
            return list
    }
    repeat(step)
    {
        list.add(pattern[2])
        if (list.size > size)
            return list
    }
    repeat(step)
    {
        list.add(pattern[3])
        if (list.size > size)
            return list
    }

    while (list.size < size) {
        repeat(step)
        {
            list.add(pattern[0])
            if (list.size > size)
                return list
        }
        repeat(step)
        {
            list.add(pattern[1])
            if (list.size > size)
                return list
        }
        repeat(step)
        {
            list.add(pattern[2])
            if (list.size > size)
                return list
        }
        repeat(step)
        {
            list.add(pattern[3])
            if (list.size > size)
                return list
        }
    }
    return list

}

fun getValue(phase: Int, index: Int): Int {
    val value = ((index) / phase) % 4
    return when (value) {
        0 -> 0
        1 -> 1
        2 -> 0
        3 -> -1
        else -> throw IllegalArgumentException(value.toString())
    }
}

fun task1(initial: List<Int>, pattern: List<Int>): String {
    var input = mutableListOf<Int>()
    input.addAll(initial)

    var phase = 1
    repeat(100) {
        val l = buildString {
            for (i in input.indices) {

                 val p = generateList(i + 1, input.size, pattern)

                val v = input.zip(p)
                        .map { it.first * it.second }
                        .sum().toString().last()
                append(Character.getNumericValue(v))
            }
        }
        input = l.map { Character.getNumericValue(it) }.toMutableList()
        //    println(input.joinToString(separator = ""))
        phase += 1
    }
    return input.joinToString(separator = "").substring(0, 8)
}
