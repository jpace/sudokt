package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.ceil

class RuleCellSinglePossibleTest {
    @Test
    fun runWithNumberSet() {
        val arg = Cell(1)
        arg.number = 3
        val obj = RuleCellSinglePossible(arg)
        val result = obj.run()
        assertEquals(emptyList<Cell>(), result)
        assertFalse(obj.updated)
    }

    @Test
    fun runWithNumberNotSetOnePossibility() {
        val arg = Cell(1)
        arg.possible.add(4)
        val obj = RuleCellSinglePossible(arg)
        val result = obj.run()
        assertEquals(listOf(arg), result)
        assertTrue(obj.updated)
    }

    @Test
    fun runWithNumberNotSetTwoPossibilities() {
        val arg = Cell(1)
        arg.possible.addAll(listOf(4, 5))
        val obj = RuleCellSinglePossible(arg)
        val result = obj.run()
        assertEquals(emptyList<Cell>(), result)
        assertFalse(obj.updated)
    }

    @Test
    fun level() {
        assertEquals(2, RuleCellSinglePossible.LEVEL)
    }
}