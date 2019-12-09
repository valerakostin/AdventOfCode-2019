package aoc.day09

import aoc.utils.Computer
import org.junit.Test
import kotlin.test.assertEquals


internal class Day09SensorBoostKtTest {
    @Test
    fun `09,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99 takes no input and produces a copy of itself as output`() {
        val program = mutableListOf(109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99)
        val computer = Computer(program)
        computer.execute()
        val outputs = computer.outputs()
        assertEquals(program.subList(0, 16), outputs)
    }
}