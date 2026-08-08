package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import kotlin.test.Test
import kotlin.test.assertEquals

fun <T> Collection<T>.second(): T = this.elementAt(1)
fun <T> Collection<T>.third(): T = this.elementAt(2)

class PrunePeersTest {
    @Test
    fun checkUnitCells() {
        val unitCells = listOf(
            Cell(0), Cell(1), Cell(2), Cell(3), Cell(4), Cell(5), Cell(6), Cell(7), Cell(8)
        )
        unitCells.first().setPossibles((1 .. 4).toSet())
        unitCells.third().setPossibles((3 .. 7).toSet())
        unitCells.last().setPossibles((5 .. 8).toSet())
        val cell = unitCells.second().also { it.number = 3 }
        val obj = PrunePeers(cell, unitCells, "row")
        val result = obj.run()
        assertEquals(listOf(unitCells.first(), unitCells.third()), result.cells)
        assertEquals(setOf(1, 2, 4), unitCells.first().possible.values)
        assertEquals(setOf(4, 5, 6, 7), unitCells.third().possible.values)
        assertEquals((5..8).toSet(), unitCells.last().possible.values)
    }

    @Test
    fun runWithoutUpdate() {
        val unitCells = (0 .. 8).map { Cell(it) }
        unitCells.first().setPossibles((1 .. 4).toSet())
        unitCells.last().setPossibles((1 .. 4).toSet())
        val cell = unitCells.second().also { it.number = 5 }
        val obj = PrunePeers(cell, unitCells, "row")
        val result = obj.run()
        assertEquals(listOf(), result.cells)
        assertEquals((1 .. 4).toSet(), unitCells.first().possible.values)
        assertEquals((1 .. 4).toSet(), unitCells.last().possible.values)
    }
}
