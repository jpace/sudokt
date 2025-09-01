package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.Rule
import org.incava.sudokt.impl.PuzzleData

abstract class RuleRow(cells: Cells) : Rule(cells) {
    override fun run(): List<Cell> {
        val updated = mutableListOf<Cell>()
        PuzzleData.cellIndices.forEach { updated += checkRow(it) }
        return updated
    }

    fun checkRow(row: Int): List<Cell> {
        val updated = mutableListOf<Cell>()
        val unitCells = cells.inRow(row)
        unitCells.forEach {
            if (checkCell(it)) {
                updated += it
            }
        }
        return updated
    }

    abstract fun checkCell(cell: Cell): Boolean
}