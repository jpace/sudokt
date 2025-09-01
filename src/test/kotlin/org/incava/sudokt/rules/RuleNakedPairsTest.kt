package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.view.PuzzleView
import org.junit.jupiter.api.assertAll
import kotlin.test.BeforeTest
import kotlin.test.Test

class RuleNakedPairsTest : RuleTestBase() {
    @BeforeTest
    fun setup2() {
        val infer = RuleInferPossible(cells)
        infer.execute()
        val rule1 = RuleRemoveNumberFromPossiblesInUnit(cells)
        rule1.execute()
    }

    @Test
    fun checkRow() {
        val view = PuzzleView(puzzle = puzzle, showId = false, showNumber = true, showPossible = true)
        view.show()
        val obj = RuleNakedPairs(cells)
        val row = 7
        assertAll(
            { assertPossible(setOf(), row, 0) },
            { assertPossible(setOf(1, 4, 5), row, 1) },
            { assertPossible(setOf(4, 9), row, 2) },
            { assertPossible(setOf(), row, 3) },
            { assertPossible(setOf(), row, 4) },
            { assertPossible(setOf(4, 9), row, 5) },
            { assertPossible(setOf(1, 4), row, 6) },
            { assertPossible(setOf(4, 7), row, 7) },
            { assertPossible(setOf(), row, 8) }
        )

        val updated = obj.checkRow(row)
        Qlog.info("updated", updated.distinct())
        view.show(updated.distinct())
        assertAll(
            { assertPossible(setOf(), row, 0) },
            { assertPossible(setOf(1, 5), row, 1) },
            { assertPossible(setOf(4, 9), row, 2) },
            { assertPossible(setOf(), row, 3) },
            { assertPossible(setOf(), row, 4) },
            { assertPossible(setOf(4, 9), row, 5) },
            { assertPossible(setOf(1), row, 6) },
            { assertPossible(setOf(7), row, 7) },
            { assertPossible(setOf(), row, 8) }
        )
    }

    @Test
    fun checkColumn() {
        val obj = RuleNakedPairs(cells)
        val column = 1
        assertAll(
            { assertPossible(setOf(), 0, column) },
            { assertPossible(setOf(), 1, column) },
            { assertPossible(setOf(2, 4), 2, column) },
            { assertPossible(setOf(2, 4), 3, column) },
            { assertPossible(setOf(), 4, column) },
            { assertPossible(setOf(2, 3, 4), 5, column) },
            { assertPossible(setOf(1, 4), 6, column) },
            { assertPossible(setOf(1, 4, 5), 7, column) },
            { assertPossible(emptySet(), 8, column) }
        )
        obj.checkColumn(column)
        assertAll(
            { assertPossible(setOf(), 0, column) },
            { assertPossible(setOf(), 1, column) },
            { assertPossible(setOf(2, 4), 2, column) },
            { assertPossible(setOf(2, 4), 3, column) },
            { assertPossible(setOf(), 4, column) },
            { assertPossible(setOf(3), 5, column) },
            { assertPossible(setOf(1), 6, column) },
            { assertPossible(setOf(1, 5), 7, column) },
            { assertPossible(setOf(), 8, column) }
        )
    }

    @Test
    fun checkBox() {
        val obj = RuleNakedPairs(cells)
        val box = 5
        assertAll(
            { assertPossible(emptySet(), 3, 6) },
            { assertPossible(emptySet(), 3, 7) },
            { assertPossible(setOf(4, 8, 9), 3, 8) },
            { assertPossible(emptySet(), 4, 6) },
            { assertPossible(setOf(4, 5, 7), 4, 7) },
            { assertPossible(setOf(4, 7), 4, 8) },
            { assertPossible(setOf(2, 4, 9), 5, 6) },
            { assertPossible(setOf(4, 7), 5, 7) },
            { assertPossible(setOf(4, 7, 9), 5, 8) }
        )
        obj.checkBox(box)
        assertAll(
            { assertPossible(emptySet(), 3 to 6) },
            { assertPossible(emptySet(), 3 to 7) },
            { assertPossible(setOf(8, 9), 3 to 8) },
            { assertPossible(emptySet(), 4 to 6) },
            { assertPossible(setOf(5), 4 to 7) },
            { assertPossible(setOf(4, 7), 4 to 8) },
            { assertPossible(setOf(2, 9), 5 to 6) },
            { assertPossible(setOf(4, 7), 5 to 7) },
            { assertPossible(setOf(9), 5 to 8) }
        )
    }
}