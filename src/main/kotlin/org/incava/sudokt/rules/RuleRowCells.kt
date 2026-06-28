package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells

class RuleRowCells(row: Int, cells: PuzzleCells, checker: (unitCells: List<Cell>) -> List<Cell>) : RuleUnits(row, checker) {
    override val unitCells = cells.inRow(unit)
    override fun description(): String = "for row $unit"
}
