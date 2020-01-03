package aoc.day20

import aoc.utils.Utils


internal typealias  Path = MutableList<Loc>

internal typealias Name = String

internal data class Portal(val name: Name, val loc: Loc)

internal data class Loc(val x: Int, val y: Int) {
    fun neighbours(): List<Loc> {
        return listOf(Loc(x - 1, y), Loc(x + 1, y), Loc(x, y - 1), Loc(x, y + 1))
    }
}

internal class DonutMaze(private val portals: Map<Loc, Name>, private val fields: Set<Loc>) {

    private fun computeConnections(): Map<Pair<Portal, Portal>, Int> {
        val connections = mutableMapOf<Pair<Portal, Portal>, Int>()

        for ((entryLoc, entryName) in portals) {
            for ((exitLoc, exitName) in portals) {
                if (entryLoc != exitLoc) {
                    val path = pathBetween(entryLoc, exitLoc)
                    if (path != null) {
                        val segment = Pair(Portal(entryName, entryLoc), Portal(exitName, exitLoc))
                        connections[segment] = path.size
                    }
                }
            }
        }
        return connections
    }

    fun minDistanceBetweenPortals(from: Name, to: Name): Int {
        val paths = mutableListOf<List<Portal>>()
        val visited = mutableSetOf<Loc>()
        val computeConnections = computeConnections()

        fun Portal.neighbours() = computeConnections.keys.filter { (it.first.name == this.name) }


        fun getDistance(portal: Pair<Portal, Portal>): Int? {
            return computeConnections.entries.find { it.key.first.name == portal.first.name && it.key.second.name == portal.second.name }?.component2()
        }

        fun computeDistance(path: List<Portal>): Int {
            val zipWithNext = path.zipWithNext()
            return zipWithNext.mapNotNull { getDistance(it) }.sum() + path.size - 2
        }

        fun paths(f: Portal, t: Portal, portals: MutableList<Portal>) {
            visited.add(f.loc)
            if (f == t) {
                paths.add(portals.toMutableList())
                visited.remove(f.loc)
                portals.remove(f)
            }

            val neighbours = f.neighbours()

            for (neighbour in neighbours) {
                if (neighbour.second.loc !in visited) {
                    if ((portals.size == 1 || (portals.size > 1 && portals.last().loc != neighbour.first.loc))) {
                        portals.add(neighbour.second)
                        paths(neighbour.second, t, portals)
                        portals.remove(neighbour.second)
                    }
                }
            }
        }

        val fromPortalSegment = computeConnections.keys.find { it.first.name == from }
        val toPortalSegment = computeConnections.keys.find { it.first.name == to }
        if (fromPortalSegment != null && toPortalSegment != null)
            paths(fromPortalSegment.first, toPortalSegment.first, mutableListOf(fromPortalSegment.first))

        return paths.map { computeDistance(it) }.min()!!
    }


    private fun pathBetween(portalEntrance: Loc, portalExit: Loc): Path? {
        val paths = mutableListOf<Path>()
        val visited = mutableSetOf<Loc>()

        fun isNeighbour(loc: Loc) = fields.contains(loc)


        fun traversePaths(from: Loc, to: Loc, path: Path) {
            visited.add(from)
            if (from == to) {
                paths.add(path.toMutableList())
                visited.remove(from)
            }

            val neighbours = from.neighbours().filter { isNeighbour(it) }

            for (loc in neighbours) {
                if (loc !in visited) {
                    path.add(loc)
                    traversePaths(loc, to, path)
                    path.remove(loc)
                }
            }
        }
        traversePaths(portalEntrance, portalExit, mutableListOf())

        return paths.minBy { it.size }
    }


    object Parser {
        fun createMaze(lines: List<String>): DonutMaze {
            val verticalRange = verticalRange(lines)
            val horizontalRange = horizontalRange(lines)

            val innerPortals = innerPortals(horizontalRange, verticalRange, lines)
            val outerPortals = entrances(lines)
            val allPortal = mutableMapOf<Loc, Name>()
            allPortal.putAll(innerPortals)
            allPortal.putAll(outerPortals)

            val freeLocations = collectFields(lines)
            return DonutMaze(allPortal, freeLocations)
        }

        private fun collectFields(lines: List<String>): Set<Loc> {
            val locations = mutableSetOf<Loc>()
            val maxLength = lines.map { it.length }.max()
            if (maxLength != null) {
                for (line in 2 until lines.size - 2) {
                    for (x in 0 until maxLength) {
                        if (lines[line].length > x && lines[line][x] == '.')
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

            val left = (2..input[middle].length).first { input[middle][it] != '.' && input[middle][it] != '#' }

            val right = (input[middle].length - 3 downTo 2).first { input[middle][it] != '.' && input[middle][it] != '#' }
            return left..right
        }

        private fun verticalRange(input: List<String>): IntRange {
            val middle = input[3].length / 2

            val upper = (2..input.size).first { input[it][middle] != '.' && input[it][middle] != '#' }

            val lower = (input.size - 3 downTo 2).first { input[it][middle] != '.' && input[it][middle] != '#' }
            return upper..lower
        }
    }
}

fun main() {

    val lines =
            Utils.linesFromResource("InputDay20.txt")
    val maze = DonutMaze.Parser.createMaze(lines)

    val task1 = maze.minDistanceBetweenPortals("AA", "ZZ")
    val task2 = task2()

    println(
            """
              Day20
               Task1: $task1
               Task2: $task2
            """.trimIndent())
}

fun task2() {}