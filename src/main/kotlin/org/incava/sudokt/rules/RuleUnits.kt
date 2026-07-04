package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Rule
import org.incava.sudokt.RuleResult

abstract class RuleUnits(val unit: Int, val checker: (unitCells: List<Cell>) -> RuleResult) : Rule() {
    abstract val unitCells: List<Cell>
    override fun description(): String = "for unit $unit"
    override fun run(): RuleResult = checker(unitCells)
}
