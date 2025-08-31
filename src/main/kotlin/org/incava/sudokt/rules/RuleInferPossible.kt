package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.Rule

class RuleInferPossible(cells: Cells) : Rule(cells) {
    override fun run() {
        cells.filter { it.number == null }
            .forEach { cell ->
                val possible = (1..9).toMutableSet()
                cell.updatePossible(possible)
            }
    }

    fun description() = """
        for a cell X without a defined number, set the possible values to 1 through 9"
    """.trimIndent()

    fun level() = 1

    fun checkCell(position: Pair<Int, Int>) {
        val (row, column) = position
        val cell = cells.at(row, column)
        checkCell(cell)
    }

    fun checkCell(cell: Cell) {
        if (cell.number != null) {
            val possible = (1..9).toMutableSet()
            cell.updatePossible(possible)
        }
    }
}