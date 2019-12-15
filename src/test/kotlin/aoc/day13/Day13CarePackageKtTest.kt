package aoc.day13

import aoc.utils.Computer
import org.junit.Test
import kotlin.test.assertEquals

internal class Day13CarePackageKtTest {
    @Test
    fun `task 1 returns 329`() {
        val program = Computer.ProgramReader.readProgram("InputDay13.txt")
        val task1 = task1(program)
        assertEquals(329, task1)
    }
}