package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.RuleResult
import org.incava.sudokt.unit.CellsUnit

class NakedPair(x: Cell, y: Cell, unit: CellsUnit) : PairRule(x, y, unit) {
    override fun description() = """
        for cells X and Y in the same unit U with two matching possibilities (number1, number2) that
        are not possibility in other cells, set the possibilities of X and Y to only (number1, number2)"
    """.trimIndent()

    fun description(matching: Set<Int>, others: List<Cell>) = """
        Naked Pair in ${unit.typeAsCapitalized}
        for cells ${x.position} and ${y.position} in the same ${unit.type} with identical possibilities $matching,
        remove those possibilities from the other cells ${others.map { it.position }.joinToString(", ")}
    """.trimIndent()

    override fun run(): RuleResult {
        if (x.possible.size != 2 && y.possible.size != 2) {
            return RuleResult(false)
        } else if (x.possible == y.possible) {
            unit.cells
                .filter { other ->
                    other != x && other != y && other.removePossibles(x.possible.values)
                }.takeIf { it.isNotEmpty() }
                ?.let {
                    return RuleResult(true, description(x.possible.values, it), it)
                }
        }
        return RuleResult(false)
    }
}
