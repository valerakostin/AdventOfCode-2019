package aoc.day22

import org.junit.Assert
import org.junit.Test


internal class IncrementTest {

    @Test
    fun `test1 increment 3`() {
        val increment = Increment(3)
        val expected = listOf(0, 7, 4, 1, 8, 5, 2, 9, 6, 3)
        for (i in 0..9) {
            val index = increment.indexOf(i, 10)
            Assert.assertEquals(expected[index], i)
        }
    }
}