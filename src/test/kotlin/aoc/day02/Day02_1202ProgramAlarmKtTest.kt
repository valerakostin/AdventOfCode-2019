package aoc.day02

import aoc.Computer
import aoc.utils.Utils
import org.junit.Test
import kotlin.test.assertEquals

internal class Day02_1202ProgramAlarmKtTest {
    @Test
    fun `task 1 should return 3409710`() {
        val program = getProgram()
        assertEquals(3409710, task1(program))
    }

    @Test
    fun `inputs 79 and 12 return 19690720`() {
        val program = getProgram()
        program[1] = 79
        program[2] = 12
        Computer(program).execute()
        assertEquals(19690720, program[0])
    }

    @Test
    fun `task 2 should return 7912`() {
        assertEquals(7912, task2())
    }

    private fun getProgram(): MutableList<Int> {
        return Utils.lineFromResource("InputDay02.txt")
                .split(",")
                .map { it.toInt() }
                .toMutableList()
    }
}