package org.incava.sudokt

import org.incava.io.Qlog
import org.incava.sudokt.rules.RuleCellInferPossible
import org.incava.sudokt.rules.RuleRemoveNumberFromPossiblesInUnit
import org.incava.sudokt.rules.RuleSinglePossible
import org.incava.sudokt.test.TestFixture
import org.incava.sudokt.view.PuzzleView1Line
import kotlin.test.Test

class PuzzleTest {
    @Test
    fun solve() {
        val obj = TestFixture.createPuzzle2()
        val view = PuzzleView1Line(puzzle = obj, showId = false, showNumber = true, showPossible = true)
        view.show()

        val cells = PuzzleCells(obj.cells)
        obj.cells.forEach { cell ->
            val rule = RuleCellInferPossible(cell)
            runRule(rule)
            // don't show each time
        }

        view.show()

        val rule1 = RuleRemoveNumberFromPossiblesInUnit(cells)
        val rule2 = RuleSinglePossible(cells)

        repeat(100) { iteration ->
            if (obj.isSolved()) {
                Qlog.info("obj.solved!", obj.isSolved())
                Qlog.info("iteration", iteration)
                return
            }
            if (runRule(rule1)) {
                view.show()
            }
            if (runRule(rule2)) {
                view.show()
            }
        }

        Qlog.info("puzzle.solved?", obj.isSolved())
    }

    fun runRule(rule: Rule): Boolean {
        Qlog.info("rule", "${rule.javaClass.simpleName} - ${rule.description()}")
        val result = rule.execute()
//        Qlog.info("result", result)
//        Qlog.info("rule.updated?", rule.updated)
//        if (rule.updated) {
//            Qlog.info("rule.description", rule.description())
//        }
        return rule.updated
    }

    @Test
    fun init() {
        val obj = TestFixture.createPuzzle2()
        TestFixture.checkPuzzle(obj)
    }
}
