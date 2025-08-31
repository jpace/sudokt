package org.incava.sudokt

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class CellsTest {
    @Test
    fun inRow() {
        val puzzle = Puzzle()
        val cells = Cells(puzzle.cells)
        val result = cells.inRow(2)
        assertEquals((18..26).toList(), result.map { it.id })
    }

    @Test
    fun inColumn() {
        val puzzle = Puzzle()
        val cells = Cells(puzzle.cells)
        val result = cells.inColumn(5)
        assertEquals(listOf(5, 14, 23, 32, 41, 50, 59, 68, 77), result.map { it.id })
    }

    @Test
    fun inBox() {
        val puzzle = Puzzle()
        val cells = Cells(puzzle.cells)
        val result = cells.inBox(5)
        assertEquals(listOf(33, 34, 35, 42, 43, 44, 51, 52, 53), result.map { it.id })
    }
}