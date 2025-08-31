package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells
import org.incava.sudokt.Rule

class RuleTwoPairs(cells: Cells) : Rule(cells) {
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
        unitCells.subList(0, cells.unitSize - 1).withIndex().forEach { (index, a) ->
            unitCells.subList(index + 1, cells.unitSize).forEach { b ->
                checkCells(a, b, unitCells)
            }
        }
    }

    fun description() = """
        for cells X and Y in the same unit U with possibilities I and J, 
        remove I and J a possible in all other cells in all of X's and Y's units"
    """.trimIndent()

    fun level() = 4

    override fun run() {
        TODO("Not yet implemented")
    }

    fun checkCells(a: Cell, b: Cell, unitCells: List<Cell>) {
        if (a.possible.isNotEmpty() && a.possible == b.possible) {
            (unitCells - a - b)
                .forEach { it.removePossible(a.possible) }
        }
    }
}
