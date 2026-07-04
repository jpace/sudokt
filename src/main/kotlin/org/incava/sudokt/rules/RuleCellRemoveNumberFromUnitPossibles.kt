package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.RuleResult
import org.incava.sudokt.impl.PuzzleData

class RuleCellRemoveNumberFromUnitPossibles(cell: Cell, unitCells: List<Cell>, val unitType: String) : RuleCellUnit(cell, unitCells) {
    val level = LEVEL

    override fun description() = """
        from a cell X with a defined number, remove that possibility from other cells in X's $unitType"
    """.trimIndent()

    fun description(fromCell: Cell, toCell: Cell) = """
        Remove Number from Possibles in Unit $unitType
        from cell ${fromCell.position} with the defined number ${fromCell.number}, remove that possibility from cell ${toCell.position} in X's $unitType"
    """.trimIndent()

    override fun run(): RuleResult {
        // @todo -- fix this to use *cell*, not *unitCells*
        val updatedCells = mutableListOf<Cell>()
        val updatedPairs = mutableListOf<Pair<Cell, Cell>>()
        unitCells.subList(0, PuzzleData.unitSize).forEach { fromCell ->
            val number = fromCell.number
            if (number != null) {
                (unitCells - fromCell).forEach { toCell ->
                    if (toCell.removePossible(number)) {
                        updatedCells += toCell
                        updatedPairs += Pair(fromCell, toCell)
                    }
                }
            }
        }
        return if (updatedCells.isEmpty()) {
            RuleResult(false)
        } else {
            // @todo -- use the from and to cells
            // build this from updatedPairs
            RuleResult(true, description(), updatedCells)
        }
    }

    companion object {
        const val LEVEL = 4
    }
}