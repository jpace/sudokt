package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.test.TestFixture.createCells
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class RulePairsTest {
    @Test
    fun checkUnitCells() {
        class RulePairsMock(cells: PuzzleCells) : RulePairs(cells) {
            val invoked = mutableListOf<Triple<Cell, Cell, List<Cell>>>()
            override fun checkCells(a: Cell, b: Cell, unitCells: List<Cell>): List<Cell> {
                invoked += Triple(a, b, unitCells)
                return emptyList()
            }

            override fun description(): String = "bogus description"
            override fun run(): List<Cell> = emptyList()
        }

        val cells = createCells(
            setOf(3, 8),
            2,
            7,
            setOf(3, 6),
            setOf(3, 4),
            setOf(1, 5, 8),
            setOf(3, 4, 6),
            9,
            setOf(1, 6, 5)
        )
        val obj = RulePairsMock(PuzzleCells(cells))
        obj.checkRow(0)
        assertAll(
            { assertEquals(36, obj.invoked.size) },
            { assertEquals(Triple(cells[0], cells[1], cells), obj.invoked[0]) },
            { assertEquals(Triple(cells[0], cells[2], cells), obj.invoked[1]) },
            { assertEquals(Triple(cells[0], cells[3], cells), obj.invoked[2]) },
            { assertEquals(Triple(cells[7], cells[8], cells), obj.invoked[35]) },
        )
    }
}