package aoc.day12

import org.junit.Test
import kotlin.test.assertEquals

internal class Day12Test {

    @Test
    fun `day 12 part 1 answer is 7471`() {
        val state = State(
                listOf(
                        Item(Pos(1, 2, -9), Vel(0, 0, 0)),
                        Item(Pos(-1, -9, -4), Vel(0, 0, 0)),
                        Item(Pos(17, 6, 8), Vel(0, 0, 0)),
                        Item(Pos(12, 4, 2), Vel(0, 0, 0))))

        var nextStep = state
        nextStep.print()
        repeat(1000) {
            nextStep = nextStep.nextStep()
        }
        assertEquals(7471, nextStep.energy())
    }


    @Test
    fun `day 12 part 2 answer is 7471`() {

      val result = task2 { State(
               listOf(
                       Item(Pos(1, 2, -9), Vel(0, 0, 0)),
                       Item(Pos(-1, -9, -4), Vel(0, 0, 0)),
                       Item(Pos(17, 6, 8), Vel(0, 0, 0)),
                       Item(Pos(12, 4, 2), Vel(0, 0, 0)))) }

        assertEquals(376243355967784, result)
    }
}