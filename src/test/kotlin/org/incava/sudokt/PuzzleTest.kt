package org.incava.sudokt

import org.incava.io.Qlog
import org.incava.sudokt.rules.RuleApplyNumberToGroups
import org.incava.sudokt.rules.RuleInferPossible
import org.incava.sudokt.rules.RuleSinglePossible
import org.incava.sudokt.test.TestFixture
import org.incava.sudokt.view.PuzzleView
import kotlin.test.Test

class PuzzleTest {
    @Test
    fun show() {
        val obj = TestFixture.puzzle1
        val view = PuzzleView(puzzle = obj, showId = false, showNumber = true, showPossible = true)
        view.show()

        val infer = RuleInferPossible(obj.cells)
        infer.execute()
        view.show()

        val rule1 = RuleApplyNumberToGroups(obj.cells)
        val rule2 = RuleSinglePossible(obj.cells)

        repeat(100) { iteration ->
            if (obj.isSolved()) {
                Qlog.info("obj.solved!", obj.isSolved())
                Qlog.info("iteration", iteration)
                return
            }
            rule1.execute()
            Qlog.info("rule1", rule1)
            Qlog.info("rule1.updated?", rule1.updated)
            view.show()

            rule2.execute()
            Qlog.info("rule2", rule2)
            Qlog.info("rule2.updated?", rule2.updated)
            view.show()
        }

        Qlog.info("puzzle.solved?", obj.isSolved())
    }
}