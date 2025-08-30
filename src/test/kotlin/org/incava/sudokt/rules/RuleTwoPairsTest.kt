package org.incava.sudokt.rules

import org.incava.sudokt.Cells
import org.incava.sudokt.Puzzle
import org.incava.sudokt.view.PuzzleView
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

class RuleTwoPairsTest {

    @Test
    fun checkRow() {
        val puzzle = Puzzle()
        // obj.show(showId = true, showNumber = true, showPossible = true)

        puzzle.setCell(0, 1, 8)
        puzzle.setCell(0, 2, 1)

        puzzle.setCell(0, 5, 6)
        puzzle.setCell(0, 6, 7)
        puzzle.setCell(0, 8, 5)

        puzzle.setCell(1, 0, 5)
        puzzle.setCell(1, 1, 6)
        puzzle.setCell(1, 2, 3)

        puzzle.setCell(1, 3, 8)

        puzzle.setCell(1, 7, 2)

        puzzle.setCell(2, 3, 3)
        puzzle.setCell(2, 4, 9)
        puzzle.setCell(2, 5, 5)

        puzzle.setCell(2, 6, 8)

        puzzle.setCells(2, mapOf(3 to 3, 4 to 9, 5 to 5, 6 to 8))

        puzzle.setCells(3, listOf(null, null, 5, null, 6, 7, 3, 1, null))
        puzzle.setCells(4, listOf(null, 9, 8, null, null, null, 6, null, null))
        puzzle.setCells(5, listOf(null, null, null, 5, 1, 8))
        puzzle.setCells(6, listOf(8, null, 6, null, 2, null, 5, 9, 3))
        puzzle.setCells(7, listOf(3, null, null, 6, 8, null, null, null, 2))
        puzzle.setCells(8, listOf(2, 7, null, 1))

        val view = PuzzleView(puzzle = puzzle, showId = false, showNumber = true, showPossible = true)
        val infer = RuleInferPossible(puzzle.cells)
        infer.execute()
        val rule1 = RuleApplyNumberToGroups(puzzle.cells)
        rule1.execute()
        view.show()

        val cells = puzzle.cells
        val obj = RuleTwoPairs(cells)
        obj.checkRow(7)
        view.show()
    }

    @Test
    fun checkCellsInRow() {
        val puzzle = Puzzle()
        // obj.show(showId = true, showNumber = true, showPossible = true)

        puzzle.setCell(0, 1, 8)
        puzzle.setCell(0, 2, 1)

        puzzle.setCell(0, 5, 6)
        puzzle.setCell(0, 6, 7)
        puzzle.setCell(0, 8, 5)

        puzzle.setCell(1, 0, 5)
        puzzle.setCell(1, 1, 6)
        puzzle.setCell(1, 2, 3)

        puzzle.setCell(1, 3, 8)

        puzzle.setCell(1, 7, 2)

        puzzle.setCell(2, 3, 3)
        puzzle.setCell(2, 4, 9)
        puzzle.setCell(2, 5, 5)

        puzzle.setCell(2, 6, 8)

        puzzle.setCells(3, listOf(null, null, 5, null, 6, 7, 3, 1, null))
        puzzle.setCells(4, listOf(null, 9, 8, null, null, null, 6, null, null))
        puzzle.setCells(5, listOf(null, null, null, 5, 1, 8))
        puzzle.setCells(6, listOf(8, null, 6, null, 2, null, 5, 9, 3))
        puzzle.setCells(7, listOf(3, null, null, 6, 8, null, null, null, 2))
        puzzle.setCells(8, listOf(2, 7, null, 1))

        val view = PuzzleView(puzzle = puzzle, showId = false, showNumber = true, showPossible = true)
        val infer = RuleInferPossible(puzzle.cells)
        infer.execute()
        val rule1 = RuleApplyNumberToGroups(puzzle.cells)
        rule1.execute()
        view.show()

        val cells = Cells(puzzle.cells)
        val obj = RuleTwoPairs(cells)
        val a = cells.at(7, 1)
        val b = cells.at(7, 2)
        val c = cells.at(7, 5)
        val d = cells.at(7, 6)
        val e = cells.at(7, 7)
        assertAll(
            { assertEquals(setOf(1, 4, 5), a.possible) },
            { assertEquals(setOf(4, 9), b.possible) },
            { assertEquals(setOf(4, 9), c.possible) },
            { assertEquals(setOf(1, 4), d.possible) },
            { assertEquals(setOf(4, 7), e.possible) }
        )
        obj.checkCellsInRow(7, b, c)
        view.show()
        assertAll(
            { assertEquals(setOf(1, 5), a.possible) },
            { assertEquals(setOf(4, 9), b.possible) },
            { assertEquals(setOf(4, 9), c.possible) },
            { assertEquals(setOf(1), d.possible) },
            { assertEquals(setOf(7), e.possible) }
        )
    }
}