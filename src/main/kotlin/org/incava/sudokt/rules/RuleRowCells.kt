package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells

class RuleRowCells(row: Int, puzzleCells: PuzzleCells, checker: (unitCells: List<Cell>) -> List<Cell>) : RuleUnits(row, checker) {
    override val unitCells = puzzleCells.inRow(unit)
    override fun description(): String = "for row $unit"
}
