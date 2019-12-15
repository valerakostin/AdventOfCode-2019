package aoc.day11

import aoc.utils.Computer
import org.junit.Test
import kotlin.test.assertEquals


internal class SpacePoliceDay11KtTest {
    @Test
    fun `task 1 should return 1747`() {
        val program = Computer.ProgramReader.readProgram("InputDay11.txt")
        val task1 = task1(program)
        assertEquals(1747, task1)
    }
}