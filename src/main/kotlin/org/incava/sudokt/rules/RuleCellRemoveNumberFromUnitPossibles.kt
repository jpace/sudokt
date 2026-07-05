package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.RuleResult

class RuleCellRemoveNumberFromUnitPossibles(cell: Cell, unitCells: List<Cell>, val unitType: String) : RuleCellUnit(cell, unitCells) {
    val level = LEVEL

    override fun description() = """
        from a cell X with a defined number, remove that possibility from other cells in X's $unitType"
    """.trimIndent()

    fun description(toCells: List<Cell>) = """
        Remove Number from Possibles in Unit $unitType
        from cell ${cell.position} with the defined number ${cell.number}, remove that possibility from cell ${toCells.map { it.position }.joinToString("")} in X's $unitType"
    """.trimIndent()

    override fun run(): RuleResult {
        val updatedCells = mutableListOf<Cell>()
        val number = cell.number
        if (number != null) {
            (unitCells - cell).forEach { toCell ->
                if (toCell.removePossible(number)) {
                    updatedCells += toCell
                }
            }
        }
        return if (updatedCells.isEmpty()) {
            RuleResult(false)
        } else {
            RuleResult(true, description(updatedCells), updatedCells)
        }
    }

    companion object {
        const val LEVEL = 4
    }
}