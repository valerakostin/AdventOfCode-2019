package aoc.day07

import aoc.utils.Computer
import aoc.utils.toIntCode
import org.junit.Test
import kotlin.test.assertEquals


internal class Day07AmplificationCircuitKtTest {
    @Test
    fun `Max thruster signal 43210 (from phase setting sequence 4,3,2,1,0)`() {
        val program = mutableListOf(3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0)
        val sequence = listOf(4L, 3L, 2L, 1L, 0L)
        val processSequence = processSequence(program.toIntCode(), sequence)
        assertEquals(43210, processSequence)
    }

    @Test
    fun `Max thruster signal 54321 (from phase setting sequence 0,1,2,3,4)`() {
        val program = mutableListOf(3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23,
                101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0)
        val sequence = listOf(0L, 1L, 2L, 3L, 4L)
        val processSequence = processSequence(program.toIntCode(), sequence)
        assertEquals(54321, processSequence)
    }

    @Test
    fun `Max thruster signal 65210 (from phase setting sequence 1,0,4,3,2)`() {
        val program = mutableListOf(3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33,
                1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0)
        val sequence = listOf(1L, 0L, 4L, 3L, 2L)
        val processSequence = processSequence(program.toIntCode(), sequence)
        assertEquals(65210, processSequence)
    }

    @Test
    fun `Part2 Max thruster signal 139629729 (from phase setting sequence 9,8,7,6,5) 3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5`() {
        val program = mutableListOf(3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26, 27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5)
        val sequence = listOf(9L, 8L, 7L, 6L, 5L)
        val processSequence = processSequenceWithFeedback(program.toIntCode(), sequence)
        assertEquals(139629729, processSequence)
    }

    @Test
    fun `Part 2 Max thruster signal 18216 (from phase setting sequence 9,7,8,5,6) 3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10`() {
        val program = mutableListOf(3, 52, 1001, 52, -5, 52, 3, 53, 1, 52, 56, 54, 1007, 54, 5, 55, 1005, 55, 26, 1001, 54, -5, 54, 1105, 1, 12, 1, 53, 54, 53, 1008, 54, 0, 55, 1001, 55, 1, 55, 2, 53, 55, 53, 4, 53, 1001, 56, -1, 56, 1005, 56, 6, 99, 0, 0, 0, 0, 10)
        val sequence = listOf(9L, 7L, 8L, 5L, 6L)
        val processSequence = processSequenceWithFeedback(program.toIntCode(), sequence)
        assertEquals(18216, processSequence)
    }

    @Test
    fun `answer for part 1 is 20413`() {
        val program = Computer.ProgramReader.readProgram("InputDay07.txt")
        assertEquals(20413, task1(program)?.first)
    }

    @Test
    fun `answer for part 2 is 3321777`() {
        val program = Computer.ProgramReader.readProgram("InputDay07.txt")
        assertEquals(3321777, task2(program)?.first)
    }

}