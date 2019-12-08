package aoc.utils


typealias Intcode = MutableList<Int>

private data class ComputerState(var counter: Int = 0,
                                 var lastOutput: Int = 0,
                                 var inputCounter: Int = 0,
                                 var isReady: Boolean = false,
                                 var pause: Boolean = false) {
    fun increaseCounter(c: Int) {
        counter += c
    }

    fun increaseInputCounter() {
        inputCounter += 1
    }
}

class Computer(private val program: Intcode, var inputSupplier: ((Int) -> Int)? = null, val resumeOnOutput: Boolean = false) {
    private val state = ComputerState()

    fun execute() {
        while (!program[state.counter].isHaltOpcode() && !state.pause) {
            val opcode = program[state.counter]
            when {
                opcode.isAddOpCode() -> addCommand(opcode.opCodePositions())
                opcode.isMulOpCode() -> mulCommand(opcode.opCodePositions())
                opcode.isInputOpCode() -> inputCommand()
                opcode.isOutputOpCode() -> outputCommand()
                opcode.isJumpIfTrueOpCode() -> jumpIfTrue(opcode.opCodePositions())
                opcode.isJumpIfFalseOpCode() -> jumpIfFalse(opcode.opCodePositions())
                opcode.isLessThanOpCode() -> isLessThan(opcode.opCodePositions())
                opcode.isEqualsOpCode() -> isEquals(opcode.opCodePositions())
                else -> throw IllegalArgumentException(opcode.opCodePositions())
            }
        }
        if (program[state.counter].isHaltOpcode())
            state.isReady = true
    }

    private fun Int.opCodePositions() = this.toString().padStart(5, '0').substring(0, 3).reversed()

    private fun Int.isHaltOpcode() = this == 99
    private fun Int.isAddOpCode() = this.toString().endsWith("1")
    private fun Int.isMulOpCode() = this.toString().endsWith("2")
    private fun Int.isInputOpCode() = this == 3
    private fun Int.isOutputOpCode() = this.toString().endsWith("4")
    private fun Int.isJumpIfTrueOpCode() = this.toString().endsWith("5")
    private fun Int.isJumpIfFalseOpCode() = this.toString().endsWith("6")
    private fun Int.isLessThanOpCode() = this.toString().endsWith("7")
    private fun Int.isEqualsOpCode() = this.toString().endsWith("8")

    private fun addCommand(mode: String) = mathCommand(mode) { o1, o2 -> o1 + o2 }
    private fun mulCommand(mode: String) = mathCommand(mode) { o1, o2 -> o1 * o2 }

    private fun outputCommand() {
        inOutCommand { state.lastOutput = program[it] }
        if (resumeOnOutput)
            state.pause = true
    }

    fun isReady() = state.isReady


    fun resume() {
        state.pause = false
    }


    private fun inputCommand() {
        if (inputSupplier != null)
            inOutCommand { program[it] = inputSupplier!!.invoke(state.inputCounter) }
        else
            throw IllegalStateException("Supplier is not specified")
    }

    private fun isEquals(modes: String) = compareCommand(modes) { x, y -> x == y }
    private fun isLessThan(modes: String) = compareCommand(modes) { x, y -> x < y }

    private fun jumpIfFalse(modes: String) = jumpCommand(modes) { it == 0 }
    private fun jumpIfTrue(modes: String) = jumpCommand(modes) { it != 0 }

    private fun mathCommand(modes: String, op: (Int, Int) -> Int) {
        val operand1 = getValue(program[state.counter + 1], modes[0])
        val operand2 = getValue(program[state.counter + 2], modes[1])

        val result = op(operand1, operand2)
        val i = program[state.counter + 3]
        program[i] = result
        state.increaseCounter(4)
    }

    private fun inOutCommand(action: (Int) -> Unit) {
        val operand1Address = program[state.counter + 1]
        action(operand1Address)
        state.increaseCounter(2)
        state.increaseInputCounter()
    }

    private fun jumpCommand(modes: String, condition: (Int) -> Boolean) {
        val operand1 = getValue(program[state.counter + 1], modes[0])
        if (condition(operand1)) {
            state.counter = getValue(program[state.counter + 2], modes[1])
        } else
            state.increaseCounter(3)
    }

    private fun compareCommand(modes: String, compare: (Int, Int) -> Boolean) {
        val operand1 = getValue(program[state.counter + 1], modes[0])
        val operand2 = getValue(program[state.counter + 2], modes[1])
        val operand1Address = program[state.counter + 3]

        if (compare(operand1, operand2))
            program[operand1Address] = 1
        else
            program[operand1Address] = 0

        state.increaseCounter(4)
    }

    private fun getValue(address: Int, m: Char): Int {
        return if (m == '0') {
            program[address]
        } else
            address
    }

    fun output() = state.lastOutput

    object ProgramReader {
        fun readProgram(resource: String): Intcode {
            return Utils.lineFromResource(resource)
                    .split(",")
                    .map { it.toInt() }
                    .toMutableList()
        }
    }
}