package aoc.day07

import org.junit.Test
import kotlin.test.assertEquals


internal class Day07AmplificationCircuitKtTest {
    @Test
    fun `Max thruster signal 43210 (from phase setting sequence 4,3,2,1,0)`() {
        val program = mutableListOf(3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0)
        val sequence = listOf(4, 3, 2, 1, 0)
        val processSequence = processSequence(program, sequence)
        assertEquals(43210, processSequence)
    }

    @Test
    fun `Max thruster signal 54321 (from phase setting sequence 0,1,2,3,4)`() {
        val program = mutableListOf(3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23,
                101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0)
        val sequence = listOf(0, 1, 2, 3, 4)
        val processSequence = processSequence(program, sequence)
        assertEquals(54321, processSequence)
    }

    @Test
    fun `Max thruster signal 65210 (from phase setting sequence 1,0,4,3,2)`() {
        val program = mutableListOf(3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33,
                1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0)
        val sequence = listOf(1, 0, 4, 3, 2)
        val processSequence = processSequence(program, sequence)
        assertEquals(65210, processSequence)
    }
}