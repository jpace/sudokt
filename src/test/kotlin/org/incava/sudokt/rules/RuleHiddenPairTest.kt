package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class RuleHiddenPairTest {
    @Test
    fun runPossiblesNotSet() {
        val defaultPossible = (0..8).toSet() - setOf(3, 4)
        val unitCells = (0..8)
            .map { id -> Cell(id).also { cell -> cell.setPossibles(defaultPossible) } }.toList()
        // 3, 4 in common:
        val x = unitCells[1].also { it.setPossibles(setOf(3, 4, 5, 6)) }
        val y = unitCells[2].also { it.setPossibles(setOf(1, 2, 3, 4)) }
        val obj = RuleHiddenPair(x, y, unitCells, "row")
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
    fun runWithPossiblesAlreadySet() {
        val defaultPossible = (0..8).toSet() - setOf(3, 4)
        val unitCells = (0..8)
            .map { id -> Cell(id).also { cell -> cell.setPossibles(defaultPossible) } }.toList()
        // 3, 4 in common:
        val x = unitCells[1].also { it.setPossibles(setOf(3, 4)) }
        val y = unitCells[2].also { it.setPossibles(setOf(1, 2, 3, 4)) }
        val obj = RuleHiddenPair(x, y, unitCells, "row")
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
