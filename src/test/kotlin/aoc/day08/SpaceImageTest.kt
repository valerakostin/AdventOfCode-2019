package aoc.day08

import aoc.utils.Utils
import org.junit.Test
import kotlin.test.assertEquals

internal class SpaceImageTest {
    @Test
    fun `test task 1 the correct answer for input should be 1548`() {
        val imageData =
                Utils.lineFromResource("InputDay08.txt")
                        .map { Character.getNumericValue(it) }
                        .toList()
        val image = SpaceImage(25, 6, imageData)
        val checkSum = image.getImageCheckSum()
        assertEquals(1548, checkSum)
    }

    @Test
    fun `test color for pixel 0222112222120000 - p1=0, p2=1, p3=1, p4=0`()
    {
        val imageData = listOf(0,2,2,2,1,1,2,2,2,2,1,2,0,0,0,0)
        val image = SpaceImage(2, 2, imageData)

        assertEquals(0, image.pixelColor(0))
        assertEquals(1, image.pixelColor(1))
        assertEquals(1, image.pixelColor(2))
        assertEquals(0, image.pixelColor(3))
    }
}