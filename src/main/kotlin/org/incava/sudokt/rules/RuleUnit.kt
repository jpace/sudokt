package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Rule
import org.incava.sudokt.impl.PuzzleData

// runs a rule for an entire unit (row, column, box)
abstract class RuleUnit(val unitCells: List<Cell>) : Rule() {
    override fun run(): List<Cell> = run(unitCells)

    abstract fun run(unitCells: List<Cell>): List<Cell>
}
