package aoc.day20

import aoc.utils.Utils

internal typealias Name = String

internal data class Portal(val name: Name, val loc: Loc)
internal data class RecursivePortal(val portal: Portal, val depth: Int) {
    val name = portal.name
}

internal data class Loc(val x: Int, val y: Int) {
    fun neighbours(): List<Loc> {
        return listOf(Loc(x - 1, y), Loc(x + 1, y), Loc(x, y - 1), Loc(x, y + 1))
    }
}

internal data class PathElement(val entrance: Portal, val exit: Portal, val length: Int)

internal data class RecursivePathElement(val entrance: Portal, val exit: Portal, val length: Int, val depth: Int)

internal class DonutMaze(private val portals: Map<Loc, Name>, private val fields: Set<Loc>) {

    private val maxX = fields.maxBy { it.x }?.x
    private val maxY = fields.maxBy { it.y }?.y

    private fun computeConnections(): List<PathElement> {

        val pathElements = mutableListOf<PathElement>()
        for ((entryLoc, entryName) in portals) {
            for ((exitLoc, exitName) in portals) {
                if (entryLoc != exitLoc) {
                    val distance = distanceBetween(entryLoc, exitLoc)
                    if (distance != null) {
                        pathElements.add(PathElement(Portal(entryName, entryLoc), Portal(exitName, exitLoc), distance))
                    }
                }
            }
        }
        return pathElements
    }


    private fun computeRecursiveConnections(): List<RecursivePathElement> {

        val pathElements = mutableListOf<RecursivePathElement>()
        for ((entryLoc, entryName) in portals) {
            for ((exitLoc, exitName) in portals) {
                if (entryLoc != exitLoc) {
                    val distance = distanceBetween(entryLoc, exitLoc)
                    if (distance != null) {
                        val depth = if (isOuter(exitLoc)) -1 else 1
                        pathElements.add(RecursivePathElement(Portal(entryName, entryLoc), Portal(exitName, exitLoc), distance, depth))
                    }
                }
            }
        }
        return pathElements
    }

    private fun isOuter(loc: Loc): Boolean {

        return loc.x == 2 || loc.x == maxX || loc.y == 2 || loc.y == maxY
    }

    fun minDistanceBetweenStartAndEndPortals(): Int? {

        val startAndEnd = startAndEndPortals()
        if (startAndEnd != null) {

            val startPortal = startAndEnd.first
            val endPortal = startAndEnd.second
            val pathElements = computeConnections()

            fun Portal.neighbours() = pathElements
                    .filter { it.entrance.name == this.name && it.entrance.loc != this.loc }

            val inputElements = pathElements
                    .filter { (it.entrance.name == startPortal.name) }

            // remove direct portals AA-ZZ
            val directPortals = inputElements.filter { it.exit.name == endPortal.name }
            var workSet = inputElements.filter { it.exit.name != endPortal.name }.toSet()


            val visited = mutableSetOf(startPortal.loc)

            while (workSet.isNotEmpty()) {
                val destination = workSet.find { it.exit == endPortal }
                if (destination != null) {
                    val directPath = directPortals.minBy { it.length }
                    return directPath?.length?.coerceAtMost(destination.length) ?: destination.length
                } else {
                    workSet.map { it.exit.loc }.forEach { visited.add(it) }


                    val newWorkSet = mutableSetOf<PathElement>()

                    for (item in workSet) {
                        val items = item.exit.neighbours().filter { it.exit.loc !in visited }
                                .map { PathElement(item.entrance, it.exit, item.length + it.length + 1) }
                                .toSet()
                        newWorkSet.addAll(items)
                    }
                    workSet = newWorkSet
                }
            }
        }
        return null
    }

    private fun startAndEndPortals(): Pair<Portal, Portal>? {
        val mapping = portals.entries.groupBy({ it.value }) { it.key }

        val entrance = mapping.filter { it.value.size == 1 }.toSortedMap()
        if (entrance.size == 2) {
            val list = entrance.toList()
            val startPortal = Portal(list[0].first, list[0].second[0])
            val endPortal = Portal(list[1].first, list[1].second[0])
            return Pair(startPortal, endPortal)
        }
        return null
    }

