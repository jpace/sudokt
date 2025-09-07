package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells

class RuleNakedPairs(cells: Cells) : RulePairs(cells) {
    fun description() = """
        for cells X and Y in the same unit U with possibilities I and J, 
        remove I and J a possible in all other cells in all of X's and Y's unit"
    """.trimIndent()

    override fun run(): List<Cell> = checkAllUnits()

    override fun checkCells(a: Cell, b: Cell, unitCells: List<Cell>): List<Cell> {
        return if (a.possible.isNotEmpty() && a.possible == b.possible) {
            val updated = mutableListOf<Cell>()
            (unitCells - a - b)
                .filter { it.possible.isNotEmpty() }
                .forEach {
                    a.possible.forEach { num ->
                        if (it.removePossible(num)) {
                            updated += it
                        }
                    }
                }
            updated
        } else {
            emptyList()
        }
    }
}
