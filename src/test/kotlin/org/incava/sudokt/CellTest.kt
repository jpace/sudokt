package org.incava.sudokt

import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CellTest {
    private fun newInstance(id: Int) = Cell(id)

    @Test
    fun init() {
        val obj = newInstance(4)
        assertNull(obj.number())
        obj.setNumber(7)
        assertEquals(7, obj.number())
    }

    @Test
    fun position() {
        assertAll(
            { assertEquals(0, newInstance(0).position.id) },
            { assertEquals(4, newInstance(4).position.id) }
        )
    }
}
