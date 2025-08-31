package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.view.PuzzleView
import org.junit.jupiter.api.assertAll
import kotlin.test.BeforeTest
import kotlin.test.Test

class RuleApplyNumberToGroupsTest : RuleTestBase() {
    @BeforeTest
    fun setup2() {
        Qlog.info("running setup2")
        val infer = RuleInferPossible(cells)
        infer.execute()
    }

    @Test
    fun checkRow() {
        assertAll(
            { assertPossible(all, 4 to 0) },
            { assertPossible(none, 4 to 1) },
            { assertPossible(none, 4 to 2) },
            { assertPossible(all, 4 to 3) },
            { assertPossible(all, 4 to 4) },
            { assertPossible(all, 4 to 5) },
            { assertPossible(none, 4 to 6) },
            { assertPossible(all, 4 to 7) },
            { assertPossible(all, 4 to 8) }
        )
        val obj = RuleApplyNumberToGroups(cells)
        obj.checkRow(4)
        val valid = (1..5).toSet() + 7
        assertAll(
            { assertPossible(valid, 4 to 0) },
            { assertPossible(none, 4 to 1) },
            { assertPossible(none, 4 to 2) },
            { assertPossible(valid, 4 to 3) },
            { assertPossible(valid, 4 to 4) },
            { assertPossible(valid, 4 to 5) },
            { assertPossible(none, 4 to 6) },
            { assertPossible(valid, 4 to 7) },
            { assertPossible(valid, 4 to 8) }
        )
    }

    @Test
    fun checkColumn() {
        assertAll(
            { assertPossible(none, 0 to 1) },
            { assertPossible(none, 1 to 1) },
            { assertPossible(all, 2 to 1) },
            { assertPossible(all, 3 to 1) },
            { assertPossible(none, 4 to 1) },
            { assertPossible(all, 5 to 1) },
            { assertPossible(all, 6 to 1) },
            { assertPossible(all, 7 to 1) },
            { assertPossible(none, 8 to 1) }
        )
        val obj = RuleApplyNumberToGroups(cells)
        obj.checkColumn(1)
        val valid = (1..5).toSet()
        assertAll(
            { assertPossible(none, 0 to 1) },
            { assertPossible(none, 1 to 1) },
            { assertPossible(valid, 2 to 1) },
            { assertPossible(valid, 3 to 1) },
            { assertPossible(none, 4 to 1) },
            { assertPossible(valid, 5 to 1) },
            { assertPossible(valid, 6 to 1) },
            { assertPossible(valid, 7 to 1) },
            { assertPossible(none, 8 to 1) }
        )
    }

    @Test
    fun checkBox() {
        val view = PuzzleView(puzzle = puzzle, showId = false, showNumber = true, showPossible = true)
        view.show()
        assertAll(
            { assertPossible(all, 0 to 3) },
            { assertPossible(all, 0 to 4) },
            { assertPossible(none, 0 to 5) },
            { assertPossible(none, 1 to 3) },
            { assertPossible(all, 1 to 4) },
            { assertPossible(all, 1 to 5) },
            { assertPossible(none, 2 to 3) },
            { assertPossible(none, 2 to 4) },
            { assertPossible(none, 2 to 5) }
        )
        val obj = RuleApplyNumberToGroups(cells)
        obj.checkBox(1)
        view.show()
        val valid = setOf(1, 2, 4, 7)
        assertAll(
            { assertPossible(valid, 0 to 3) },
            { assertPossible(valid, 0 to 4) },
            { assertPossible(none, 0 to 5) },
            { assertPossible(none, 1 to 3) },
            { assertPossible(valid, 1 to 4) },
            { assertPossible(valid, 1 to 5) },
            { assertPossible(none, 2 to 3) },
            { assertPossible(none, 2 to 4) },
            { assertPossible(none, 2 to 5) }
        )
    }
}
