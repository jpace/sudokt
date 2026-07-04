package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.Rule
import org.incava.sudokt.RuleResult
import org.incava.sudokt.impl.PuzzleData

abstract class RuleAllUnits(private val puzzleCells: PuzzleCells) : Rule() {
//    fun checkUnit(checker: (Int) -> RuleResult): RuleResult {
//        return PuzzleData.cellIndices.flatMap { checker(it) }
//    }

    fun checkRow(row: Int): RuleResult {
        Qlog.info("checking row", row)
        val rule = RuleRowCells(row, puzzleCells, ::checkUnitCells)
        return rule.run()
    }

    fun checkColumn(column: Int): RuleResult {
        Qlog.info("checking column", column)
        val rule = RuleColumnCells(column, puzzleCells, ::checkUnitCells)
        return rule.run()
    }

    fun checkBox(box: Int): RuleResult {
        Qlog.info("checking box", box)
        val rule = RuleBoxCells(box, puzzleCells, ::checkUnitCells)
        return rule.run()
    }

    abstract fun checkUnitCells(unitCells: List<Cell>): RuleResult
}
