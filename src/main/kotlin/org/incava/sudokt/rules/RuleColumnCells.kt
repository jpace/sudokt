package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells

class RuleColumnCells(column: Int, puzzleCells: PuzzleCells, checker: (unitCells: List<Cell>) -> List<Cell>) :
    RuleUnits(column, puzzleCells, checker) {

    override val unitCells = puzzleCells.inColumn(column)

    override fun description(): String = "for column $unit"
}
