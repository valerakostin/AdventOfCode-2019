package aoc.day25

import aoc.utils.Computer
import aoc.utils.Intcode

fun main() {

    val program = Computer.ProgramReader.readProgram("InputDay25.txt")
    println("Day25:")
    val robot = Robot(program, autoplayMode = true)
    robot.playGame()
}

class Robot(val program: Intcode, autoplayMode: Boolean = true) {
    private val computer: Computer
    private val inputBuffer = mutableListOf<Char>()

    init {
        computer = Computer(program, resumeOnOutput = true, inputSupplier = ::inputSupplier)
        if (autoplayMode) {
            val autoplay = listOf(
                    "west\n",// Hot Chocolate Fountain
                    "west\n", // Passages
                    "west\n", // Stables
                    "west\n", // Holodeck
                    "take dark matter\n", // Items here: - dark matter
                    "east\n", "east\n", // Go back to Passages
                    "south\n",// Sream Lab
                    "take astronaut ice cream\n",// Items here: - astronaut ice cream
                    "south\n", // Crew Quarters
                    "east\n", // Observatory
                    "take easter egg\n", // Items here: - easter egg
                    "east\n", // Engineering
                    "take weather machine\n", //Items here: - weather machine
                    "north\n", // Security Checkpoint
                    "north\n" // Pressure-Sensitive Floor
            )
            autoplay.flatMap { it.toCharArray().toList() }.forEach { inputBuffer.add(it) }
        }
    }

    private fun inputSupplier(`in`: Int): Long {
        if (inputBuffer.isEmpty()) {
            val readLine = readLine()
            readLine?.let {
                inputBuffer.addAll((readLine + '\n').toCharArray().toList())
            }
        }
        return inputBuffer.removeAt(0).toLong()
    }


    fun playGame() {
        while (!computer.isReady()) {
            computer.execute()
            val output = computer.output()
            print(output.toChar())
            computer.resume()
        }
    }
}