    private fun RecursivePathElement.neighbours(startPortal: RecursivePortal, endPortal: RecursivePortal, element: List<RecursivePathElement>): List<RecursivePathElement> {
        return element
                .filter {
                    it.entrance.name != startPortal.name &&
                            it.entrance.name == endPortal.name
                    it.entrance.name == this.exit.name &&
                            it.entrance.loc != this.exit.loc
                }
    }

    fun minRecursiveDistanceBetweenStartAndEndPortals(): Int? {

        val startAndEnd = startAndEndPortals()
        if (startAndEnd != null) {
            val startPortal = RecursivePortal(startAndEnd.first, 0)
            val endPortal = RecursivePortal(startAndEnd.second, 0)

            val pathElements = computeRecursiveConnections()

            val inputElements = pathElements
                    .filter { (it.entrance.name == startPortal.name) }

            val exits = pathElements
                    .filter { it.exit.name == endPortal.name }
                    .groupBy({ it.entrance.name }, { it.length })
                    .entries.map { it.key to it.value.min() }.toMap()

            var workSet = inputElements
                    .filter { it.exit.name != endPortal.name && it.depth > 0 }
                    .toSet()

            val visited = mutableSetOf(startPortal)

            while (workSet.isNotEmpty()) {

                val result = workSet
                        .filter { it.depth == 0 && exits.containsKey(it.exit.name) }
                        .map { it.length + 1 + (exits[it.exit.name] ?: error("is not null, checked in filter")) }
                        .min()

                if (result != null)
                    return result

                workSet.map { RecursivePortal(it.exit, it.depth) }.forEach { visited.add(it) }

                val newWorkSet = mutableSetOf<RecursivePathElement>()

                for (item in workSet) {
                    val neighbours = item.neighbours(startPortal, endPortal, pathElements)
                            .filter { RecursivePortal(it.exit, it.depth) !in visited }
                            .map {
                                RecursivePathElement(
                                        item.entrance,
                                        it.exit,
                                        item.length + it.length + 1,
                                        it.depth + item.depth)
                            }
                            .filter { it.depth >= 0 && (it.exit.name != endPortal.name || it.depth == 0) }
                            .toSet()
                    newWorkSet.addAll(neighbours)
                }
                workSet = newWorkSet
            }
        }
        return null
    }


    private fun distanceBetween(portalEntrance: Loc, portalExit: Loc): Int? {

        fun isNeighbour(loc: Loc) = fields.contains(loc)

        fun computeDistance(): Int? {
            var workSet = portalEntrance.neighbours().filter { isNeighbour(it) }
                    .toSet()
            val visited = mutableSetOf(portalEntrance)
            var distance = 1

            while (workSet.isNotEmpty()) {
                if (workSet.contains(portalExit))
                    return distance
                else {
                    visited.addAll(workSet)
                    workSet = workSet
                            .flatMap { it.neighbours() }
                            .filter { isNeighbour(it) && it !in visited }
                            .toSet()
                    distance += 1
                }
            }
            return null
        }
        return computeDistance()
    }


