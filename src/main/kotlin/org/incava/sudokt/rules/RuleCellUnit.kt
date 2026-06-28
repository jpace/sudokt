package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.impl.PuzzleData

// runs a rule for a cell against its unit
abstract class RuleCellUnit(cell: Cell, val unitCells: List<Cell>) : RuleCell(cell) {
    override fun run(): List<Cell> = run(cell, unitCells)

    abstract fun run(cell: Cell, unitCells: List<Cell>): List<Cell>
}