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
        assertEquals(2, moduleFuelRequirements(12))
    }

    @Test
    fun `test fuel requirements for mass 14`() {
        assertEquals(2, moduleFuelRequirements(14))
    }

    @Test
    fun `test fuel requirements for mass 1969`() {
        assertEquals(654, moduleFuelRequirements(1969))
    }

    @Test
    fun `test fuel requirements for mass 100756`() {
        assertEquals(33583, moduleFuelRequirements(100756))
    }

    @Test
    fun `test total fuel requirements for mass 12`() {
        assertEquals(2, totalFuelRequirements(12))
    }

    @Test
    fun `test total fuel requirements for mass 14`() {
        assertEquals(2, totalFuelRequirements(14))
    }

    @Test
    fun `test total fuel requirements for mass 1969`() {
        assertEquals(966, totalFuelRequirements(1969))
    }

    @Test
    fun `test total fuel requirements for mass 100756`() {
        assertEquals(50346, totalFuelRequirements(100756))
    }
}