package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.RuleResult

// @todo - this is a propagation rule, run after a cell is solved
class PrunePeers(cell: Cell, unitCells: List<Cell>, val unitType: String) : RuleCellUnit(cell, unitCells) {
    override fun description() = """
        from a cell X with a defined number, remove that possibility from other cells in X's $unitType"
    """.trimIndent()

    fun description(toCells: List<Cell>) = """
        Remove Number from Possibles in Unit $unitType
        from cell ${cell.position} with the defined number ${cell.number},
        remove that possibility from cell(s) ${toCells.map { it.position }.joinToString(", ")} in X's $unitType"
    """.trimIndent()

    override fun run(): RuleResult {
        val number = cell.number
        if (number != null) {
            val updatedCells = (unitCells - cell).filter { toCell ->
                toCell.removePossible(number)
            }
            if (updatedCells.isNotEmpty()) {
                return RuleResult(true, description(updatedCells), updatedCells)
            }
        }
        return RuleResult(false)
    }
}