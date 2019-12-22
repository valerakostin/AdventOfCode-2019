package aoc.day22

import aoc.utils.Utils

interface Operation {
    fun indexOf(index: Int, size: Int): Int

    object Parser {
        fun parseOperations(lines: List<String>): List<Operation> {
            val operations = mutableListOf<Operation>()
            for (line in lines) {
                when {
                    "deal into new stack" == line -> operations.add(NewStack)
                    line.startsWith("cut") -> {
                        val indexOf = line.lastIndexOf(" ")
                        val steps = line.substring(indexOf + 1, line.length).toInt()
                        operations.add(Cut(steps))
                    }
                    line.startsWith("deal with increment") -> {
                        val indexOf = line.lastIndexOf(" ")
                        val steps = line.substring(indexOf + 1, line.length).toInt()
                        operations.add(Increment(steps))
                    }
                }
            }
            return operations
        }
    }
}

object NewStack : Operation {
    override fun indexOf(index: Int, size: Int) = size - index - 1
}

class Increment(val inc: Int) : Operation {
    override fun indexOf(index: Int, size: Int) = (index * inc) % size
}

class Cut(private val shift: Int) : Operation {
    override fun indexOf(index: Int, size: Int) = (size + index - shift) % size
}

fun main() {

    val lines =
            Utils.linesFromResource("InputDay22.txt")
    val operations = Operation.Parser.parseOperations(lines)

    val task1 = task1(operations)
    val task2 = task2()

    println(
            """
              Day22
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

internal fun indexOf(index: Int, size: Int, operations: List<Operation>): Int {
    return operations.fold(index) { inx, op -> op.indexOf(inx, size) }
}

fun task1(operations: List<Operation>): Int {
    return indexOf(2019, 10007, operations)
}

fun task2() {}
