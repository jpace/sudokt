package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.impl.PuzzleData
import org.junit.jupiter.api.assertAll
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class RuleHiddenPairsTest : RuleTestBase() {
    @BeforeTest
    fun setup2() {
        val infer = RuleInferPossible(cells)
        infer.execute()
        val rule1 = RuleRemoveNumberFromPossiblesInUnit(cells)
        rule1.execute()
    }

    fun createCell(id: Int, number: Int): Cell {
        return Cell(id).also { it.number = number }
    }

    fun createCell(id: Int, possible: Set<Int>): Cell {
        return Cell(id).also { it.setPossibles(possible) }
    }

    @Test
    fun checkRow() {
        val row = 0
        var id = row * PuzzleData.unitSize
        val cells = listOf(
            createCell(id++, setOf(3, 8)),
            createCell(id++, 2),
            createCell(id++, 7),
            createCell(id++, setOf(3, 6)),
            createCell(id++, setOf(3, 4)),
            createCell(id++, setOf(1, 5, 8)),
            createCell(id++, setOf(3, 4, 6)),
            createCell(id++, 9),
            createCell(id, setOf(1, 6, 5)),
        )
        Qlog.info("cells", cells)
        val obj = RuleHiddenPairs(Cells(cells))
        // val updated = obj.checkCells(cells[5], cells[8], cells)
        val updated = obj.checkRow(0)
        Qlog.info("updated", updated.distinct())
        Qlog.info("cells", cells)
        assertAll(
            { assertEquals(listOf(cells[5], cells[8]), updated) },
            { assertEquals(setOf(1, 5), cells[5].possible) },
            { assertEquals(setOf(1, 5), cells[8].possible) },
        )
    }
}