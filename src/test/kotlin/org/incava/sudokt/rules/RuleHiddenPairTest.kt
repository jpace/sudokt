package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.test.TestFixture.createCells
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class RuleHiddenPairTest {
    @Test
    fun checkUnitCells() {
        val defaultPossible = (0 .. 8).toSet() - setOf(3, 4)
        val unitCells = (0 .. 8).map { Cell(it).also { it.possible = defaultPossible.toMutableSet() } }.toList()
        // 3, 4 in common:
        val x = unitCells[1].also { it.possible = mutableSetOf(3, 4, 5, 6) }
        val y = unitCells[2].also { it.possible = mutableSetOf(1, 2, 3, 4) }
        val obj = RuleHiddenPair(x, y, unitCells)
        val result = obj.run()
        assertEquals(listOf(unitCells[1], unitCells[2]), result)
        assertEquals(setOf(3, 4), unitCells[1].possible)
        assertEquals(setOf(3, 4), unitCells[2].possible)
    }
}
