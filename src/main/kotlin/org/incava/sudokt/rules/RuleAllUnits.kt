package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.Rule
import org.incava.sudokt.impl.PuzzleData

abstract class RuleAllUnits(cells: Cells) : Rule(cells) {
    fun checkAllUnits(): List<Cell> {
        val updated = mutableListOf<Cell>()
        PuzzleData.cellIndices.forEach { updated += checkRow(it) }
        PuzzleData.cellIndices.forEach { updated += checkColumn(it) }
        PuzzleData.cellIndices.forEach { updated += checkBox(it) }
        return updated
    }

    fun checkRow(row: Int): List<Cell> {
        val unitCells = cells.inRow(row)
        return checkUnitCells(unitCells)
    }

    fun checkColumn(column: Int): List<Cell> {
        val unitCells = cells.inColumn(column)
        return checkUnitCells(unitCells)
    }

    fun checkBox(box: Int): List<Cell> {
        val unitCells = cells.inBox(box)
        return checkUnitCells(unitCells)
    }

    abstract fun checkUnitCells(unitCells: List<Cell>): List<Cell>
}
