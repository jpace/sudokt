package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells

abstract class RuleColumnCells(column: Int, cells: PuzzleCells, checker: (unitCells: List<Cell>) -> List<Cell>) :
    RuleUnits(column, cells, checker) {
    override fun description(): String = "for column $unit"

    override fun run(): List<Cell> {
        val unitCells = cells.inColumn(unit)
        return checker(unitCells)
    }
}
