package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells

abstract class RuleBoxCells(box: Int, cells: PuzzleCells, checker: (unitCells: List<Cell>) -> List<Cell>) :
    RuleUnits(box, cells, checker) {
    override fun description(): String = "for box $unit"

    override fun run(): List<Cell> {
        val unitCells = cells.inBox(unit)
        return checker(unitCells)
    }
}
