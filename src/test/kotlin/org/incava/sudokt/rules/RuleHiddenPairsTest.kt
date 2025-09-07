package org.incava.sudokt.rules

import org.incava.sudokt.Cells
import org.incava.sudokt.test.TestFixture.createCells
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class RuleHiddenPairsTest {
    @Test
    fun checkCells() {
        val cells = createCells(
            setOf(3, 8),
            2,
            7,
            setOf(3, 6),
            setOf(3, 4),
            setOf(1, 5, 8),
            setOf(3, 4, 6),
            9,
            setOf(1, 6, 5),
        )
        val obj = RuleHiddenPairs(Cells(cells))
        val updated = obj.checkCells(cells[5], cells[8], cells)
        assertAll(
            { assertEquals(listOf(cells[5], cells[8]), updated) },
            { assertEquals(setOf(1, 5), cells[5].possible) },
            { assertEquals(setOf(1, 5), cells[8].possible) },
        )
    }
}