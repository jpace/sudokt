package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.Rule

class RuleApplyNumberToGroups(cells: List<Cell>) : Rule(Cells(cells)) {
    private fun checkMatch(x: Cell, index: Int, supplier: (Cell) -> Int) {
        val other = cells.at(index)
        if (supplier(x) == supplier(other) && other.possible.contains(x.number)) {
            other.removePossible(x.number!!)
            updated = true
        }
    }

    override fun run() {
        val indices = (0 until cells.ids())
        indices.forEach { index ->
            val cell = cells.at(index)
            indices
                .filter { cell.number != null }
                .filter { it != index }
                .forEach { other ->
                    checkMatch(cell, other) { it.position.row }
                    checkMatch(cell, other) { it.position.column }
                    checkMatch(cell, other) { it.position.box }
                }
        }
    }

    fun description() = """
        from a cell X with a defined number, remove that possibility from other cells in X's units"
    """.trimIndent()

    fun level() = 3
}