package org.incava.sudokt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CellTest {
    @Test
    fun init() {
        val obj = Cell()
        assertNull(obj.number())
        assertEquals((1 .. 9).toSet(), obj.possible)
        obj.removePossible(3)
        assertEquals((1 .. 9).toSet() - 3, obj.possible)
        obj.setNumber(7)
        assertEquals(7, obj.number())
        assertEquals(setOf(7), obj.possible)
    }
}