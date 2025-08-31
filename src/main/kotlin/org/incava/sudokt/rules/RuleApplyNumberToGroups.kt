package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.Rule

class RuleApplyNumberToGroups(cells: Cells) : Rule(cells) {
    private fun checkMatch(x: Cell, index: Int, supplier: (Cell) -> Int) {
        val other = cells.at(index)
        if (supplier(x) == supplier(other) && other.possible.contains(x.number)) {
            other.removePossible(x.number!!)
            updated = true
        }
    }

    override fun run() {
        val indices = (0 until cells.size)
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
    fun checkRow(row: Int) {
        val unitCells = cells.inRow(row)
        checkUnitCells(unitCells)
    }

    fun checkColumn(column: Int) {
        val unitCells = cells.inColumn(column)
        checkUnitCells(unitCells)
    }

    fun checkBox(box: Int) {
        val unitCells = cells.inBox(box)
        checkUnitCells(unitCells)
    }

    fun checkUnitCells(unitCells: List<Cell>) {
        unitCells.subList(0, cells.unitSize).forEach { a ->
            if (a.number != null) {
                (unitCells - a).forEach { it.removePossible(a.number!!) }
            }
        }
    }
}