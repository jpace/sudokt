package org.incava.sudokt

import org.incava.io.Qlog
import org.incava.sudokt.rules.InferPossible
import org.incava.sudokt.rules.PrunePeers
import org.incava.sudokt.rules.NakedSingle
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
            // level 1
            val rule = InferPossible(cell)
            runRule(rule)
            // don't show each time
        }

        view.show()

        repeat(100) { iteration ->
            if (obj.isSolved()) {
                Qlog.info("obj.solved!", obj.isSolved())
                Qlog.info("iteration", iteration)
                return
            }
            var singleUpdated = false
            obj.cells.forEach {
                // level 2
                val rule = NakedSingle(it)
                singleUpdated = runRule(rule) || singleUpdated
                // don't show each time
            }
            if (singleUpdated) {
                view.show()
            }

            var removeNumberFromUnitPossibles = false
            obj.cells.forEach { cell ->
                // level 4
                removeNumberFromUnitPossibles = removeFromPossiblesInUnit(cell, cells.inRow(cell.position.row), "row") || removeNumberFromUnitPossibles
                removeNumberFromUnitPossibles = removeFromPossiblesInUnit(cell, cells.inColumn(cell.position.column), "column") || removeNumberFromUnitPossibles
                removeNumberFromUnitPossibles = removeFromPossiblesInUnit(cell, cells.inBox(cell.position.box), "box") || removeNumberFromUnitPossibles
                // don't show each time
            }
            if (removeNumberFromUnitPossibles) {
                view.show()
            }
        }

        Qlog.info("puzzle.solved?", obj.isSolved())
    }

    fun removeFromPossiblesInUnit(cell: Cell, unitCells: List<Cell>, unitType: String): Boolean {
        val rule = PrunePeers(cell, unitCells, unitType)
        return runRule(rule)
    }

    fun runRule(rule: Rule): Boolean {
        // Qlog.info("rule", "${rule.javaClass.simpleName} - ${rule.description()}")
        val result = rule.run()
        return result.updated
    }

    @Test
    fun init() {
        val obj = TestFixture.createPuzzle2()
        TestFixture.checkPuzzle(obj)
    }
}
