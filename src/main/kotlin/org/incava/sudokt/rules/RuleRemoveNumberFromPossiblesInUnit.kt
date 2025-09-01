package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.impl.PuzzleData

class RuleRemoveNumberFromPossiblesInUnit(cells: Cells) : RuleAllUnits(cells) {
    override fun run() = checkAllUnits()

    fun description() = """
        from a cell X with a defined number, remove that possibility from other cells in X's units"
    """.trimIndent()

    override fun checkUnitCells(unitCells: List<Cell>): List<Cell> {
        val updated = mutableListOf<Cell>()
        unitCells.subList(0, PuzzleData.unitSize).forEach { a ->
            if (a.number != null) {
                // not sure why IJ/Kotlin can't infer from null (Int? -> Int)
                val num = a.number!!
                (unitCells - a).forEach {
                    if (it.removePossible(num)) {
                        updated += it
                    }
                }
            }
        }
        return updated
    }
}