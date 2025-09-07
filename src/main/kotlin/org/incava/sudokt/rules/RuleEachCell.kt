package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.Rule

abstract class RuleEachCell(cells: Cells) : Rule(cells) {
    override fun run(): List<Cell> {
        return cells.filter {
            checkCell(it)
        }
    }

    abstract fun checkCell(cell: Cell): Boolean
}