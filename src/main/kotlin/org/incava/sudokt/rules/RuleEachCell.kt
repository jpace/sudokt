package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.PuzzleCells
import org.incava.sudokt.Rule

abstract class RuleEachCell(val puzzleCells: PuzzleCells) : Rule() {
    override fun run(): List<Cell> = puzzleCells.filter { checkCell(it) }

    abstract fun checkCell(cell: Cell): Boolean
}