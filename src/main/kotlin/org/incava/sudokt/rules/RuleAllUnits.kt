package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.Rule
import org.incava.sudokt.impl.PuzzleData

abstract class RuleAllUnits(private val puzzleCells: PuzzleCells) : Rule() {
    fun checkAllUnits(): List<Cell> {
        return listOf(::checkRow, ::checkColumn, ::checkBox)
            .flatMap { checkUnit(it) }
            .onEach { setUpdated(it) }
    }

    fun checkUnit(checker: (Int) -> List<Cell>): List<Cell> {
        return PuzzleData.cellIndices.flatMap { checker(it) }
    }

    fun checkRow(row: Int): List<Cell> {
        Qlog.info("checking row", row)
        val rule = RuleRowCells(row, puzzleCells, ::checkUnitCells)
        return rule.run()
    }

    fun checkColumn(column: Int): List<Cell> {
        Qlog.info("checking column", column)
        val rule = RuleColumnCells(column, puzzleCells, ::checkUnitCells)
        return rule.run()
    }

    fun checkBox(box: Int): List<Cell> {
        Qlog.info("checking box", box)
        val rule = RuleBoxCells(box, puzzleCells, ::checkUnitCells)
        return rule.run()
    }

    abstract fun checkUnitCells(unitCells: List<Cell>): List<Cell>
}
