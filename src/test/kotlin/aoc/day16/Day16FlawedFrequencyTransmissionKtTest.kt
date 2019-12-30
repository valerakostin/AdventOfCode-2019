package aoc.day16



import org.junit.Assert
import org.junit.Test


internal class Day16FlawedFrequencyTransmissionKtTest {
    @Test
    fun `80871224585914546619083218645595 becomes 24176176`() {
        val input = "80871224585914546619083218645595".map { Character.getNumericValue(it) }.toList()
        Assert.assertEquals("24176176", task1(input, listOf(0, 1, 0, -1)))
    }

    @Test
    fun `19617804207202209144916044189917 becomes 73745418`() {
        val input = "19617804207202209144916044189917".map { Character.getNumericValue(it) }.toList()
        Assert.assertEquals("73745418", task1(input, listOf(0, 1, 0, -1)))
    }

    @Test
    fun `03081770884921959731165446850517 becomes 53553731`() {
        val input = "69317163492948606335995924319873".map { Character.getNumericValue(it) }.toList()
        Assert.assertEquals("52432133", task1(input, listOf(0, 1, 0, -1)))
    }
}