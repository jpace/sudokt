package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.RuleResult

class RuleHiddenPair(x: Cell, y: Cell, unitCells: List<Cell>, val unitType: String) : RuleCellPair(x, y, unitCells) {
    override fun description() = """
        for cells X and Y in the same unit U with two intersecting possibilities (number1, number2) that
        are not possibility in other cells, set the possibilities of X and Y to only (number1, number2)"
    """.trimIndent()

    fun description(matching: Set<Int>) = """
        Hidden Pair
        for cells ${x.position} and ${y.position} in the same $unitType with two intersecting possibilities $matching that
        are not possibilities in other cells, set the possibilities of ${x.position} and ${y.position} to only $matching
    """.trimIndent()

    override fun run(): RuleResult {
        if (x.possible.isEmpty() || y.possible.isEmpty()) {
            return RuleResult(false)
        } else {
            val inBoth = x.possible.intersect(y.possible)
            return if (inBoth.size == 2) {
                val updatedCells = mutableListOf<Cell>()
                val inOthers = (unitCells - x - y).any { containsAny(it.possible.values, inBoth) }
                if (!inOthers) {
                    // the possibility might already be the same as inBoth
                    updatePossibles(x, inBoth, updatedCells)
                    updatePossibles(y, inBoth, updatedCells)
                }
                if (updatedCells.isEmpty()) {
                    RuleResult(false)
                } else {
                    RuleResult(true, description(inBoth), updatedCells)
                }
            } else {
                RuleResult(false)
            }
        }
    }

    fun updatePossibles(cell: Cell, possibles: Set<Int>, updated: MutableList<Cell>) {
        if (cell.setPossibles(possibles)) {
            updated += cell
        }
    }

    fun <T> containsAny(x: Collection<T>, y: Collection<T>) = y.any { x.contains(it) }
}
