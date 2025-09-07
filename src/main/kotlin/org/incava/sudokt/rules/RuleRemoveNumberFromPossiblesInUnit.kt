package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.impl.PuzzleData

class RuleRemoveNumberFromPossiblesInUnit(cells: Cells) : RuleAllUnits(cells) {
    override fun run() = checkAllUnits()

    fun description() = """
        from a cell X with a defined number, remove that possibility from other cells in X's units"
    """.trimIndent()

    override fun checkUnitCells(unitCells: List<Cell>): List<Cell> {
        Qlog.info("unitCells", unitCells)
        val updated = mutableListOf<Cell>()
        unitCells.subList(0, PuzzleData.unitSize).forEach { a ->
            val number = a.number
            if (number != null) {
                (unitCells - a).forEach {
                    if (it.removePossible(number)) {
                        updated += it
                    }
                }
            }
        }
        Qlog.info("unitCells", unitCells)
        return updated
    }
}