package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.Rule
import org.incava.sudokt.impl.PuzzleData

abstract class RulePairs(cells: Cells) : RuleAllUnits(cells) {
    override fun checkUnitCells(unitCells: List<Cell>): List<Cell> {
        val updated = mutableListOf<Cell>()
        unitCells.subList(0, PuzzleData.unitSize - 1).withIndex().forEach { (index, a) ->
            unitCells.subList(index + 1, PuzzleData.unitSize).forEach { b ->
                updated += checkCells(a, b, unitCells)
            }
        }
        return updated
    }

    abstract fun checkCells(a: Cell, b: Cell, unitCells: List<Cell>): List<Cell>
}
