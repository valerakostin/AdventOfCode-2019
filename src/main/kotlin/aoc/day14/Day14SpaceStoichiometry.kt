package aoc.day14

import aoc.utils.Utils
import kotlin.math.ceil

data class Element(val name: String, val weight: Int) {
    object Parser {
        fun parse(raw: String): Element {
            val indexOf = raw.indexOf(' ')
            val weight = raw.substring(0 until indexOf)
            val name = raw.substring(indexOf + 1)
            return Element(name, weight.toInt())
        }
    }
}

data class Reaction(val element: Element, val components: MutableList<Element>) {
    object Parser {
        fun parse(raw: String): Reaction {
            val indexOf = raw.indexOf("=>")
            val mainComponent = raw.substring(indexOf + "=>".length).trim()
            val mc = Element.Parser.parse(mainComponent)
            val rawElements = raw.substring(0, indexOf)
            val split = rawElements.split(", ")
            val elements = split.map { Element.Parser.parse(it.trim()) }.toMutableList()
            return Reaction(mc, elements)
        }
    }
}

private fun parseReaction(rawString: String) = Reaction.Parser.parse(rawString)
fun main() {

    val reactions =
            Utils.itemsFromResource("InputDay14.txt", ::parseReaction).associateBy { it.element.name }

    val task1 = task1(reactions)
    val task2 = task2()

    println(
            """
              Day14
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}


fun computeOREs(material: String, quantity: Int, reactions: Map<String, Reaction>): Int {
    val waste = mutableMapOf<String, Long>()

    fun computeOREs(material: String, quantity: Int): Int {
        if (material == "ORE")
            return quantity
        else {
            reactions[material]?.let {
                val alreadyAvailable = waste.getOrDefault(it.element.name, 0)
                val required = quantity - alreadyAvailable
                waste[material] = (alreadyAvailable - quantity).coerceAtLeast(0)

                return if (required > 0) {
                    val available = it.element.weight
                    val scale = ceil(required.toDouble() / available.toDouble()).toInt()
                    val wasted = scale * available - required

                    waste[material] = waste.getOrDefault(material, 0) + wasted
                    it.components.map { c -> computeOREs(c.name, c.weight * scale) }.sum()

                } else
                    0

            }
        }
        return 0
    }

    return computeOREs(material, quantity)
}

fun task1(reactions: Map<String, Reaction>): Int {
    return computeOREs("FUEL", 1, reactions)
}

fun task2() {}