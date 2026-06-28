package org.incava.sudokt.rules

import org.incava.io.Qlog
import org.incava.sudokt.Cell

class RuleHiddenPair(x: Cell, y: Cell, unitCells: List<Cell>) : RuleCellPair(x, y, unitCells) {
    override fun description() = """
        for cells X and Y in the same unit U with two intersecting possibilities (number1, number2) that
        are not possibility in other cells, set the possibilities of X and Y to only (number1, number2)"
    """.trimIndent()

    fun description(matching: Set<Int>) = """
        Hidden Pair
        for cells ${x.position} and ${y.position} in the same unit U with two intersecting possibilities $matching that
        are not possibility in other cells, set the possibilities of ${x.position} and ${y.position} to only $matching"
    """.trimIndent()

    override fun run(x: Cell, y: Cell, unitCells: List<Cell>): List<Cell> {
        if (x.possible.isEmpty() || y.possible.isEmpty()) {
            return emptyList()
        } else {
            val inBoth = x.possible intersect y.possible
            Qlog.info("inBoth", inBoth)
            return if (inBoth.size == 2) {
                val updatedCells = mutableListOf<Cell>()
                val inOthers = (unitCells - x - y).any { (it.possible intersect inBoth).isNotEmpty() }
                Qlog.info("inOthers", inOthers)
                if (!inOthers) {
                    // the possibility might already be the same as inBoth
                    if (x.setPossibles(inBoth)) {
                        updatedCells += x
                        setUpdated(x, description(inBoth))
                    }
                    if (y.setPossibles(inBoth)) {
                        updatedCells += y
                        setUpdated(x, description(inBoth))
                    }
                }
                updatedCells
            } else {
                emptyList()
            }
        }
    }
}
