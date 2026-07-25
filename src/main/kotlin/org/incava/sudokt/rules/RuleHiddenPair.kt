package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.RuleResult

class RuleHiddenPair(x: Cell, y: Cell, unitCells: List<Cell>, val unitType: String) : RuleCellPair(x, y, unitCells) {
    override fun description() = description("X", "Y", "Unit", "[number1, number2]")

    fun description(x: String, y: String, unitType: String, matching: String) = """
        Hidden Pair
        for cells $x and $y in the same $unitType with two intersecting possibilities $matching that
        are not possibilities in other cells, set the possibilities of $x and $y to only $matching
    """.trimIndent()

    fun description(matching: Set<Int>) = description(x.position.toString(), y.position.toString(), unitType, matching.toString())

    override fun run(): RuleResult {
        if (x.possible.isEmpty() || y.possible.isEmpty()) {
            return RuleResult(false)
        } else {
            val inBoth = x.possible.intersect(y.possible)
            return if (inBoth.size == 2) {
                val updatedCells = mutableListOf<Cell>()
                val inOthers = (unitCells - x - y).any { it.possible.values.intersect(inBoth).isNotEmpty() }
                if (!inOthers) {
                    // the cell's possibilities might already be the same as inBoth
                    listOf(x, y).forEach { cell ->
                        if (cell.possible.values != inBoth) {
                            cell.setPossibles(inBoth)
                            updatedCells += cell
                        }
                    }
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
}
