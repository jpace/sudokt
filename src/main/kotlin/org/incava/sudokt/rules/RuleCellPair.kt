package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Rule
import org.incava.sudokt.impl.PuzzleData

// runs a rule for two cells against their common unit
abstract class RuleCellPair(val x: Cell, val y: Cell, val unitCells: List<Cell>) : Rule() {
    override fun run(): List<Cell> = run(x, y, unitCells)

    abstract fun run(x: Cell, y: Cell, unitCells: List<Cell>): List<Cell>
}
