package org.incava.sudokt.rules

import org.incava.sudokt.Cells
import org.incava.sudokt.test.TestFixture
import org.incava.sudokt.view.PuzzleView
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

class RuleTwoPairsTest {
    @Test
    fun checkRow() {
        val puzzle = TestFixture.puzzle1

        val view = PuzzleView(puzzle = puzzle, showId = false, showNumber = true, showPossible = true)
        val infer = RuleInferPossible(puzzle.cells)
        infer.execute()
        val rule1 = RuleApplyNumberToGroups(puzzle.cells)
        rule1.execute()
        view.show()

        val cells = Cells(puzzle.cells)
        val obj = RuleTwoPairs(cells)
        val assertions = CellsAssertions(cells)
        assertAll(
            { assertions.assertPossible(setOf(), 7, 0) },
            { assertions.assertPossible(setOf(1, 4, 5), 7, 1) },
            { assertions.assertPossible(setOf(4, 9), 7, 2) },
            { assertions.assertPossible(setOf(), 7, 3) },
            { assertions.assertPossible(setOf(), 7, 4) },
            { assertions.assertPossible(setOf(4, 9), 7, 5) },
            { assertions.assertPossible(setOf(1, 4), 7, 6) },
            { assertions.assertPossible(setOf(4, 7), 7, 7) },
            { assertions.assertPossible(setOf(), 7, 8) }
        )
        obj.checkRow(7)
        view.show()
        assertAll(
            { assertions.assertPossible(setOf(), 7, 0) },
            { assertions.assertPossible(setOf(1, 5), 7, 1) },
            { assertions.assertPossible(setOf(4, 9), 7, 2) },
            { assertions.assertPossible(setOf(), 7, 3) },
            { assertions.assertPossible(setOf(), 7, 4) },
            { assertions.assertPossible(setOf(4, 9), 7, 5) },
            { assertions.assertPossible(setOf(1), 7, 6) },
            { assertions.assertPossible(setOf(7), 7, 7) },
            { assertions.assertPossible(setOf(), 7, 8) }
        )
    }

    @Test
    fun checkColumn() {
        val puzzle = TestFixture.puzzle1

        val view = PuzzleView(puzzle = puzzle, showId = false, showNumber = true, showPossible = true)
        val infer = RuleInferPossible(puzzle.cells)
        infer.execute()
        val rule1 = RuleApplyNumberToGroups(puzzle.cells)
        rule1.execute()
        view.show()

        val cells = Cells(puzzle.cells)
        val obj = RuleTwoPairs(cells)
        val assertions = CellsAssertions(cells)
        assertAll(
            { assertions.assertPossible(setOf(), 0, 1) },
            { assertions.assertPossible(setOf(), 1, 1) },
            { assertions.assertPossible(setOf(2, 4), 2, 1) },
            { assertions.assertPossible(setOf(2, 4), 3, 1) },
            { assertions.assertPossible(setOf(), 4, 1) },
            { assertions.assertPossible(setOf(2, 3, 4), 5, 1) },
            { assertions.assertPossible(setOf(1, 4), 6, 1) },
            { assertions.assertPossible(setOf(1, 4, 5), 7, 1) },
            { assertions.assertPossible(emptySet(), 8, 1) }
        )
        obj.checkColumn(1)
        assertAll(
            { assertions.assertPossible(setOf(), 0, 1) },
            { assertions.assertPossible(setOf(), 1, 1) },
            { assertions.assertPossible(setOf(2, 4), 2, 1) },
            { assertions.assertPossible(setOf(2, 4), 3, 1) },
            { assertions.assertPossible(setOf(), 4, 1) },
            { assertions.assertPossible(setOf(3), 5, 1) },
            { assertions.assertPossible(setOf(1), 6, 1) },
            { assertions.assertPossible(setOf(1, 5), 7, 1) },
            { assertions.assertPossible(setOf(), 8, 1) }
        )
        view.show()
    }

    @Test
    fun checkBox() {
        val puzzle = TestFixture.puzzle1

        val view = PuzzleView(puzzle = puzzle, showId = false, showNumber = true, showPossible = true)
        val infer = RuleInferPossible(puzzle.cells)
        infer.execute()
        val rule1 = RuleApplyNumberToGroups(puzzle.cells)
        rule1.execute()
        view.show()

        val cells = Cells(puzzle.cells)
        val obj = RuleTwoPairs(cells)
        val assertions = CellsAssertions(cells)
        assertAll(
            { assertions.assertPossible(emptySet(), 3, 6) },
            { assertions.assertPossible(emptySet(), 3, 7) },
            { assertions.assertPossible(setOf(4, 8, 9), 3, 8) },
            { assertions.assertPossible(emptySet(), 4, 6) },
            { assertions.assertPossible(setOf(4, 5, 7), 4, 7) },
            { assertions.assertPossible(setOf(4, 7), 4, 8) },
            { assertions.assertPossible(setOf(2, 4, 9), 5, 6) },
            { assertions.assertPossible(setOf(4, 7), 5, 7) },
            { assertions.assertPossible(setOf(4, 7, 9), 5, 8) }
        )
        obj.checkBox(5)
        view.show()
        assertAll(
            { assertions.assertPossible(emptySet(), 3 to 6) },
            { assertions.assertPossible(emptySet(), 3 to 7) },
            { assertions.assertPossible(setOf(8, 9), 3 to 8) },
            { assertions.assertPossible(emptySet(), 4 to 6) },
            { assertions.assertPossible(setOf(5), 4 to 7) },
            { assertions.assertPossible(setOf(4, 7), 4 to 8) },
            { assertions.assertPossible(setOf(2, 9), 5 to 6) },
            { assertions.assertPossible(setOf(4, 7), 5 to 7) },
            { assertions.assertPossible(setOf(9), 5 to 8) }
        )
    }

    fun assertPossible(cells: Cells, expected: Set<Int>, row: Int, column: Int) {
        assertEquals(expected, cells.at(row, column).possible)
    }
}