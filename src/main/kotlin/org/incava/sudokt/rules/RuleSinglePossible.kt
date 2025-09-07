package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells

class RuleSinglePossible(cells: Cells) : RuleEachCell(cells) {
    fun description() = """
        for a cell X without a defined number and with only one possibility I, define the number as I and clear it as a possibility"
    """.trimIndent()

    override fun checkCell(cell: Cell): Boolean {
        if (cell.number == null && cell.possible.size == 1) {
            val number = cell.possible.first()
            cell.number = number
            cell.removePossible(number)
            return true
        } else {
            return false
        }
    }
}