package org.incava.sudokt.rules

import org.incava.sudokt.Cell
import org.incava.sudokt.RuleResult

class NakedPair(x: Cell, y: Cell, unitCells: List<Cell>, val unitType: String) : PairRule(x, y, unitCells) {
    override fun description() = """
        for cells X and Y in the same unit U with two matching possibilities (number1, number2) that
        are not possibility in other cells, set the possibilities of X and Y to only (number1, number2)"
    """.trimIndent()

    fun description(matching: Set<Int>, others: List<Cell>) = """
        Naked Pair
        for cells ${x.position} and ${y.position} in the same $unitType with identical possibilities $matching,
        remove those possibilities from the other cells ${others.map { it.position }.joinToString(", ")}
    """.trimIndent()

    override fun run(): RuleResult {
        if (x.possible.size != 2 && y.possible.size != 2) {
            return RuleResult(false)
        } else if (x.possible == y.possible) {
            val updatedCells = mutableListOf<Cell>()
            unitCells.forEach { other ->
                if (other != x && other != y) {
                    if (other.removePossibles(x.possible.values)) {
                        updatedCells += other
                    }
                }
            }
            return RuleResult(true, description(x.possible.values, updatedCells), updatedCells)
        } else {
            return RuleResult(false)
        }
    }
}
