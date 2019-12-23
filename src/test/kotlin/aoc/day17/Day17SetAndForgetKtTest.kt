package aoc.day17

import org.junit.Test
import kotlin.test.assertEquals

internal class Day17SetAndForgetKtTest {
    @Test
    fun `task 1 should return 6672`() {
        val sceneData = collectSceneData()
        val task1 = task1(sceneData)
        assertEquals(6672, task1)
    }

    @Test
    fun `task 2 should return 923017`() {
        val sceneData = collectSceneData()
        val task2 = task2(sceneData)
        val robot = Robot(task2)
        assertEquals(923017, robot.execute())
    }
}