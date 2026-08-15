package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.impl.PuzzleData
import org.incava.sudokt.test.CellsBuilder
import org.incava.sudokt.unit.CellsUnit
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class NakedPairTest {
    @Test
    fun `with exact match`() {
        val unitCells = CellsBuilder()
            .withPossible(1, setOf(3, 4))
            .withPossible(2, setOf(3, 4))
            .newCells()
        val x = unitCells[1]
        val y = unitCells[2]
        val otherCells = unitCells - x - y
        val obj = NakedPair(x, y, newCellsUnit(unitCells, 1, "row"))
        val result = obj.run()
        val expectedPossible = PuzzleData.cellIndices.toSet() - setOf(3, 4)
        val executables = listOf(
            { assertEquals(otherCells, result.cells) },
            {
                assertEquals(
                    "Naked Pair in Row\n" +
                            "for cells 1,2 and 1,3 in the same row with identical possibilities [3, 4],\n" +
                            "remove those possibilities from the other cells 1,1, 1,4, 1,5, 1,6, 1,7, 1,8, 1,9",
                    result.description
                )
            }
        ) + otherCells.map { { assertEquals(expectedPossible, it.possible.values) } } +
                listOf(x, y).map { { assertEquals(setOf(3, 4), it.possible.values) } }

        assertAll(executables)
    }

    @Test
    fun `with subset match`() {
        val defaultPossible = PuzzleData.cellIndices.toSet() - setOf(3, 4)
        val unitCells = CellsBuilder()
            .withDefaultPossible(defaultPossible)
            .withPossible(1, setOf(3, 4))
            .withPossible(2, setOf(3, 4))
            .withPossible(3, setOf(1, 2, 3, 4))
            .newCells()
        val x = unitCells[1]
        val y = unitCells[2]
        val z = unitCells[3]
        val obj = NakedPair(x, y, newCellsUnit(unitCells, 1, "row"))
        val result = obj.run()
        assertEquals(listOf(z), result.cells)
        assertEquals(setOf(1, 2), z.possible.values)
        assertEquals(setOf(3, 4), unitCells[2].possible.values)
    }

    fun newCellsUnit(cells: List<Cell>, offset: Int, unitType: String): CellsUnit =
        CellsUnit(cells, offset + 1, unitType)
}
