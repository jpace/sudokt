package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.Rule

class RuleInferPossible(cells: List<Cell>) : Rule(Cells(cells)) {
    override fun run() {
        cells.cells
            .filter { it.number == null }
            .forEach { cell ->
                val possible = (1..9).toMutableSet()
                cell.updatePossible(possible)
            }
    }

    fun description() = """
        for a cell X without a defined number, set the possible values to 1 through 9"
    """.trimIndent()

    fun level() = 1
}