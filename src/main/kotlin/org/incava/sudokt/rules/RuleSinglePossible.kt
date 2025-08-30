package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.Rule

class RuleSinglePossible(cells: List<Cell>) : Rule(Cells(cells)) {
    override fun run() {
        cells.cells
            .filter { it.number == null && it.possible.size == 1 }
            .forEach {
                val number = it.possible.first()
                it.setNumber(number)
                it.removePossible(number)
                updated = true
            }
    }

    fun description() = """
        for a cell X without a defined number and with only one possibility I, define the number as I and clear it as a possibility"
    """.trimIndent()

    fun level() = 2
}