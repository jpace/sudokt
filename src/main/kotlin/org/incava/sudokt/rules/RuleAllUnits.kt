package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.Rule
import org.incava.sudokt.impl.PuzzleData

abstract class RuleAllUnits(cells: PuzzleCells) : Rule(cells) {
    fun checkAllUnits(): List<Cell> {
        val updatedCells = mutableListOf<Cell>()
        PuzzleData.cellIndices.forEach { updatedCells += checkRow(it) }
        PuzzleData.cellIndices.forEach { updatedCells += checkColumn(it) }
        PuzzleData.cellIndices.forEach { updatedCells += checkBox(it) }
        super.updated = updatedCells.isNotEmpty()
        return updatedCells
    }

    fun checkUnit(rule: RuleUnits, unit: Int): List<Cell> {
        return rule.run()
    }

    fun checkRow(row: Int): List<Cell> {
        Qlog.info("checking row", row)
        val rule = object : RuleRowCells(row, cells, ::checkCellsInUnit) {}
        return rule.run()
    }

    fun checkColumn(column: Int): List<Cell> {
        Qlog.info("checking column", column)
        val rule = object : RuleColumnCells(column, cells, ::checkCellsInUnit) {}
        return rule.run()
    }

    fun checkBox(box: Int): List<Cell> {
        Qlog.info("checking box", box)
        val rule = object : RuleBoxCells(box, cells, ::checkCellsInUnit) {}
        return checkUnit(rule, box)
    }

    fun checkCellsInUnit(unitCells: List<Cell>) = checkUnitCells(unitCells)

    abstract fun checkUnitCells(unitCells: List<Cell>): List<Cell>
}
