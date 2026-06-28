package org.incava.sudokt.rules

import org.incava.sudokt.Cell
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

    override fun run(cell: Cell, unitCells: List<Cell>): List<Cell> {
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

    companion object {
        const val LEVEL = 4
    }
}