package aoc.day22

import org.junit.Assert
import org.junit.Test

internal class CutTest {

    @Test
    fun `cut 3 for input 0,1,2,3,4,5,6,7,8,9 should return 3, 4, 5, 6, 7, 8, 9, 0, 1, 2`() {
        val cut = Cut(3)
        val expected = listOf(3, 4, 5, 6, 7, 8, 9, 0, 1, 2)
        for (i in 0..9) {
            val index = cut.indexOf(i, 10)
            Assert.assertEquals(expected[index], i)
        }
    }

    @Test
    fun `cut -4 for input 0,1,2,3,4,5,6,7,8,9 should return 6, 7, 8, 9, 0, 1, 2, 3, 4, 5`() {
        val cut = Cut(-4)
        val expected = listOf(6, 7, 8, 9, 0, 1, 2, 3, 4, 5)
        for (i in 0..9) {
            val index = cut.indexOf(i, 10)
            Assert.assertEquals(expected[index], i)
        }
    }
}