package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells

class RuleBoxCells(box: Int, cells: PuzzleCells, checker: (unitCells: List<Cell>) -> List<Cell>) :
    RuleUnits(box, cells, checker) {

    override val unitCells = cells.inBox(unit)

    override fun description(): String = "for box $unit"
}
