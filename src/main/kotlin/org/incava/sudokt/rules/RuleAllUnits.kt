package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.Rule

abstract class RuleAllUnits(cells: Cells) : Rule(cells) {
    fun checkAllUnits(): List<Cell> {
        val updated = mutableListOf<Cell>()
        val units = (0..8)
        units.forEach { updated += checkRow(it) }
        units.forEach { updated += checkColumn(it) }
        units.forEach { updated += checkBox(it) }
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
