package org.incava.sudokt.rules

import org.incava.sudokt.impl.PuzzleData
import org.incava.sudokt.test.CellsBuilder
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class HiddenPairTest {
    @Test
    fun `possibles not set`() {
        val defaultPossible = PuzzleData.cellIndices.toSet() - setOf(3, 4)
        val unitCells = CellsBuilder()
            .withDefaultPossible(defaultPossible)
            .newCells()
        // 3, 4 in common:
        val x = unitCells[1].apply { setPossibles(setOf(3, 4, 5, 6)) }
        val y = unitCells[2].apply { setPossibles(setOf(1, 2, 3, 4)) }
        val obj = HiddenPair(x, y, unitCells, "row")
        val result = obj.run()
        val executables = listOf(
            { assertEquals(listOf(x, y), result.cells) },
            {
                assertEquals(
                    "Hidden Pair\n" +
                            "for cells 1,2 and 1,3 in the same row with two intersecting possibilities [3, 4] that\n" +
                            "are not possibilities in other cells, set the possibilities of 1,2 and 1,3 to only [3, 4]",
                    result.description
                )
            }
        ) + (unitCells - x - y).map { { assertEquals(defaultPossible, it.possible.values) } } +
                listOf(x, y).map { { assertEquals(setOf(3, 4), it.possible.values) } }
        assertAll(executables)
    }

    @Test
    fun `possibles already set`() {
        val defaultPossible = PuzzleData.cellIndices.toSet() - setOf(3, 4)
        val unitCells = CellsBuilder()
            .withDefaultPossible(defaultPossible)
            .newCells()
        // 3, 4 in common:
        val x = unitCells[1].apply { setPossibles(setOf(3, 4)) }
        val y = unitCells[2].apply { setPossibles(setOf(1, 2, 3, 4)) }
        val obj = HiddenPair(x, y, unitCells, "row")
        val result = obj.run()
        val executables = listOf(
            { assertEquals(listOf(y), result.cells) },
            {
                assertEquals(
                    "Hidden Pair\n" +
                            "for cells 1,2 and 1,3 in the same row with two intersecting possibilities [3, 4] that\n" +
                            "are not possibilities in other cells, set the possibilities of 1,2 and 1,3 to only [3, 4]",
                    result.description
                )
            }
        ) + (unitCells - x - y).map { { assertEquals(defaultPossible, it.possible.values) } } +
                listOf(x, y).map { { assertEquals(setOf(3, 4), it.possible.values) } }
        assertAll(executables)
    }
}
