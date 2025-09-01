package org.incava.sudokt

import org.incava.io.Qlog
import org.incava.sudokt.rules.RuleRemoveNumberFromPossiblesInUnit
import org.incava.sudokt.rules.RuleInferPossible
import org.incava.sudokt.rules.RuleSinglePossible
import org.incava.sudokt.test.TestFixture
import org.incava.sudokt.view.PuzzleView
import kotlin.test.Test

class PuzzleTest {
    @Test
    fun solve() {
        val obj = TestFixture.createPuzzle2()
        val view = PuzzleView(puzzle = obj, showId = false, showNumber = true, showPossible = true)
        view.show()

        val cells = Cells(obj.cells)
        val infer = RuleInferPossible(cells)
        infer.execute()
        view.show()

        val rule1 = RuleRemoveNumberFromPossiblesInUnit(cells)
        val rule2 = RuleSinglePossible(cells)

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

    @Test
    fun init() {
        val obj = TestFixture.createPuzzle2()
        TestFixture.checkPuzzle(obj)
    }
}
