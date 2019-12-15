package aoc.day11

import org.junit.Test
import kotlin.test.assertEquals

internal class PositionTest {
    @Test
    fun `move from (0,0, UP)`() {
        val p = Position(Loc(0, 0), Direction.UP)
        val moveLeft = p.move(0)

        assertEquals(-1, moveLeft.loc.x)
        assertEquals(0, moveLeft.loc.y)
        assertEquals(Direction.LEFT, moveLeft.dir)

        val moveRight = p.move(1)

        assertEquals(1, moveRight.loc.x)
        assertEquals(0, moveRight.loc.y)
        assertEquals(Direction.RIGHT, moveRight.dir)
    }

    @Test
    fun `move from (0,0, DOWN)`() {
        val p = Position(Loc(0, 0), Direction.DOWN)
        val moveLeft = p.move(0)

        assertEquals(1, moveLeft.loc.x)
        assertEquals(0, moveLeft.loc.y)
        assertEquals(Direction.RIGHT, moveLeft.dir)

        val moveRight = p.move(1)

        assertEquals(-1, moveRight.loc.x)
        assertEquals(0, moveRight.loc.y)
        assertEquals(Direction.LEFT, moveRight.dir)
    }

    @Test
    fun `move from (0,0, LEFT)`() {
        val p = Position(Loc(0, 0), Direction.LEFT)
        val moveLeft = p.move(0)

        assertEquals(0, moveLeft.loc.x)
        assertEquals(1, moveLeft.loc.y)
        assertEquals(Direction.DOWN, moveLeft.dir)

        val moveRight = p.move(1)

        assertEquals(0, moveRight.loc.x)
        assertEquals(-1, moveRight.loc.y)
        assertEquals(Direction.UP, moveRight.dir)
    }

    @Test
    fun `move from (0,0, RIGHT)`() {
        val p = Position(Loc(0, 0), Direction.RIGHT)
        val moveLeft = p.move(0)

        assertEquals(0, moveLeft.loc.x)
        assertEquals(-1, moveLeft.loc.y)
        assertEquals(Direction.UP, moveLeft.dir)

        val moveRight = p.move(1)

        assertEquals(0, moveRight.loc.x)
        assertEquals(1, moveRight.loc.y)
        assertEquals(Direction.DOWN, moveRight.dir)
    }
}