    companion object Parser {
        private const val SPACE = '.'
        private const val WALL = '#'
        fun createMaze(lines: List<String>): DonutMaze {
            val verticalRange = verticalRange(lines)
            val horizontalRange = horizontalRange(lines)

            val innerPortals = innerPortals(horizontalRange, verticalRange, lines)
            val outerPortals = entrances(lines)

            val freeLocations = collectFields(lines)
            return DonutMaze(innerPortals + outerPortals, freeLocations)
        }

        private fun collectFields(lines: List<String>): Set<Loc> {
            val locations = mutableSetOf<Loc>()
            val maxLength = lines.map { it.length }.max()
            if (maxLength != null) {
                for (line in 2 until lines.size - 2) {
                    for (x in 0 until maxLength) {
                        if (lines[line].length > x && lines[line][x] == SPACE)
                            locations.add(Loc(x, line))
                    }
                }
            }
            return locations
        }

        private fun entrances(lines: List<String>): Map<Loc, Name> {
            val entrances = mutableMapOf<Loc, Name>()
            val first = lines.first()
            for (i in first.indices) {
                val firstLetter = first[i]
                if (firstLetter.isLetter()) {
                    val secondLetter = lines[1][i]
                    val key = "$firstLetter$secondLetter"
                    val loc = Loc(i, 2)
                    entrances[loc] = key
                }
            }

            val last = lines.last()
            for (i in last.indices) {
                val secondLetter = last[i]
                if (secondLetter.isLetter()) {
                    val firstLetter = lines[lines.lastIndex - 1][i]
                    val key = "$firstLetter$secondLetter"
                    val loc = Loc(i, lines.lastIndex - 2)
                    entrances[loc] = key
                }
            }

            for (i in lines.indices) {
                val firstLetter = lines[i][0]
                if (firstLetter.isLetter()) {
                    val secondLetter = lines[i][1]
                    val key = "$firstLetter$secondLetter"
                    val loc = Loc(2, i)
                    entrances[loc] = key
                }
            }

            val maxLength = lines.map { it.length }.max()
            if (maxLength != null) {
                val index = maxLength - 1
                for (i in lines.indices) {
                    if (lines[i].length == maxLength) {
                        val secondLetter = lines[i][index]
                        if (secondLetter.isLetter()) {
                            val firstLetter = lines[i][index - 1]
                            val key = "$firstLetter$secondLetter"
                            val point = Loc(index - 2, i)
                            entrances[point] = key
                        }
                    }
                }
            }

            return entrances
        }

        private fun innerPortals(horizontalRange: IntRange, verticalRange: IntRange, lines: List<String>): Map<Loc, Name> {
            val innerPortals = mutableMapOf<Loc, Name>()

            for (x in horizontalRange) {
                val firstLetter = lines[verticalRange.first][x]
                if (firstLetter.isLetter()) {
                    val secondLetter = lines[verticalRange.first + 1][x]
                    val key = "$firstLetter$secondLetter"
                    val loc = Loc(x, verticalRange.first - 1)
                    innerPortals[loc] = key
                }
            }

            for (x in horizontalRange) {
                val secondLetter = lines[verticalRange.last][x]
                if (secondLetter.isLetter()) {
                    val firstLetter = lines[verticalRange.last - 1][x]
                    val key = "$firstLetter$secondLetter"
                    val loc = Loc(x, verticalRange.last + 1)
                    innerPortals[loc] = key
                }
            }


            for (i in verticalRange) {
                val firstLetter = lines[i][horizontalRange.first]
                if (firstLetter.isLetter()) {
                    val secondLetter = lines[i][horizontalRange.first + 1]
                    val key = "$firstLetter$secondLetter"
                    val loc = Loc(horizontalRange.first - 1, i)
                    innerPortals[loc] = key
                }
            }

            for (i in verticalRange) {
                val secondLetter = lines[i][horizontalRange.last]
                if (secondLetter.isLetter()) {
                    val firstLetter = lines[i][horizontalRange.last - 1]
                    val key = "$firstLetter$secondLetter"
                    val loc = Loc(horizontalRange.last + 1, i)
                    innerPortals[loc] = key
                }
            }

            return innerPortals
        }

        private fun horizontalRange(input: List<String>): IntRange {
            val middle = input.size / 2

            val left = (2..input[middle].length).first { input[middle][it] != SPACE && input[middle][it] != WALL }

            val right = (input[middle].length - 3 downTo 2).first { input[middle][it] != SPACE && input[middle][it] != WALL }
            return left..right
        }

        private fun verticalRange(input: List<String>): IntRange {
            val middle = input[3].length / 2

            val upper = (2..input.size).first { input[it][middle] != SPACE && input[it][middle] != WALL }

            val lower = (input.size - 3 downTo 2).first { input[it][middle] != SPACE && input[it][middle] != WALL }
            return upper..lower
        }
    }
}

fun main() {
    val lines =
            Utils.linesFromResource("InputDay20.txt")
    val maze = DonutMaze.createMaze(lines)

    val task1 = maze.minDistanceBetweenStartAndEndPortals()
    val task2 = maze.minRecursiveDistanceBetweenStartAndEndPortals()

    println(
            """
              Day20
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}