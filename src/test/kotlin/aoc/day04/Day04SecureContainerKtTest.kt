package aoc.day04

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Day04SecureContainerKtTest {
    @Test
    fun `111111 meets these criteria double 11, never decreases`() {
        assertTrue("111111".hasAdjustedNumbers())
    }

    @Test
    fun `123789 does not meet these criteria no double`() {
        assertFalse("123789".hasAdjustedNumbers())
    }

    @Test
    fun `223450 does not meet these criteria (decreasing pair of digits 50)`() {
        assertFalse("223450".isOrdered())
    }

    @Test
    fun `task 1 for range 347312-805915 should return 594`() {
        assertEquals(594, task1(347312..805915))
    }

    @Test
    fun `112233 meets these criteria because the digits never decrease and all repeated digits are exactly two digits long`() {
        assertTrue("112233".has2AdjustedNumbers())
    }

    @Test
    fun `123444 no longer meets the criteria the repeated 44 is part of a larger group of 444`() {
        assertFalse("123444".has2AdjustedNumbers())
    }

    @Test
    fun `111122 meets the criteria even though 1 is repeated more than twice, it still contains a double 22`() {
        assertTrue("111122".has2AdjustedNumbers())
    }

    @Test
    fun `task 2 for range 347312-805915 should return 364`() {
        assertEquals(364, task2(347312..805915))
    }
}