package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.Cells

class RuleHiddenPairs(cells: Cells) : RulePairs(cells) {
    fun description() = """
        for cells X and Y in the same unit U with possibilities I and J, 
        remove I and J a possible in all other cells in all of X's and Y's units"
    """.trimIndent()

    override fun run(): List<Cell> = checkAllUnits()

    override fun checkCells(a: Cell, b: Cell, unitCells: List<Cell>): List<Cell> {
        if (a.possible.isEmpty() || b.possible.isEmpty()) {
            return emptyList()
        } else {
            val inBoth = a.possible intersect b.possible
            return if (inBoth.size == 2) {
                val updated = mutableListOf<Cell>()
                val inOthers = (unitCells - a - b).any { (it.possible intersect inBoth).isNotEmpty() }
                if (!inOthers) {
                    if (a.setPossibles(inBoth)) {
                        updated += a
                    }
                    if (b.setPossibles(inBoth)) {
                        updated += b
                    }
                }
                updated
            } else {
                emptyList()
            }
        }
    }
}
