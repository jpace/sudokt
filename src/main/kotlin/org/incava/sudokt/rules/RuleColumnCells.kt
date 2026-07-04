package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.RuleResult

class RuleColumnCells(column: Int, puzzleCells: PuzzleCells, checker: (unitCells: List<Cell>) -> RuleResult) : RuleUnits(column, checker) {
    override val unitCells = puzzleCells.inColumn(column)
    override fun description(): String = "for column $unit"
}
