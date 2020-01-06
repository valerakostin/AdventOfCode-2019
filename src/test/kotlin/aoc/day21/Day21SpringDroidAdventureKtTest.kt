package aoc.day21

import aoc.utils.Computer
import org.junit.Test
import kotlin.test.assertEquals

internal class Day21SpringDroidAdventureKtTest
{
    @Test
    fun `task 1 should return 19353565`()
    {
        val program = Computer.ProgramReader.readProgram("InputDay21.txt")
        val task1 = task1(program)
        assertEquals(19353565, task1)
    }
}