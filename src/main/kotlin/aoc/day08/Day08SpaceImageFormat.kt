package aoc.day08

import aoc.day08.SpaceImage.PixelColor.BLACK
import aoc.day08.SpaceImage.PixelColor.TRANSPARENT
import aoc.day08.SpaceImage.PixelColor.WHITE
import aoc.utils.Utils

typealias  ImageData = String

data class SpaceImage(val width: Int, val height: Int, val data: ImageData) {
    private fun layerCount() = data.length / (width * height)

    private fun layerRange(layer: Int) = IntRange(layer * width * height, (layer + 1) * width * height - 1)

    private fun layerStartIndex(layer: Int) = layer * width * height

    private fun countOfPixelAtLayer(number: Char, layer: Int): Int {
        return layerRange(layer).count { data[it] == number }
    }

    fun getImageCheckSum(): Int {
        (0 until layerCount())
                .map { Pair(it, countOfPixelAtLayer(BLACK, it)) }
                .minBy { it.second }
                ?.let {
                    val ones = countOfPixelAtLayer(WHITE, it.first)
                    val twos = countOfPixelAtLayer(TRANSPARENT, it.first)
                    return ones * twos
                }
        throw  IllegalAccessException("Invalid image")
    }

    internal fun pixelColor(pixel: Int): Char {
        return (0 until layerCount())
                .map { data[layerStartIndex(it) + pixel] }
                .first { it != TRANSPARENT }
    }

    object PixelColor {
        const val BLACK = '0'
        const val WHITE = '1'
        const val TRANSPARENT = '2'
    }

    object ImagePrinter {
        fun drawImage(image: SpaceImage) {
            for (i in 0 until image.height) {
                for (j in 0 until image.width) {
                    val pixel = i * image.width + j
                    val pixelColor = image.pixelColor(pixel)
                    if (pixelColor == BLACK)
                        print(' ')
                    else
                        print('#')
                }
                println()
            }
        }
    }
}

fun main() {
    val imageData =
            Utils.lineFromResource("InputDay08.txt")
    val image = SpaceImage(25, 6, imageData)
    val task1 = image.getImageCheckSum()

    println(
            """
              Day08
               Task1: $task1
               Task2: 
            """.trimIndent())

    SpaceImage.ImagePrinter.drawImage(image)
}
