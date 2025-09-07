package org.incava.sudokt.rules

import org.incava.sudokt.Cells
import org.incava.sudokt.test.TestFixture.createCells
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class RuleRemoveNumberFromPossiblesInUnitTest {
    @Test
    fun checkUnitCells() {
        val all = (1..9).toSet()
        val cells = createCells(all, 9, 8, all, all, all, 6, all, all)
        val obj = RuleRemoveNumberFromPossiblesInUnit(Cells(cells))
        val updated = obj.checkUnitCells(cells)
        val filtered = (1..5).toSet() + 7
        val expected = createCells(filtered, 9, 8, filtered, filtered, filtered, 6, filtered, filtered)
        assertAll(
            { assertEquals(expected.size, cells.size) },
            { assertEquals(expected, cells) },
            { assertEquals(listOf(cells[0], cells[3], cells[4], cells[5], cells[7], cells[8]), updated.distinct()) },
            { assertEquals(expected, cells) },
        )
    }
}
