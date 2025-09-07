package org.incava.sudokt.rules

import org.incava.sudokt.Cells
import org.incava.sudokt.test.TestFixture.createCells
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class RuleNakedPairsTest {
    @Test
    fun checkCells() {
        val cells = createCells(
            3,
            setOf(1, 4, 5),
            setOf(4, 9),
            6,
            8,
            setOf(4, 9),
            setOf(1, 4),
            setOf(4, 7),
            2
        )
        val obj = RuleNakedPairs(Cells(cells))
        val updated = obj.checkCells(cells[2], cells[5], cells)
        assertAll(
            { assertEquals(listOf(cells[1], cells[6], cells[7]), updated) },
            { assertEquals(setOf(1, 5), cells[1].possible) },
            { assertEquals(setOf(1), cells[6].possible) },
            { assertEquals(setOf(7), cells[7].possible) },
        )
    }
}