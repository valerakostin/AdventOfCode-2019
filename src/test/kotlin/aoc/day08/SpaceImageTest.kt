package aoc.day08

import aoc.utils.Utils
import org.junit.Test
import kotlin.test.assertEquals

internal class SpaceImageTest {
    @Test
    fun `test task 1 the correct answer for input should be 1548`() {
        val imageData =
                Utils.lineFromResource("InputDay08.txt")
        val image = SpaceImage(25, 6, imageData)
        val checkSum = image.getImageCheckSum()
        assertEquals(1548, checkSum)
    }

    @Test
    fun `test color for pixel 0222112222120000 - p1=0, p2=1, p3=1, p4=0`() {
        val imageData = "0222112222120000"
        val image = SpaceImage(2, 2, imageData)

        assertEquals(SpaceImage.PixelColor.BLACK, image.pixelColor(0))
        assertEquals(SpaceImage.PixelColor.WHITE, image.pixelColor(1))
        assertEquals(SpaceImage.PixelColor.WHITE, image.pixelColor(2))
        assertEquals(SpaceImage.PixelColor.BLACK, image.pixelColor(3))
    }
}