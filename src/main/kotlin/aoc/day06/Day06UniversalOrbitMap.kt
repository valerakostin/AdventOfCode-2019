package aoc.day06

import aoc.utils.Utils

fun main() {
    val mapping = Utils.linesFromResource("InputDay06.txt").map { it.split(")") }
            .map { it[1] to it[0] }
            .toMap()

    val task1 = task1(mapping)
    val task2 = task2(mapping, "SAN", "YOU")

    println(
            """
              Day06
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

fun task1(mapping: Map<String, String>): Int {
    return mapping.keys
            .map { connectionCount(mapping[it], mapping) }
            .sum()
}

internal fun connectionCount(key: String?, mapping: Map<String, String>): Int {
    fun rec(key: String?, acc: Int): Int {
        if (key == null)
            return acc
        return rec(mapping[key], acc + 1)
    }
    return rec(key, 0)
}


internal fun collectPath(key: String?, mapping: Map<String, String>): List<String> {
    fun rec(key: String?, acc: MutableList<String>): List<String> {
        if (key == null)
            return acc
        acc.add(key)
        return rec(mapping[key], acc)
    }
    return rec(key, mutableListOf())
}

fun task2(mapping: Map<String, String>, from: String, to: String): Int {
    val fromPath = collectPath(mapping[from], mapping)
    val toPath = collectPath(mapping[to], mapping)
    val item = fromPath.firstCommonElement(toPath)
    return fromPath.distanceTo(item) + toPath.distanceTo(item)
}

private fun List<String>.firstCommonElement(path: List<String>): String {
    return this.first { path.contains(it) }
}

private fun List<String>.distanceTo(location: String): Int {
    return this.indexOf(location)
}
