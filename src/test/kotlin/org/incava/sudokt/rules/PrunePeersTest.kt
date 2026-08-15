package org.incava.sudokt.rules

import org.incava.sudokt.test.CellsBuilder
import org.incava.sudokt.unit.CellsUnit
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

fun <T> Collection<T>.second(): T = this.elementAt(1)
fun <T> Collection<T>.third(): T = this.elementAt(2)

class PrunePeersTest {
    @Test
    fun `with match`() {
        val unitCells = CellsBuilder()
            .withDefaultPossible((4 .. 9).toSet())
            .withPossible(0, (1..3).toSet())
            .withPossible(1, (3..5).toSet())
            .withNumber(2, 3)
            .newCells()
        val a = unitCells[0]
        val b = unitCells[1]
        val c = unitCells[2]
        val obj = PrunePeers(c, CellsUnit(unitCells, 1, "row"))
        val result = obj.run()
        assertEquals(listOf(a, b), result.cells)
        assertEquals(setOf(1, 2), a.possible.values)
        assertEquals(setOf(4, 5), b.possible.values)
        assertEquals("""
            Remove Number from Possibles in Row
            from cell 1,3 with the defined number 3,
            remove that possibility from cell(s) 1,1, 1,2 in X's row
        """.trimIndent(), result.description)
    }

    @Test
    fun `without match`() {
        val unitCells = CellsBuilder()
            .withDefaultPossible((4 .. 9).toSet())
            .withNumber(2, 3)
            .newCells()
        val obj = PrunePeers(unitCells[2], CellsUnit(unitCells, 1, "row"))
        val result = obj.run()
        assertFalse(result.updated)
    }
}
