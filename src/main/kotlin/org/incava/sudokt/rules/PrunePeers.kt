package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.RuleResult
import org.incava.sudokt.unit.CellsUnit

// @todo - this is a propagation rule, run after a cell is solved
class PrunePeers(cell: Cell, unit: CellsUnit) : RuleCellUnit(cell, unit) {
    override fun description() = """
        from a cell X with a defined number, remove that possibility from other cells in X's ${unit.type}
    """.trimIndent()

    fun description(toCells: List<Cell>) = """
        Remove Number from Possibles in ${unit.typeAsCapitalized}
        from cell ${cell.position} with the defined number ${cell.number},
        remove that possibility from cell(s) ${toCells.map { it.position }.joinToString(", ")} in X's ${unit.type}
    """.trimIndent()

    override fun run(): RuleResult {
        val number = cell.number
        if (number != null) {
            (unit.cells - cell)
                .filter { toCell -> toCell.removePossible(number) }
                .takeIf { it.isNotEmpty() }
                ?.let { return RuleResult(true, description(it), it) }
        }
        return RuleResult(false)
    }
}