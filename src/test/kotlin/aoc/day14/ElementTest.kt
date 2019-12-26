package aoc.day14

import org.junit.Test
import kotlin.test.assertEquals


internal class ElementTest {
    @Test
    fun `parse string 2 LQMT`() {
        val element = Element.Parser.parse("2 LQMT")
        assertEquals("LQMT", element.name)
        assertEquals(2, element.weight)
    }
}