package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Rule

abstract class RuleUnits(val unit: Int, val checker: (unitCells: List<Cell>) -> List<Cell>) : Rule() {
    abstract val unitCells: List<Cell>
    override fun description(): String = "for unit $unit"
    override fun run(): List<Cell> = checker(unitCells)
}
