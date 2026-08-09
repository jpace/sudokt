package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NakedSingleTest {
    @Test
    fun `with number set`() {
        val arg = Cell(1).apply { number = 3 }
        val obj = NakedSingle(arg)
        val result = obj.run()
        assertFalse(result.updated)
        assertEquals(emptyList<Cell>(), result.cells)
    }

    @Test
    fun `with number not set, one possibility`() {
        val arg = Cell(1).apply { setPossibles(setOf(4)) }
        val obj = NakedSingle(arg)
        val result = obj.run()
        assertTrue(result.updated)
        assertEquals(listOf(arg), result.cells)
    }

    @Test
    fun `with number not set, multiple possibilities`() {
        val arg = Cell(1).apply { setPossibles(setOf(4, 5)) }
        val obj = NakedSingle(arg)
        val result = obj.run()
        assertFalse(result.updated)
        assertEquals(emptyList<Cell>(), result.cells)
    }
}