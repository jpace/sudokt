package org.incava.sudokt.test

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.Puzzle
import org.incava.sudokt.impl.PuzzleData
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

object TestFixture {
    fun createPuzzle2(): Puzzle {
        val puzzle = Puzzle()
        puzzle.let {
            it.setCells(0, mapOf(1 to 8, 2 to 1, 5 to 6, 6 to 7, 8 to 5))
            it.setCells(1, mapOf(0 to 5, 1 to 6, 2 to 3, 3 to 8, 7 to 2))
            it.setCells(2, mapOf(3 to 3, 4 to 9, 5 to 5, 6 to 8))

            it.setCells(3, listOf(null, null, 5, null, 6, 7, 3, 1, null))
            it.setCells(4, listOf(null, 9, 8, null, null, null, 6, null, null))
            it.setCells(5, listOf(null, null, null, 5, 1, 8))
            it.setCells(6, listOf(8, null, 6, null, 2, null, 5, 9, 3))
            it.setCells(7, listOf(3, null, null, 6, 8, null, null, null, 2))
            it.setCells(8, listOf(2, 7, null, 1))
        }
        return puzzle
    }

    fun checkPuzzle(puzzle: Puzzle) {
        val cells = Cells(puzzle.cells)
        assertAll(
            { assertEquals(81, cells.size) },
            { assertEquals(listOf(null, 8, 1, null, null, 6, 7, null, 5), cells.inRow(0).map { it.number }) },
            { assertEquals(listOf(5, 6, 3, 8, null, null, null, 2, null), cells.inRow(1).map { it.number }) },
            { assertEquals(listOf(null, null, null, 3, 9, 5, 8, null, null), cells.inRow(2).map { it.number }) },
            { assertEquals(listOf(null, null, 5, null, 6, 7, 3, 1, null), cells.inRow(3).map { it.number }) },
            { assertEquals(listOf(null, 9, 8, null, null, null, 6, null, null), cells.inRow(4).map { it.number }) },
            { assertEquals(listOf(null, null, null, 5, 1, 8, null, null, null), cells.inRow(5).map { it.number }) },
            { assertEquals(listOf(8, null, 6, null, 2, null, 5, 9, 3), cells.inRow(6).map { it.number }) },
            { assertEquals(listOf(3, null, null, 6, 8, null, null, null, 2), cells.inRow(7).map { it.number }) },
            { assertEquals(listOf(2, 7, null, 1, null, null, null, null, null), cells.inRow(8).map { it.number }) },
        )
    }

    fun createCell(id: Int, number: Int): Cell {
        return Cell(id).also { it.number = number }
    }

    fun createCell(id: Int, possible: Set<Int>): Cell {
        return Cell(id).also { it.setPossibles(possible) }
    }

    fun createCells(map: Map<Int, Int>) : List<Cell> {
        return (0 until PuzzleData.unitSize).map {
            val num = map[it]
            if (num == null) {
                createCell(it, emptySet())
            } else {
                createCell(it, num)
            }
        }
    }

    fun createCells(vararg args: Any) = createCells(args.toList())

    fun createCells(args: List<Any>): List<Cell> {
        return args.withIndex().map { (index, arg) ->
            if (arg is Set<*> && arg.all { it is Int }) {
                val intSet = arg as Set<Int>
                createCell(index, intSet)
            } else {
                val num = arg as Int
                createCell(index, num)
            }
        }
    }
}