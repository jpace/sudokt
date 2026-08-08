package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.Cell
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class NakedPairTest {
    @Test
    fun checkUnitCells() {
        val defaultPossible = (0..8).toSet()
        val unitCells = (0..8)
            .map { id -> Cell(id).also { cell -> cell.setPossibles(defaultPossible) } }.toList()
        // 3, 4 in common:
        val x = unitCells[1].also { it.setPossibles(setOf(3, 4)) }
        val y = unitCells[2].also { it.setPossibles(setOf(3, 4)) }
        val otherCells = unitCells - x - y
        val obj = NakedPair(x, y, unitCells, "row")
        Qlog.info("obj", obj)
        Qlog.info("unitCells", unitCells)
        val result = obj.run()
        Qlog.info("unitCells", unitCells)
        Qlog.info("result.description", result.description)
        val expectedPossible = (0..8).toSet() - setOf(3, 4)
        val executables = listOf(
            { assertEquals(otherCells, result.cells) },
            {
                assertEquals(
                    "Naked Pair\n" +
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
    fun run() {
        val defaultPossible = (0..1)
        val unitCells =
            (0..8).map { id -> Cell(id).also { cell -> cell.setPossibles(defaultPossible.toSet()) } }.toList()
        // 3, 4 in common:
        val x = unitCells[1].also { it.setPossibles(setOf(3, 4)) }
        val y = unitCells[2].also { it.setPossibles(setOf(3, 4)) }
        val z = unitCells[3].also { it.setPossibles(setOf(1, 2, 3, 4)) }
        val obj = NakedPair(x, y, unitCells, "row")
        val result = obj.run()
        Qlog.info("result", result)
        assertEquals(listOf(z), result.cells)
        assertEquals(setOf(1, 2), z.possible.values)
        assertEquals(setOf(3, 4), unitCells[2].possible.values)
    }
}
