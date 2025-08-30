package org.incava.sudokt

import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class PositionTest {
    fun newInstance(id: Int) : Position = Position(id)

    @Test
    fun row() {
        assertAll(
            { assertEquals(0, newInstance(0).row) },
            { assertEquals(0, newInstance(4).row) },
            { assertEquals(0, newInstance(8).row) },
            { assertEquals(1, newInstance(9).row) },
            { assertEquals(8, newInstance(80).row) }
        )
    }

    @Test
    fun column() {
        assertAll(
            { assertEquals(0, newInstance(0).column) },
            { assertEquals(8, newInstance(8).column) },
            { assertEquals(0, newInstance(9).column) },
            { assertEquals(1, newInstance(10).column) },
            { assertEquals(8, newInstance(80).column) }
        )
    }

    @Test
    fun box() {
        assertAll(
            { assertEquals(0, newInstance(0).box) },
            { assertEquals(2, newInstance(8).box) },
            { assertEquals(0, newInstance(9).box) },
            { assertEquals(2, newInstance(16).box) },
            { assertEquals(8, newInstance(80).box) }
        )
    }

    @Test
    fun all() {
        val positions = (0..80).map { newInstance(it) }
        (0..8).forEach { row ->
            (0..8).forEach { col ->
                val id = row * 9 + col
                val box = (row / 3) * 3 + col / 3
                val cell = positions[id]
                assertAll(
                    { assertEquals(id, cell.id) },
                    { assertEquals(row, cell.row) },
                    { assertEquals(col, cell.column) },
                    { assertEquals(box, cell.box) }
                )
            }
        }
    }
}
