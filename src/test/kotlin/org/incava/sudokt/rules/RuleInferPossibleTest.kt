package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class RuleInferPossibleTest {
    @Test
    fun run() {
        val cells = listOf(
            Cell(0),
            Cell(1).also { it.number = 3 }
        )
        val obj = RuleInferPossible(Cells(emptyList()))
        obj.checkCell(cells[0])
        obj.checkCell(cells[1])
        assertAll(
            { assertEquals((1..9).toSet(), cells[0].possible) },
            { assertEquals(emptySet<Int>(), cells[1].possible) },
        )
    }
}