package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RuleCellInferPossibleTest {
    @Test
    fun runWithNoNumberSet() {
        val arg = Cell(1)
        val obj = RuleCellInferPossible(arg)
        val result = obj.run()
        assertEquals(listOf(arg), result)
        assertTrue(obj.updated)
        assertEquals(9, arg.possible.size)
    }

    @Test
    fun runWithNumberSet() {
        val arg = Cell(1)
        arg.number = 3
        val obj = RuleCellInferPossible(arg)
        val result = obj.run()
        assertEquals(emptyList<Cell>(), result)
        assertFalse(obj.updated)
        assertTrue(arg.possible.isEmpty())
    }

    @Test
    fun level() {
        val arg = Cell(1)
        assertEquals(1, RuleCellInferPossible.LEVEL)
    }
}