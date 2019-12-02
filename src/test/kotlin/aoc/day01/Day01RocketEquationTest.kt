package aoc.day01

import aoc.utils.Utils
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day01RocketEquationTest {
   @Test
   fun `test result of task 1`()
   {
       assertEquals(3405637,task1(Utils.itemsFromResource("InputDay01.txt") { it.toLong() }))
   }

    @Test
    fun `test result of task 2`()
    {
        assertEquals(5105597,task2(Utils.itemsFromResource("InputDay01.txt") { it.toLong() }))
    }

    @Test
    fun `test fuel requirements for mass 12`() {
        assertEquals(2, moduleFuelRequirement(12))
    }

    @Test
    fun `test fuel requirements for mass 14`() {
        assertEquals(2, moduleFuelRequirement(14))
    }

    @Test
    fun `test fuel requirements for mass 1969`() {
        assertEquals(654, moduleFuelRequirement(1969))
    }

    @Test
    fun `test fuel requirements for mass 100756`() {
        assertEquals(33583, moduleFuelRequirement(100756))
    }

    @Test
    fun `test total fuel requirements for mass 12`() {
        assertEquals(2, totalFuelRequirement(12))
    }

    @Test
    fun `test total fuel requirements for mass 14`() {
        assertEquals(2, totalFuelRequirement(14))
    }

    @Test
    fun `test total fuel requirements for mass 1969`() {
        assertEquals(966, totalFuelRequirement(1969))
    }

    @Test
    fun `test total fuel requirements for mass 100756`() {
        assertEquals(50346, totalFuelRequirement(100756))
    }
}