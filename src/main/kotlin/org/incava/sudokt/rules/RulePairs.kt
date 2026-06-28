package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.impl.PuzzleData

abstract class RulePairs(cells: PuzzleCells) : RuleAllUnits(cells) {
    override fun checkUnitCells(unitCells: List<Cell>): List<Cell> {
        val updatedCells = mutableListOf<Cell>()
        unitCells.subList(0, PuzzleData.unitSize - 1).withIndex().forEach { (index, a) ->
            unitCells.subList(index + 1, PuzzleData.unitSize).forEach { b ->
                updatedCells += checkCells(a, b, unitCells)
            }
        }
        return updatedCells
    }

    abstract fun checkCells(a: Cell, b: Cell, unitCells: List<Cell>): List<Cell>
}
