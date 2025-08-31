package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells

class RuleInferPossible(cells: Cells) : RuleRow(cells) {
    fun description() = """
        for a cell X without a defined number, set the possible values to 1 through 9"
    """.trimIndent()

    override fun checkCell(cell: Cell): Boolean {
        if (cell.number == null) {
            val possible = (1..9).toMutableSet()
            cell.updatePossible(possible)
            return true
        } else {
            return false
        }
    }
}