package aoc.day09

import aoc.utils.Computer
import aoc.utils.toIntCode
import org.junit.Test
import kotlin.test.assertEquals

internal class Day09SensorBoostKtTest {

    @Test
    fun `task 1 with input 1 should return 2406950601`() {
        val program = Computer.ProgramReader.readProgram("InputDay09.txt")
        val task1 = computeBoostCode(program, 1)
        assertEquals(2406950601, task1)
    }

    @Test
    fun `task 2 with input 2 should return 83239`() {
        val program = Computer.ProgramReader.readProgram("InputDay09.txt")
        val task1 = computeBoostCode(program, 2)
        assertEquals(83239, task1)
    }

    @Test
    fun `09,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99 takes no input and produces a copy of itself as output`() {
        val program = mutableListOf(109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99)
        val intCode = program.toIntCode()
        val computer = Computer(intCode)
        computer.execute()
        val outputs = computer.outputs()
        for (i in 0 until 16)
            assertEquals(intCode[i], outputs[i])
    }

    @Test
    fun `109, -1, 4, 1, 99 returns -1 `() {
        val c = Computer(listOf(109, -1, 4, 1, 99).toIntCode())
        c.execute()
        assertEquals(-1, c.output())
    }

    @Test
    fun `109, -1, 104, 1, 99 returns 1`() {
        val c = Computer(listOf(109, -1, 104, 1, 99).toIntCode())
        c.execute()
        assertEquals(1, c.output())
    }

    @Test
    fun `109, -1, 204, 1, 99 returns 109`() {
        val c = Computer(listOf(109, -1, 204, 1, 99).toIntCode())
        c.execute()
        assertEquals(109, c.output())
    }

    @Test
    fun `109, 1, 9, 2, 204, -6, 99 returns 204`() {
        val c = Computer(listOf(109, 1, 9, 2, 204, -6, 99).toIntCode())
        c.execute()
        assertEquals(204, c.output())
    }

    @Test
    fun `109, 1, 109, 9, 204, -6, 99 returns 204`() {
        val c = Computer(listOf(109, 1, 109, 9, 204, -6, 99).toIntCode())
        c.execute()
        assertEquals(204, c.output())
    }

    @Test
    fun `109, 1, 209, -1, 204, -106, 99 returns 204`() {
        val c = Computer(listOf(109, 1, 209, -1, 204, -106, 99).toIntCode())
        c.execute()
        assertEquals(204, c.output())
    }

    @Test
    fun `109, 1, 3, 3, 204, 2, 99 returns 4711`() {
        val c = Computer(listOf(109, 1, 3, 3, 204, 2, 99).toIntCode(), inputSupplier = { 4711 })
        c.execute()
        assertEquals(4711, c.output())
    }

    @Test
    fun `109, 1, 203, 2, 204, 2, 99 returns 4711`() {
        val c = Computer(listOf(109, 1, 203, 2, 204, 2, 99).toIntCode(), inputSupplier = { 4711 })
        c.execute()
        assertEquals(4711, c.output())
    }


    @Test
    fun `1102,34915192,34915192,7,4,7,99,0 should output a 16-digit number`() {
        val program = mutableListOf(1102, 34915192, 34915192, 7, 4, 7, 99, 0)
        val computer = Computer(program.toIntCode())
        computer.execute()

        assertEquals(16, computer.output().toString().length)
    }

    @Test
    fun `104,1125899906842624,99 should output the large number in the middle`() {
        val program = mutableMapOf(0 to 104L, 1 to 1125899906842624L, 2 to 99L)
        val computer = Computer(program)
        computer.execute()

        assertEquals(1125899906842624, computer.output())
    }

    @Test
    fun `109, -1, 4, 1, 99 returns -1`() {
        val program = listOf(109, -1, 4, 1, 99)
        val computer = Computer(program.toIntCode())
        computer.execute()
        assertEquals(-1, computer.output())
    }

    @Test
    fun `109, -1, 104, 1, 99 return 1`() {
        val program = listOf(109, -1, 104, 1, 99)
        val computer = Computer(program.toIntCode())
        computer.execute()
        assertEquals(1, computer.output())
    }

    @Test
    fun `09, -1, 204, 1, 99 returns 109`() {
        val program = mutableListOf(109, -1, 204, 1, 99)

        val computer = Computer(program.toIntCode())
        computer.execute()

        assertEquals(109, computer.output())
    }
}