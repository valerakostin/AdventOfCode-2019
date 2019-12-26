package aoc.day14

import org.junit.Test
import kotlin.test.assertEquals

internal class ReactionTest {
    @Test
    fun `parse reaction string 1 XWLQM, 29 XQBHG, 7 KPNWG to 5 BXVL`() {
        val reaction = Reaction.Parser.parse("1 XWLQM, 29 XQBHG, 7 KPNWG => 5 BXVL")

        val element = reaction.element
        assertEquals(5, element.weight)
        assertEquals("BXVL", element.name)

        val components = reaction.components
        assertEquals(3, components.size)

        assertEquals(1, components[0].weight)
        assertEquals("XWLQM", components[0].name)

        assertEquals(29, components[1].weight)
        assertEquals("XQBHG", components[1].name)

        assertEquals(7, components[2].weight)
        assertEquals("KPNWG", components[2].name)
    }
}