package org.incava.sudokt.rules

import org.incava.sudokt.Cells
import org.incava.sudokt.test.TestFixture.createCell
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class RuleSinglePossibleTest {
    @Test
    fun checkCell() {
        val cells = listOf(
            createCell(0, setOf(6)),
            createCell(1, setOf(4, 7)),
        )
        val obj = RuleSinglePossible(Cells(cells))
        cells.forEach { obj.checkCell(it) }
        assertAll(
            { assertEquals(6, cells[0].number) },
            { assertNull(cells[1].number) }
        )
    }
}