package aoc.day22

import aoc.utils.Utils
import org.junit.Test
import kotlin.test.assertEquals

internal class Day22SlamShuffleKtTest {

    @Test
    fun `sequence Inc(7), NewStack, NewStack return sequence 0, 3, 6, 9, 2, 5, 8, 1, 4, 7`() {
        val operations = listOf(Increment(7), NewStack, NewStack)
        val expected = listOf(0, 3, 6, 9, 2, 5, 8, 1, 4, 7)
        for (i in 0 until 10) {
            val index = indexOf(i, 10, operations)
            assertEquals(expected[index], i)
        }
    }

    @Test
    fun `sequence Cut(6), Inc(7), NewStack should return sequence 3, 0, 7, 4, 1, 8, 5, 2, 9, 6`() {
        val operations = listOf(Cut(6), Increment(7), NewStack)
        val expected = listOf(3, 0, 7, 4, 1, 8, 5, 2, 9, 6)
        for (i in 0 until 10) {
            val index = indexOf(i, 10, operations)
            assertEquals(expected[index], i)
        }
    }

    @Test
    fun `sequence Inc(7), Inc(9), Cut(-2) should return sequence 6,3,0,7,4,1,8,5,2,9`() {
        val operations = listOf(Increment(7), Increment(9), Cut(-2))
        val expected = listOf(6, 3, 0, 7, 4, 1, 8, 5, 2, 9)
        for (i in 0 until 10) {
            val index = indexOf(i, 10, operations)
            assertEquals(expected[index], i)
        }
    }

    @Test
    fun `sequence NewStack, Cut(-2), Inc(7), Cut(8), Cut(-4), Inc(7), Cut(3), Inc(9), Inc(3), Cut(-1) should return sequence 9,2,5,8,1,4,7,0,3,6`() {
        val operations = listOf(NewStack, Cut(-2), Increment(7), Cut(8), Cut(-4), Increment(7), Cut(3), Increment(9), Increment(3), Cut(-1))
        val expected = listOf(9, 2, 5, 8, 1, 4, 7, 0, 3, 6)
        for (i in 0 until 10) {
            val index = indexOf(i, 10, operations)
            assertEquals(expected[index], i)
        }
    }

    @Test
    fun `task 1 should return 6431`() {
        val lines =
                Utils.linesFromResource("InputDay22.txt")
        val operations = Operation.Parser.parseOperations(lines)
        assertEquals(6431, task1(operations))
    }
}
