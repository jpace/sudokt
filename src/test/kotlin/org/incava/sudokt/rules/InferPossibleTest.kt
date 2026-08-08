package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.Cell
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class InferPossibleTest {
    @Test
    fun runWithNoNumberSet() {
        val arg = Cell(1)
        val obj = InferPossible(arg)
        val result = obj.run()
        assertTrue(result.updated)
        assertEquals(listOf(arg), result.cells)
        assertSame(arg, result.cells.first())
        assertEquals(9, arg.possible.size)
    }

    @Test
    fun runWithNumberSet() {
        val arg = Cell(1)
        arg.number = 3
        val obj = InferPossible(arg)
        val result = obj.run()
        assertFalse(result.updated)
        assertEquals(emptyList(), result.cells)
        assertTrue(arg.possible.isEmpty())
    }

    @Test
    fun runWithResultNoNumberSet() {
        val arg = Cell(1)
        val obj = InferPossible(arg)
        val result = obj.run()
        Qlog.info("result", result)
        assertEquals(listOf(arg), result.cells)
        assertTrue(result.updated)
        assertEquals(9, arg.possible.size)
        assertEquals("Infer Possible\nfor cell 1,2 without a defined number, set the possible values to 1 through 9", result.description)
    }

    @Test
    fun runWithResultNumberSet() {
        val arg = Cell(1).also { it.number = 3 }
        val obj = InferPossible(arg)
        val result = obj.run()
        Qlog.info("result", result)
        assertEquals(emptyList(), result.cells)
        assertFalse(result.updated)
        assertEquals(0, arg.possible.size)
        assertEquals("", result.description)
    }
}