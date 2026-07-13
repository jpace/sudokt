package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.test.TestFixture.createCells
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class RuleCellRemoveNumberFromUnitPossiblesTest {
    @Test
    fun checkUnitCells() {
        val unitCells = listOf(
            Cell(0), Cell(1), Cell(2), Cell(3), Cell(4), Cell(5), Cell(6), Cell(7), Cell(8)
        )
        unitCells.first().setPossibles((1 .. 4).toSet())
        unitCells[2].setPossibles((3 .. 7).toSet())
        unitCells.last().setPossibles((5 .. 8).toSet())
        val cell = unitCells[1]
        cell.number = 3
        val obj = RuleCellRemoveNumberFromUnitPossibles(cell, unitCells, "row")
        val result = obj.run()
        assertEquals(listOf(unitCells.first(), unitCells[2]), result.cells)
        assertEquals(setOf(1, 2, 4), unitCells.first().possible.values)
        assertEquals(setOf(4, 5, 6, 7), unitCells[2].possible.values)
        assertEquals((5..8).toSet(), unitCells.last().possible.values)
    }
}
