package aoc.day23

import aoc.utils.Computer
import aoc.utils.Network

fun main() {

    val result = simulateNetwork()
    println(
            """
              Day23
               Task1: ${result.first}
               Task2: ${result.second}
            """.trimIndent())
}

private val inputQueue = mutableMapOf<Long, MutableList<Long>>()
private const val natAddress = 255L

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

fun simulateNetwork(): Pair<Long, Long> {
    val networkSize = 50
    for (i in 0 until networkSize) {
        inputQueue[i.toLong()] = mutableListOf()
        inputQueue[natAddress] = mutableListOf()
    }

    val network = (0 until networkSize).map { createComputer(it) }.toList()
    val cache = mutableSetOf<Long>()
    while (true) {
        for (computer in network) {

            Thread(Runnable {
                if (!computer.isReady()) {
                    computer.execute()
                }
            }).start()
        }
        var task1: Long? = null

        while (true) {

            if (isIdle()) {
                val natData = inputQueue[natAddress]
                if (natData != null) {
                    val x = natData[natData.size - 2]
                    val y = natData[natData.size - 1]
                    natData.clear()

                    inputQueue[0]?.add(x)
                    inputQueue[0]?.add(y)

                    if (task1 == null)
                        task1 = y

                    if (cache.contains(y)) {
                        return Pair(task1, y)
                    } else
                        cache.add(y)

                }
            }
        }
    }
}

private fun isIdle(): Boolean {
    val filtered = inputQueue.entries.filter { it.value.size >= 2 }
    return filtered.size == 1 && filtered[0].key == natAddress
}
