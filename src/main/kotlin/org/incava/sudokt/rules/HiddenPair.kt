package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.RuleResult

class HiddenPair(x: Cell, y: Cell, unitCells: List<Cell>, val unitType: String) : PairRule(x, y, unitCells) {
    override fun description() = description("X", "Y", "Unit", "[number1, number2]")

    fun description(x: String, y: String, unitType: String, matching: String) = """
        Hidden Pair
        for cells $x and $y in the same $unitType with two intersecting possibilities $matching that
        are not possibilities in other cells, set the possibilities of $x and $y to only $matching
    """.trimIndent()

    fun description(matching: Set<Int>) =
        description(x.position.toString(), y.position.toString(), unitType, matching.toString())

    override fun run(): RuleResult {
        if (x.possible.isNotEmpty() && y.possible.isNotEmpty()) {
            val inBoth = x.possible.intersect(y.possible)
            if (inBoth.size == 2) {
                val inOthers = (unitCells - x - y).any { it.possible.values.intersect(inBoth).isNotEmpty() }
                if (!inOthers) {
                    // the cell's possibilities might already be the same as inBoth
                    listOf(x, y)
                        .filter { it.possible.values != inBoth }
                        .onEach { it.setPossibles(inBoth) }
                        .takeIf { it.isNotEmpty() }
                        ?.let {
                            return RuleResult(true, description(inBoth), it)
                        }
                }
            }
        }
        return RuleResult(false)
    }
}
