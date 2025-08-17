package org.incava.sudokt

import org.incava.io.Qlog
import kotlin.test.Test

class PuzzleTest {
    @Test
    fun show() {
        val obj = Puzzle()
        // obj.show(showId = true, showNumber = true, showPossible = true)

        obj.setCell(0, 1, 8)
        obj.setCell(0, 2, 1)

        obj.setCell(0, 5, 6)
        obj.setCell(0, 6, 7)
        obj.setCell(0, 8, 5)

        obj.setCell(1, 0, 5)
        obj.setCell(1, 1, 6)
        obj.setCell(1, 2, 3)

        obj.setCell(1, 3, 8)

        obj.setCell(1, 7, 2)

        obj.setCell(2, 3, 3)
        obj.setCell(2, 4, 9)
        obj.setCell(2, 5, 5)

        obj.setCell(2, 6, 8)

        obj.setCells(3, listOf(null, null, 5, null, 6, 7, 3, 1, null))
        obj.setCells(4, listOf(null, 9, 8, null, null, null, 6, null, null))
        obj.setCells(5, listOf(null, null, null, 5, 1, 8))
        obj.setCells(6, listOf(8, null, 6, null, 2, null, 5, 9, 3))
        obj.setCells(7, listOf(3, null, null, 6, 8, null, null, null, 2))
        obj.setCells(8, listOf(2, 7, null, 1))

        val view = PuzzleView(puzzle = obj, showId = false, showNumber = true, showPossible = true)
        view.show()

        val infer = RuleInferPossible(obj.cells)
        infer.run()
        view.show()

        val rule1 = RuleApplyNumberToGroups(obj.cells)
        val rule2 = RuleSinglePossible(obj.cells)

        repeat(100) { iteration ->
            if (obj.isSolved()) {
                Qlog.info("obj.solved!", obj.isSolved())
                Qlog.info("iteration", iteration)
                return
            }
            rule1.run()
            Qlog.info("rule1", rule1)
            Qlog.info("rule1.updated?", rule1.updated)
            view.show()

            rule2.run()
            Qlog.info("rule2", rule2)
            Qlog.info("rule2.updated?", rule2.updated)
            view.show()
        }

        Qlog.info("puzzle.solved?", obj.isSolved())
    }
}