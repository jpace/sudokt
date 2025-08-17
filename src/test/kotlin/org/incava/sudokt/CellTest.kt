package org.incava.sudokt

import org.incava.io.Qlog
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CellTest {
    @Test
    fun init() {
        val obj = Cell(4)
        assertNull(obj.number())
        assertEquals((1..9).toSet(), obj.possible)
        obj.removePossible(3)
        assertEquals((1..9).toSet() - 3, obj.possible)
        obj.setNumber(7)
        assertEquals(7, obj.number())
        assertEquals(setOf(7), obj.possible)
    }

    @Test
    fun row() {
        assertAll(
            { assertEquals(0, Cell(0).row()) },
            { assertEquals(0, Cell(4).row()) },
            { assertEquals(0, Cell(8).row()) },
            { assertEquals(1, Cell(9).row()) },
            { assertEquals(8, Cell(80).row()) }
        )
    }

    @Test
    fun column() {
        assertAll(
            { assertEquals(0, Cell(0).column()) },
            { assertEquals(8, Cell(8).column()) },
            { assertEquals(0, Cell(9).column()) },
            { assertEquals(1, Cell(10).column()) },
            { assertEquals(8, Cell(80).column()) }
        )
    }

    @Test
    fun box() {
        assertAll(
            { assertEquals(0, Cell(0).box()) },
            { assertEquals(2, Cell(8).box()) },
            { assertEquals(0, Cell(9).box()) },
            { assertEquals(2, Cell(16).box()) },
            { assertEquals(8, Cell(80).box()) }
        )
    }

    @Test
    fun all() {
        val cells = (0..80).map { Cell(it) }
        cells.forEach { runTest(it) }
    }

    @Test
    fun show() {
        val cells = (0..80).map { Cell(it) }
        (0..8).forEach { row ->
            (0..8).forEach { col ->
                val id = row * 9 + col
                val box = (row / 3) * 3 + col / 3
                val cell = cells[id]
                assertAll(
                    { assertEquals(id, cell.id) },
                    { assertEquals(row, cell.row()) },
                    { assertEquals(col, cell.column()) },
                    { assertEquals(box, cell.box()) }
                )
            }
        }
    }

    fun runTest(cell: Cell) {
        Qlog.info("id", cell.id)
        Qlog.info("row", cell.row())
        Qlog.info("column", cell.column())
        Qlog.info("box", cell.box())
        println()
    }
}