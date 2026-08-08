package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NakedSingleTest {
    @Test
    fun runWithNumberSet() {
        val arg = Cell(1)
        arg.number = 3
        val obj = NakedSingle(arg)
        val result = obj.run()
        assertFalse(result.updated)
        assertEquals(emptyList<Cell>(), result.cells)
    }

    @Test
    fun runWithNumberNotSetOnePossibility() {
        val arg = Cell(1)
        arg.setPossibles(setOf(4))
        val obj = NakedSingle(arg)
        val result = obj.run()
        assertTrue(result.updated)
        assertEquals(listOf(arg), result.cells)
    }

    @Test
    fun runWithNumberNotSetTwoPossibilities() {
        val arg = Cell(1)
        arg.setPossibles(setOf(4, 5))
        val obj = NakedSingle(arg)
        val result = obj.run()
        assertFalse(result.updated)
        assertEquals(emptyList<Cell>(), result.cells)
    }

    @Test
    fun level() {
        assertEquals(2, NakedSingle.LEVEL)
    }
}