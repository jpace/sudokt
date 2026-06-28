package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.impl.PuzzleData

class RuleRemoveNumberFromPossiblesInUnit(cells: PuzzleCells) : RuleAllUnits(cells) {
    override fun run() = checkAllUnits()

    override fun description() = """
        from a cell X with a defined number, remove that possibility from other cells in X's units"
    """.trimIndent()

    fun description(fromCell: Cell, toCell: Cell) = """
        from cell ${fromCell.position} with the defined number ${fromCell.number}, remove that possibility from cell ${toCell.position} in X's unit"
    """.trimIndent()

    override fun checkUnitCells(unitCells: List<Cell>): List<Cell> {
        val updatedCells = mutableListOf<Cell>()
        unitCells.subList(0, PuzzleData.unitSize).forEach { a ->
            val number = a.number
            if (number != null) {
                (unitCells - a).forEach {
                    if (it.removePossible(number)) {
                        updatedCells += it
                        setUpdated(it, description(a, it))
                    }
                }
            }
        }
        return updatedCells
    }
}