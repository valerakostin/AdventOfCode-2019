package aoc.day22

import org.junit.Assert
import org.junit.Test

internal class NewStackTest {

    @Test
    fun `deal into new stack for 0,1,2,3,4,5,6,7,8,9 should return 9, 8, 7, 6, 5, 4, 3, 2, 1, 0`() {
        val expected = listOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
        for (i in 0..9) {
            val index = NewStack.indexOf(i, 10)
            Assert.assertEquals(expected[i], index)
        }
    }
}