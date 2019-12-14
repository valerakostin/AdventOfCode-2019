package aoc.utils
import org.junit.Test
import kotlin.test.assertEquals
internal class ComputerTest {
    @Test
    fun `1,0,0,0,99 becomes 2,0,0,0,99 (1 + 1 = 2)`() {
        testProgram(mutableListOf(1, 0, 0, 0, 99), listOf(2, 0, 0, 0, 99))
    }

    @Test
    fun `2,3,0,3,99 becomes 2,3,0,6,99 (3 * 2 = 6)`() {
        testProgram(mutableListOf(2, 3, 0, 3, 99), listOf(2, 3, 0, 6, 99))
    }

    @Test
    fun `2,4,4,5,99,0 becomes 2,4,4,5,99,9801 (99 * 99 = 9801)`() {
        testProgram(mutableListOf(2, 4, 4, 5, 99, 0), listOf(2, 4, 4, 5, 99, 9801))
    }

    @Test
    fun `1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99`() {
        testProgram(mutableListOf(1, 1, 1, 4, 99, 5, 6, 0, 99), listOf(30, 1, 1, 4, 2, 5, 6, 0, 99))
    }

    @Test
    fun `1,9,10,3,2,3,11,0,99,30,40,50 becomes 3500,9,10,70,2,3,11,0,99,30,40,50`() {
        testProgram(mutableListOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50), listOf(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50))
    }

    private fun testProgram(input: MutableList<Int>, output: List<Int>) {
        val program = input.withIndex().associateTo(mutableMapOf()) {
            it.index to it.value.toLong()
        }
        val computer = Computer(program)
        computer.execute()
        for ((index, value) in output.withIndex())
            assertEquals(value.toLong(), program[index])
    }
}
