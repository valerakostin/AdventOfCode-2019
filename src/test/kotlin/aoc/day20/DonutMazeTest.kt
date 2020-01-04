package aoc.day20

import aoc.utils.Utils
import org.junit.Test
import kotlin.test.assertEquals

internal class DonutMazeTest {
    @Test
    fun `test should return 23`() {
        val input = """
                     A
                     A
              #######.#########
              #######.........#
              #######.#######.#
              #######.#######.#
              #######.#######.#
              #####  B    ###.#
            BC...##  C    ###.#
              ##.##       ###.#
              ##...DE  F  ###.#
              #####    G  ###.#
              #########.#####.#
            DE..#######...###.#
              #.#########.###.#
            FG..#########.....#
              ###########.#####
                         Z
                         Z
        """.trimIndent()
        val toList = input.split("\n").toList()
        val maze = DonutMaze.Parser.createMaze(toList)
        val minDistanceBetweenPortals = maze.minDistanceBetweenStartAndEndPortals()
        assertEquals(23, minDistanceBetweenPortals)
    }

    @Test
    fun `test should return 58`() {
        val input = """
                               A               
                               A               
              #################.#############  
              #.#...#...................#.#.#  
              #.#.#.###.###.###.#########.#.#  
              #.#.#.......#...#.....#.#.#...#  
              #.#########.###.#####.#.#.###.#  
              #.............#.#.....#.......#  
              ###.###########.###.#####.#.#.#  
              #.....#        A   C    #.#.#.#  
              #######        S   P    #####.#  
              #.#...#                 #......VT
              #.#.#.#                 #.#####  
              #...#.#               YN....#.#  
              #.###.#                 #####.#  
            DI....#.#                 #.....#  
              #####.#                 #.###.#  
            ZZ......#               QG....#..AS
              ###.###                 #######  
            JO..#.#.#                 #.....#  
              #.#.#.#                 ###.#.#  
              #...#..DI             BU....#..LF
              #####.#                 #.#####  
            YN......#               VT..#....QG
              #.###.#                 #.###.#  
              #.#...#                 #.....#  
              ###.###    J L     J    #.#.###  
              #.....#    O F     P    #.#...#  
              #.###.#####.#.#####.#####.###.#  
              #...#.#.#...#.....#.....#.#...#  
              #.#####.###.###.#.#.#########.#  
              #...#.#.....#...#.#.#.#.....#.#  
              #.###.#####.###.###.#.#.#######  
              #.#.........#...#.............#  
              #########.###.###.#############  
                       B   J   C               
                       U   P   P               
            """.trimIndent()

        val toList = input.split("\n").toList()
        val maze = DonutMaze.Parser.createMaze(toList)
        val minDistanceBetweenPortals = maze.minDistanceBetweenStartAndEndPortals()
        assertEquals(58, minDistanceBetweenPortals)
    }

    @Test
    fun `task1  should return 668`()
    {
        val lines =
                Utils.linesFromResource("InputDay20.txt")
        val maze = DonutMaze.Parser.createMaze(lines)

        val minDistanceBetweenPortals = maze.minDistanceBetweenStartAndEndPortals()
        assertEquals(668, minDistanceBetweenPortals)
    }
}