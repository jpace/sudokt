package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.test.TestFixture.createCells
import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class RuleNakedPairTest {
    @Test
    fun run() {
        val defaultPossible = (0 .. 1)
        val unitCells = (0 .. 8).map { Cell(it).also { it.possible = defaultPossible.toMutableSet() } }.toList()
        // 3, 4 in common:
        val x = unitCells[1].also { it.possible = mutableSetOf(3, 4) }
        val y = unitCells[2].also { it.possible = mutableSetOf(3, 4) }
        val z = unitCells[3].also { it.possible = mutableSetOf(1, 2, 3, 4) }
        val obj = RuleNakedPair(x, y, unitCells)
        val result = obj.run()
        Qlog.info("result", result)
        assertEquals(listOf(z), result)
        assertEquals(setOf(1, 2), z.possible)
        assertEquals(setOf(3, 4), unitCells[2].possible)
    }
}
