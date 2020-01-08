package aoc.day23

import aoc.utils.Computer
import aoc.utils.Network
import java.lang.Thread.sleep

fun main() {

    val task1 = task1()
    val task2 = task2()

    println(
            """
              Day23
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

val inputQueue = mutableMapOf<Long, MutableList<Long>>()

private fun createComputer(id: Int): Computer {

    fun inputSupplier(`in`: Int): Long {
        return if (`in` == 0) {
            id.toLong()
        } else {
            val inputQueueList = inputQueue[id.toLong()]
            if (inputQueueList == null || inputQueueList.isEmpty())
                -1L
            else
                inputQueueList.removeAt(0)
        }
    }

    val program = Computer.ProgramReader.readProgram("InputDay23.txt")
    return Computer(program, inputSupplier = ::inputSupplier, network = Network(id, inputQueue))
}

fun task1(): Long {
    val networkSize = 50
    val natAddress = 255L
    for (i in 0 until networkSize) {
        inputQueue[i.toLong()] = mutableListOf()

        inputQueue[255L] = mutableListOf()
    }

    val network = (0 until networkSize).map { createComputer(it) }.toList()

    for (computer in network) {

        Thread(Runnable {
            if (!computer.isReady()) {
                computer.execute()
            }
        }).start()
    }

    while (inputQueue[natAddress]?.size != 2) {
        sleep(100)
    }

    return inputQueue[natAddress]!![1]
}

fun task2() {}