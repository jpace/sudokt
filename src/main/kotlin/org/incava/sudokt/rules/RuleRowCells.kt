package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells

abstract class RuleRowCells(row: Int, cells: PuzzleCells, checker: (unitCells: List<Cell>) -> List<Cell>) :
    RuleUnits(row, cells, checker) {
    override fun description(): String = "for row $unit"

    override fun run(): List<Cell> {
        val unitCells = cells.inRow(unit)
        return checker(unitCells)
    }
}
