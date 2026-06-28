package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.Rule

abstract class RuleUnits(val unit: Int, cells: PuzzleCells, val checker: (unitCells: List<Cell>) -> List<Cell>) :
    Rule(cells) {
    override fun description(): String = "for unit $unit"
}
