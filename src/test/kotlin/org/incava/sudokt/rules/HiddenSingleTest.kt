package org.incava.sudokt.rules

import org.incava.sudokt.impl.PuzzleData
import org.incava.sudokt.test.CellsBuilder
import org.incava.sudokt.unit.CellsUnit
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HiddenSingleTest {
    @Test
    fun `with match`() {
        val defaultPossible = PuzzleData.cellIndices.toSet() - setOf(3)
        val unitCells = CellsBuilder()
            .withDefaultPossible(defaultPossible)
            .withPossible(1, setOf(3, 4, 5))
            .newCells()
        val x = unitCells[1]
        val obj = HiddenSingle(CellsUnit(unitCells, 1, "row"))
        val result = obj.run()
        val executables = listOf(
            { assertEquals(listOf(x), result.cells) },
            { assertEquals(3, x.number) },
            { assertTrue(x.possible.isEmpty()) },
            {
                assertEquals(
                    """
                        Hidden Single in Row
                        in unit row 1 with number 3 appearing only once in the row,
                        set that number in the cell 1,2
                    """.trimIndent(), result.description
                )
            }
        ) + (unitCells - x).map { { assertEquals(defaultPossible, it.possible.values) } }
        assertAll(executables)
    }

    @Test
    fun `no match`() {
        val unitCells = CellsBuilder()
            .newCells()
        val obj = HiddenSingle(CellsUnit(unitCells, 1, "row"))
        val result = obj.run()
        assertFalse(result.updated)
    }
}
